package kh.org.soramitsu.yubikey.service;

import com.yubico.client.v2.VerificationResponse;
import com.yubico.client.v2.YubicoClient;
import kh.org.soramitsu.yubikey.helper.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class YubikeyClient {
    @ConfigProperty(name = "yubikey.client-id")
    String clientId;

    @ConfigProperty(name = "yubikey.api-key")
    String apiKey;

    @Inject
    IYubikeyStore yubikeyStore;

    public Status register(String username, String otp) {
        try {
            VerificationResponse response = YubicoClient.getClient(Integer.valueOf(clientId), apiKey).verify(otp);
            if (response.isOk()) {
                String yubikeyId = YubicoClient.getPublicId(otp);
                yubikeyStore.save(username, yubikeyId);
                return Status.OK;
            }
            return Status.INVALID;
        } catch (Exception e) {
            return Status.INVALID;
        }
    }

    public Status login(String username, String otp) {
        try {
            VerificationResponse response = YubicoClient.getClient(Integer.valueOf(clientId), apiKey).verify(otp);
            if (response.isOk()) {
                String yubikeyId = YubicoClient.getPublicId(otp);
                if (yubikeyStore.find(username).contains(yubikeyId)) {
                    return Status.OK;
                }
                return Status.YUBIKEY_NOT_COMBINATION;
            }
            return Status.INVALID;
        } catch (Exception e) {
            return Status.INVALID;
        }
    }
}

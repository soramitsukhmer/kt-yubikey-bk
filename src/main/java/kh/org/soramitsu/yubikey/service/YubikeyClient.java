package kh.org.soramitsu.yubikey.service;

import com.google.common.collect.HashMultimap;
import com.yubico.client.v2.VerificationResponse;
import com.yubico.client.v2.YubicoClient;
import com.yubico.client.v2.exceptions.YubicoValidationFailure;
import com.yubico.client.v2.exceptions.YubicoVerificationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class YubikeyClient {
    @ConfigProperty(name = "yubikey.client-id")
    String clientId;

    @ConfigProperty(name = "yubikey.api-key")
    String apiKey;
    private final HashMultimap<String, String> yubikeyIds = HashMultimap.create();

    public Boolean register(String username, String otp) throws YubicoValidationFailure, YubicoVerificationException {
        VerificationResponse response = YubicoClient.getClient(Integer.valueOf(clientId), apiKey).verify(otp);
        if (response.isOk()) {
            String yubikeyId = YubicoClient.getPublicId(otp);
            yubikeyIds.put(username, yubikeyId);
            return true;
        }
        return false;
    }

    public Boolean login(String username, String otp) throws YubicoValidationFailure, YubicoVerificationException {
        VerificationResponse response = YubicoClient.getClient(Integer.valueOf(clientId), apiKey).verify(otp);
        if (response.isOk()) {
            String yubikeyId = YubicoClient.getPublicId(otp);
            if (yubikeyIds.get(username).contains(yubikeyId)) {
                return true;
            }
            throw new NotFoundException("No such username and YubiKey combination.");
        }
        return false;
    }

}

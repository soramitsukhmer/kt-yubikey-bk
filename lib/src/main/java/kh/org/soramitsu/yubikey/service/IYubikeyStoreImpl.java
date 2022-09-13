package kh.org.soramitsu.yubikey.service;

import com.google.common.collect.HashMultimap;

import javax.enterprise.context.ApplicationScoped;
import java.util.Set;

@ApplicationScoped
public class IYubikeyStoreImpl implements IYubikeyStore {

    private final HashMultimap<String, String> yubikeyIds = HashMultimap.create();

    @Override
    public void save(String username, String yubikeyId) {
        yubikeyIds.put(username, yubikeyId);
    }

    @Override
    public Set<String> find(String username) {
        return yubikeyIds.get(username);
    }
}

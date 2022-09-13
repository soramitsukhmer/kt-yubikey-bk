package kh.org.soramitsu.yubikey.service;

import java.util.Set;

public interface IYubikeyStore {
    void save(String username, String yubikeyId);
    Set<String> find(String username);
}

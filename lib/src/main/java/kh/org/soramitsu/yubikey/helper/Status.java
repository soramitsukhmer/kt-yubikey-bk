package kh.org.soramitsu.yubikey.helper;

public enum Status {
    YUBIKEY_NOT_COMBINATION("YubiKey is not belong to this user."),
    INVALID("Invalid yubikey"),
    OK("OK");

    public final String label;

    Status(String label) {
        this.label = label;
    }
}

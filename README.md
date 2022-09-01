# yubikey-kotlin Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew clean build
```

## Specific these two value in client application properties file:
application.properties
```properties
yubikey.client-id={{VALUE}}
yubikey.api-key={{VALUE}}
```

*Note*:
- We use library `com.yubico:yubico-validation-client` to validate yubikey otp.
- The library above using wsapi url: `https://api.yubico.com/wsapi/2.0/verify` to verify otp from yubikey.
- Generate your first Yubikey API key [here](https://upgrade.yubico.com/getapikey)
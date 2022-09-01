package kh.org.soramitsu.yubikey.web;

import kh.org.soramitsu.yubikey.request.YubikeyRequest;
import kh.org.soramitsu.yubikey.service.YubikeyClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/two-factor/webauthn")
@RegisterRestClient
public class YubikeyResource {
    @Inject
    YubikeyClient client;

    public YubikeyResource(YubikeyClient client) {
        this.client = client;
    }

    @POST
    @Path("register")
    public Boolean register(@Valid YubikeyRequest request) throws Exception {
        return client.register(request.username, request.otp);
    }


    @Path("login")
    @POST
    public Boolean login(@Valid YubikeyRequest request) throws Exception {
        return client.login(request.username, request.otp);
    }
}
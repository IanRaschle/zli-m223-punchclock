package ch.zli.m223.controller;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import javax.annotation.processing.Generated;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Credentials;
import ch.zli.m223.service.ApplicationUserService;
import ch.zli.m223.service.SessionService;
import io.smallrye.jwt.build.Jwt;

public class SessionController {

    @Inject
    SessionService sessionService;

    @POST
    @PermitAll
    @Path("/login")
    public Response login(Credentials credentials) {
        if (sessionService.checkCredentials(credentials)) {
            String token = Jwt.issuer("https://zli.example.com").claim("username", credentials.getUserName()).expiresIn(Duration.ofMinutes(10)).groups("user, admin")
                    .sign();
            return ResponseBuilder.ok("Hello " + credentials.getUserName() + "!", MediaType.TEXT_PLAIN_TYPE)
                    .header("Authorization", token)
                    //.cookie(new NewCookie("JWT", token))
                    .build()
                    .toResponse();
        } else {
            return ResponseBuilder.create(403).build().toResponse();
        }
    }

}

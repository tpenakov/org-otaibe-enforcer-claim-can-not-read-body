package org.otaibe.enforcer.claim.can.not.read.body.web.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class RestApiController {

    @POST
    @Path("/auth-entry")
    @Produces(MediaType.TEXT_PLAIN)
    public String post() {
        return "post";
    }
}
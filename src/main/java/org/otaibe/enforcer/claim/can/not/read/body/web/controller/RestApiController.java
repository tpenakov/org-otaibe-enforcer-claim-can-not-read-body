package org.otaibe.enforcer.claim.can.not.read.body.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Path("/api")
@Slf4j
public class RestApiController {

    @POST
    @Path("/auth-entry")
    @Produces(MediaType.TEXT_PLAIN)
    public String post(@Context HttpServletRequest request) {
        log.info("will try to read body for the second time");
        try (ServletInputStream inputStream = request.getInputStream()) {
            String body = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.isBlank(body)) {
                throw new RuntimeException("body is empty");
            }
            log.info("body: {}", body);
        } catch (IOException e) {
            log.error("uanble to read input stream", e);
        }
        return "post";
    }
}
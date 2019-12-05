package org.otaibe.enforcer.claim.can.not.read.body.web.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.keycloak.util.TokenUtil;

import java.text.MessageFormat;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RestControllerTest {

    public static final String TOKEN = "__PUT_YOUR_TOKEN_HERE__";

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/test")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

    @Test
    public void testPostEndpoint() {
        given()
                .header(HttpHeaders.AUTHORIZATION.toString(), MessageFormat.format("{0} {1}", TokenUtil.TOKEN_TYPE_BEARER, TOKEN) )
                .body("{\"auth\":{\"whoAmI\":true}}")
                .when().post("/api/auth-entry")
                .then()
                .statusCode(200)
                .body(is("post"));
    }

}
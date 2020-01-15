package org.otaibe.enforcer.claim.can.not.read.body.cip;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.keycloak.adapters.authorization.ClaimInformationPointProvider;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.util.TokenUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Slf4j
public class FhirClaimInformationPointProvider implements ClaimInformationPointProvider {

    public static final String PREFERRED_USERNAME = "preferred_username";
    public static final String USERID = "userid";
    public static final String USERNAME = "username";

    private final Map<String, Object> config;
    private static final Integer BEARER_OFFSET = TokenUtil.TOKEN_TYPE_BEARER.length() + 1;

    public FhirClaimInformationPointProvider(Map<String, Object> config) {
        this.config = config;
    }

    @Override
    public Map<String, List<String>> resolve(HttpFacade httpFacade) {

        Map<String, List<String>> result = new HashMap<>();
        HttpFacade.Request request = httpFacade.getRequest();

        //Important is to pass buffered=true parameter to getInputStream method
        //otherwise will read the entire input and will not be able to read it again
        //example implementation can be found here:
        // org.keycloak.adapters.authorization.util.RequestPlaceHolderResolver or
        // org.keycloak.adapters.authorization.util.KeycloakSecurityContextPlaceHolderResolver
        try (InputStream inputStream = request.getInputStream(true)) {
            if (inputStream != null && inputStream.available() > 0) {
                if (true) {
                    String body = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    inputStream.reset();
                    log.info("body: {}", body);
                }
                inputStream.reset();
            }
        } catch (IOException e) {
            log.error("unable to read body", e);
        }

        return result;
    }
}

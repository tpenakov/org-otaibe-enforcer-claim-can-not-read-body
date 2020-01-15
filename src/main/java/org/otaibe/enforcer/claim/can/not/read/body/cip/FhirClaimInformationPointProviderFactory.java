package org.otaibe.enforcer.claim.can.not.read.body.cip;

import lombok.Getter;
import org.keycloak.adapters.authorization.ClaimInformationPointProviderFactory;
import org.keycloak.adapters.authorization.PolicyEnforcer;

import java.util.Map;

@Getter
public class FhirClaimInformationPointProviderFactory
        implements ClaimInformationPointProviderFactory<FhirClaimInformationPointProvider> {

    public static final String FHIR_CIP = "fhir-cip";
    public static FhirClaimInformationPointProviderFactory INSTANCE;

    private PolicyEnforcer policyEnforcer;

    @Override
    public String getName() {
        return FHIR_CIP;
    }

    @Override
    public void init(PolicyEnforcer policyEnforcer) {
        this.policyEnforcer = policyEnforcer;
        INSTANCE = this;
    }

    @Override
    public FhirClaimInformationPointProvider create(Map<String, Object> config) {
        return new FhirClaimInformationPointProvider(config);
    }
}

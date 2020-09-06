package com.cashfree.lib.marketplacesettlements.clients;

import com.cashfree.lib.constants.Constants;
import com.cashfree.lib.exceptions.InvalidCredentialsException;
import com.cashfree.lib.exceptions.UnknownExceptionOccured;
import com.cashfree.lib.http.HttpUtils;
import com.cashfree.lib.marketplacesettlements.constants.Endpoints;
import com.cashfree.lib.marketplacesettlements.constants.MarketplaceSettlementsConstants;
import com.cashfree.lib.marketplacesettlements.domains.response.AuthenticationResponse;
import com.cashfree.lib.marketplacesettlements.domains.response.CfMarketplaceSettlementsResponse;

import java.util.HashMap;
import java.util.Map;

public class MarketplaceSettlements {

    private String clientId;
    private String clientSecret;

    private String endpoint;
    private String bearerToken;

    private static MarketplaceSettlements SINGLETON_INSTANCE;

    private MarketplaceSettlements(Constants.Environment env, String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        if (Constants.Environment.PRODUCTION.equals(env)) {
            this.endpoint = Endpoints.PROD_ENDPOINT;
        } else if (Constants.Environment.TEST.equals(env)) {
            this.endpoint = Endpoints.TEST_ENDPOINT;
        }
    }

    public static MarketplaceSettlements getInstance(Constants.Environment env, String clientId, String clientSecret) {
        if (SINGLETON_INSTANCE == null) {
            SINGLETON_INSTANCE = new MarketplaceSettlements(env, clientId, clientSecret);
        }
        return SINGLETON_INSTANCE;
    }

    public boolean init() {
        try {
            updateBearerToken();
            return true;
        } catch (Exception x) {
            return false;
        }
    }

    private Map<String, String> buildAuthHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + bearerToken);
        return headers;
    }

    void updateBearerToken() {
        // Setup headers.
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("X-Client-Id", clientId);
        headersMap.put("X-Client-Secret", clientSecret);

        AuthenticationResponse body =
                HttpUtils.performPostRequest(
                        endpoint + MarketplaceSettlementsConstants.AUTH_REL_URL, headersMap, null, AuthenticationResponse.class);

        if (body == null) {
            throw new UnknownExceptionOccured();
        }
        if (200 == body.getSubCode()) {
            if (body.getData() == null) {
                throw new UnknownExceptionOccured();
            }
            bearerToken = body.getData().getToken();
        } else if (401 == body.getSubCode()) {
            throw new InvalidCredentialsException();
        }
    }

    public boolean verifyToken() {
        Map<String, String> authHeaders = buildAuthHeader();
        CfMarketplaceSettlementsResponse body =
                HttpUtils.performPostRequest(
                        endpoint + MarketplaceSettlementsConstants.VERIFY_TOKEN_REL_URL, authHeaders, null, CfMarketplaceSettlementsResponse.class);

        if (body == null) {
            throw new UnknownExceptionOccured();
        }
        if (200 == body.getSubCode()) {
            return true;
        } else if (403 == body.getSubCode()) {
            return false;
        }
        return false;
    }

    <Request, Response extends CfMarketplaceSettlementsResponse> Response performPostRequest(String relUrl, Request request, Class<Response> clazz) {
        Map<String, String> authHeaders = buildAuthHeader();

        Response body = HttpUtils.performPostRequest(endpoint + relUrl, authHeaders, request, clazz);

        if (body == null) {
            throw new UnknownExceptionOccured();
        }
        // TODO Return this as it is. Make change in the core api itself instead of adding a fix.
        if (body.getSubCode() == null) {
            return body;
        }
        if (403 == body.getSubCode()) {
            updateBearerToken();
            performPostRequest(relUrl, request, clazz);
        }
        return body;
    }

    <Response extends CfMarketplaceSettlementsResponse> Response performGetRequest(String relUrl, Class<Response> clazz) {
        Map<String, String> authHeaders = buildAuthHeader();
        Response body =
                HttpUtils.performGetRequest(endpoint + relUrl, authHeaders, clazz);
        if (body == null) {
            throw new UnknownExceptionOccured();
        }
        if (403 == body.getSubCode()) {
            updateBearerToken();
            performGetRequest(relUrl, clazz);
        }
        return body;
    }
}

package com.cashfree.lib.marketplacesettlements.clients;

import com.cashfree.lib.exceptions.IllegalPayloadException;
import com.cashfree.lib.exceptions.ResourceDoesntExistException;
import com.cashfree.lib.exceptions.UnknownExceptionOccured;
import com.cashfree.lib.http.UriBuilder;
import com.cashfree.lib.marketplacesettlements.constants.MarketplaceSettlementsConstants;
import com.cashfree.lib.marketplacesettlements.domains.VendorDetails;
import com.cashfree.lib.marketplacesettlements.domains.request.AddVendorRequest;
import com.cashfree.lib.marketplacesettlements.domains.request.EditVendorRequest;
import com.cashfree.lib.marketplacesettlements.domains.response.CfMarketplaceSettlementsResponse;
import com.cashfree.lib.marketplacesettlements.domains.response.GetVendorResponse;
import com.cashfree.lib.marketplacesettlements.domains.response.GetVendorsResponse;
import com.cashfree.lib.payout.constants.PayoutConstants;
import com.cashfree.lib.payout.domains.BeneficiaryDetails;
import com.cashfree.lib.payout.domains.response.GetBeneficiaryResponse;
import com.cashfree.lib.payout.domains.response.GetTransfersResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Vendors {

    private final MarketplaceSettlements marketplaceSettlements;

    public Vendors(MarketplaceSettlements marketplaceSettlements) {
        this.marketplaceSettlements = marketplaceSettlements;
    }

    public boolean addVendor(AddVendorRequest vendorRequest) {
        CfMarketplaceSettlementsResponse body = marketplaceSettlements.performPostRequest(
                MarketplaceSettlementsConstants.ADD_VENDOR_REL_URL,
                vendorRequest,
                CfMarketplaceSettlementsResponse.class);
        if (200 == body.getSubCode()) {
            return true;
        }
        throw new UnknownExceptionOccured(body.getMessage());
    }

    public boolean editVendor(EditVendorRequest vendorRequest) {
        CfMarketplaceSettlementsResponse body = marketplaceSettlements.performPostRequest(
                MarketplaceSettlementsConstants.ADD_VENDOR_REL_URL,
                vendorRequest,
                CfMarketplaceSettlementsResponse.class);
        if (200 == body.getSubCode()) {
            return true;
        }
        throw new UnknownExceptionOccured(body.getMessage());
    }

    public VendorDetails getVendorDetails(String vendorId) {
        GetVendorResponse body = marketplaceSettlements.performGetRequest(
                MarketplaceSettlementsConstants.GET_VENDOR_REL_URL + "/" + vendorId, GetVendorResponse.class);
        if (200 == body.getSubCode()) {
            return body.getData();
        } else if (404 == body.getSubCode()) {
            throw new ResourceDoesntExistException("Vendor does not exist");
        }
        throw new UnknownExceptionOccured("Unable to fetch vendor details");
    }

    public List<VendorDetails> getVendorsDetails(Integer maxReturn, Integer lastReturnId) {
        UriBuilder uri = UriBuilder.fromUriString(PayoutConstants.GET_TRANSFERS_REL_URL);
        if (maxReturn != null) {
            uri.queryParam("maxReturn", maxReturn.toString());
        }
        if (lastReturnId != null) {
            uri.queryParam("lastReturnId", lastReturnId.toString());
        }
        return getVendorsDetails(uri.toUriString());
    }

    public List<VendorDetails> getVendorsDetails(String relUrl) {
        GetVendorsResponse body = marketplaceSettlements.performGetRequest(relUrl, GetVendorsResponse.class);
        if (200 == body.getSubCode()) {
            return body.getData();
        } else if (412 == body.getSubCode()) {
            throw new IllegalPayloadException("Invalid maxReturn value passed");
        }
        throw new UnknownExceptionOccured(body.getMessage());
    }

}

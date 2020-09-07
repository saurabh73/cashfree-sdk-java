package com.cashfree.lib.marketplacesettlements.domains.response;

import com.cashfree.lib.marketplacesettlements.domains.VendorDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
public class GetVendorResponse extends CfMarketplaceSettlementsResponse {
    private VendorDetails data;
}

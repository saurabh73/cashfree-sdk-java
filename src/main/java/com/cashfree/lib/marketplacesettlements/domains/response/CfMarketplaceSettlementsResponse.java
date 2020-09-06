package com.cashfree.lib.marketplacesettlements.domains.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CfMarketplaceSettlementsResponse {
  private String status;

  private Integer subCode;

  private String message;
}

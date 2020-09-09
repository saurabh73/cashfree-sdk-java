package com.cashfree.lib.pg.domains.request;

import com.cashfree.lib.annotations.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OrderTokenRequest {

    @NotNull
    private String orderId;

    @NotNull
    private BigDecimal orderAmount;

    private String orderCurrency;
}

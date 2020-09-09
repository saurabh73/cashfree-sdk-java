package com.cashfree.lib.pg.domains.request;

import com.cashfree.lib.annotations.NotNull;

import java.math.BigDecimal;

public class OrderTokenRequest {

    @NotNull
    private String orderId;

    @NotNull
    private BigDecimal orderAmount;

    private String orderCurrency;
}

package com.cashfree.lib.marketplacesettlements.domains;

import com.cashfree.lib.annotations.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString(callSuper=true)
@Accessors(chain = true)
public class VendorDetails {

    @NotNull
    private String vendorId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String bankAccount;

    @NotNull
    private String accountHolder;

    @NotNull
    private String ifsc;

    private String panNo;

    private String aadharNo;

    private String gstin;

    private VendorStatus status;

    private String commission;

    private String balance;

    private String remarks;
}

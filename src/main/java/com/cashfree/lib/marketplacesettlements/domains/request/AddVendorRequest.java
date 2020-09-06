package com.cashfree.lib.marketplacesettlements.domains.request;

import com.cashfree.lib.annotations.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString(callSuper=true)
@Accessors(chain = true)
public class AddVendorRequest {

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

    @NotNull
    private String address1;

    @NotNull
    private String address2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String pincode;
}

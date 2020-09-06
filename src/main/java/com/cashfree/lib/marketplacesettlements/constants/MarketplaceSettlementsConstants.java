package com.cashfree.lib.marketplacesettlements.constants;

public class MarketplaceSettlementsConstants {
    private static final String CES_REL_URL = "/ces";

    private static final String VERSION = "/v1";

    public static final String AUTH_REL_URL = CES_REL_URL + VERSION + "/authorize";

    public static final String VERIFY_TOKEN_REL_URL = CES_REL_URL + VERSION + "/verifyToken";

    public static final String ADD_VENDOR_REL_URL = CES_REL_URL + VERSION + "/addVendor";

    public static final String EDIT_VENDOR_REL_URL = CES_REL_URL + VERSION + "/editVendor";

    public static final String GET_VENDORS_REL_URL = CES_REL_URL + VERSION + "/getVendors";

    public static final String GET_VENDOR_REL_URL = CES_REL_URL + VERSION + "/getVendor";

    public static final String IMPORT_TRANSACTION_REL_URL = CES_REL_URL + VERSION + "/importTransaction";

    public static final String GET_TRANSACTIONS_REL_URL = CES_REL_URL + VERSION + "/getTransactions";

    public static final String GET_TRANSACTION_REL_URL = CES_REL_URL + VERSION + "/getTransaction";

    public static final String ATTACH_VENDOR_REL_URL = CES_REL_URL + VERSION + "/attachVendor";

    public static final String DETACH_VENDOR_REL_URL = CES_REL_URL + VERSION + "/detachVendor";

    public static final String ADJUST_VENDOR_BALANCE_REL_URL = CES_REL_URL + VERSION + "/adjustVendor";

    public static final String REQUEST_VENDOR_PAYOUT_REL_URL = CES_REL_URL + VERSION + "/requestVendorPayout";

    public static final String GET_VENDOR_LEDGER_REL_URL = CES_REL_URL + VERSION + "/getVendorLedger";

    public static final String ORDER_SETTLEMENT_STATUS_REL_URL = CES_REL_URL + VERSION + "/getOrderSettlementStatus";

    public static final String GET_VENDOR_TRANSFER_REL_URL = CES_REL_URL + VERSION + "/getVendorTransfer";

    public static final String GET_VENDOR_TRANSFERS_REL_URL = CES_REL_URL + VERSION + "/getVendorTransfers";

    public static final String TRANSFER_VENDOR_BALANCE_REL_URL = CES_REL_URL + VERSION + "/transferVendorBalance";

    public static final String REQUEST_WITHDRAWAL_REL_URL = CES_REL_URL + VERSION + "/requestWithdrawal";

    public static final String CHECK_BALANCE_REL_URL = CES_REL_URL + VERSION + "/getBalance";

    public static final String GET_MERCHANT_LEDGER_REL_URL = CES_REL_URL + VERSION + "/getLedger";

}

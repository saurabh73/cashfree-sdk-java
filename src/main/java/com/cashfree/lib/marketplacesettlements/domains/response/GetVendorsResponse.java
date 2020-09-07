package com.cashfree.lib.marketplacesettlements.domains.response;

import com.cashfree.lib.annotations.Deserialize;
import com.cashfree.lib.http.ObjectReaderUtils;
import com.cashfree.lib.marketplacesettlements.domains.VendorDetails;
import com.cashfree.lib.serializers.JsonFieldDeserializer;
import com.cashfree.lib.utils.CommonUtils;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
public class GetVendorsResponse  extends CfMarketplaceSettlementsResponse {
    @Deserialize(using = ListDeserializer.class)
    private List<VendorDetails> data;
    private String lastReturnId;


    public static final class ListDeserializer implements JsonFieldDeserializer<List<VendorDetails>> {
        public ListDeserializer() {
        }

        @Override
        public List<VendorDetails> deserialize(String serializedString) {
            if (CommonUtils.isBlank(serializedString)) {
                return null;
            }

            JsonArray jsonArray = Json.parse(serializedString).asArray();
            List<VendorDetails> vendorDetailsList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); ++i) {
                JsonValue jsonValue = jsonArray.get(i);
                vendorDetailsList.add((VendorDetails) ObjectReaderUtils.getFieldInstance(jsonValue, VendorDetails.class));
            }

            return vendorDetailsList;
        }
    }
}
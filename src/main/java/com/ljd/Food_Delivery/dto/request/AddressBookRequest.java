package com.ljd.Food_Delivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookRequest {

    private long id;

    private long userId;

    private String consignee;

    private byte sex;

    private String phone;

    private String provinceCode;

    private String provinceName;

    private String cityCode;

    private String cityName;

    private String districtCode;

    private String districtName;

    private String detail;

    private String label;

    private byte isDefault;

    private int isDeleted;
}

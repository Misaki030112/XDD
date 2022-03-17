package com.hznu.xdd.domain.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class locationDto {
    private String name;

    private Double latitude;

    private Double longitude;

    private String address;

    private String province;

    private String city;

    private String district;
}

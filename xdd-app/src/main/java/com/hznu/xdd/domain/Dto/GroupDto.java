package com.hznu.xdd.domain.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class GroupDto {
    private Integer id;

    private List<Attachment> attachment;

    private Location location;

    @NotNull(message="label 字段不能为空")
    private String label;

    private String topic;

    @NotNull(message = "content 字段不能为空")
    private String content;

    private String title;

    private String params;

    @Data
    public class Attachment{
        private String attachment_type;
        private String attachment_url;
    }

    @Data
    public class Location{
        private String name;
        private Float latitude;
        private Float longitude;
        private String address;
        private String province;
        private String city;
        private String district;
    }
}

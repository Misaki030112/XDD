package com.hznu.xdd.domain.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class UGCDto {
    private List<attachmentDto> attachment;

    private Integer id;

    private Integer user_id;

    private locationDto location;

    private String label;

    private String topic;

    private String content;

    private String title;

    private String key;

    private String order_by;

    private Integer parent_id;

    private String to_type;

    private Integer to_id;

    private boolean status;

    private Integer page;

    private Integer offset;

    private boolean anonymous;


}

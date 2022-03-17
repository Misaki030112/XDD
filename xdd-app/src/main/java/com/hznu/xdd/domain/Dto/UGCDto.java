package com.hznu.xdd.domain.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class UGCDto {
    private List<attachmentDto> attachment;

    private Integer id;

    private Integer userId;

    private locationDto location;

    private String label;

    private String topic;

    private String content;

    private String title;

    private boolean anonymous;


}

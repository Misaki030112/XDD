package com.hznu.xdd.domain.Dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class attachmentDto {
    private String attachment_type;

    private String attachment_url;
}

package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AnswerVO {
    private Integer id;

    private String content;
}

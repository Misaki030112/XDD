package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.answerDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AnswerVO implements Serializable {
    private Integer id;

    private String content;

    public AnswerVO(answerDO a) {
        this.id = a.getId();
        this.content = a.getContent();
    }
}

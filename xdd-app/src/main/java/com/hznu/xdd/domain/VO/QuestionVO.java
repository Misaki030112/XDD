package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.questionDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class QuestionVO implements Serializable {
    private Integer id;

    private String title;

    private String sub_title;

    private Integer right_answer_id;

    private List<AnswerVO> answer;

    public QuestionVO(questionDO q) {
        this.id=q.getId();
        this.title=q.getTitle();
        this.sub_title=q.getSub_title();
        this.right_answer_id=q.getRight_answer_id();
    }
}

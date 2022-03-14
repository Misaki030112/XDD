package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class QuestionVO{
    private Integer id;

    private String title;

    private String sub_title;

    private Integer right_answer_id;

    private List<AnswerVO> answer;

}

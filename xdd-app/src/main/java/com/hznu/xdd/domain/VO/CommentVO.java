package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommentVO {
    private Integer id;

    private UserVO userVO;

    private Integer vote_num;

    private Date create_time;

    private String content;

    private CommentVO parent_comment;
}

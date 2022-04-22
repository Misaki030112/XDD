package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommentVO implements Serializable {
    private Integer id;

    private Integer parent_id;

    private UserVO user_info;

    private Integer vote_num;

    private Date create_time;

    private String content;

    private List<CommentVO> parent_comment;
}

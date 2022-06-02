package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CourseCommentVO {
    private Integer id;
    private String content;
    private Date time;
    private boolean is_anonymous;
    private UserVO user_info;


    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}

package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.hznu.xdd.domain.Dto.CourseCommentDto;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class courseCommentDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private Integer course_id;

    private String content;

    private Integer teacher_id;

    private Integer parent_id;

    private Boolean is_roll;

    private BigDecimal teacher_score;

    private Double credit;

    private Boolean is_anonymous;

    private static final long serialVersionUID = 1L;

    public courseCommentDO() {
    }

    public courseCommentDO(CourseCommentDto courseCommentDto) {
        this.course_id=courseCommentDto.getCourse_id();
        this.content=courseCommentDto.getContent();
        this.teacher_id=courseCommentDto.getTeacher_id();
        this.parent_id=courseCommentDto.getParent_id();
        this.is_roll=courseCommentDto.getIs_roll();
        this.teacher_score=courseCommentDto.getTeacher_score();
        this.credit=courseCommentDto.getCredit();
        this.is_anonymous=courseCommentDto.getIs_anonymous();
    }

    public courseCommentDO(Integer course_id) {
        this.course_id = course_id;
    }
}
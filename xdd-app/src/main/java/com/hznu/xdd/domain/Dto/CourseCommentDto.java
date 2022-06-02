package com.hznu.xdd.domain.Dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CourseCommentDto {
    @NotNull
    private Integer course_id;
    @NotNull
    private String content;
    @NotNull
    private Double credit;
    @NotNull
    private Boolean is_roll;
    @NotNull
    private BigDecimal teacher_score;
    @NotNull
    private Integer teacher_id;
    @NotNull
    private Integer parent_id;
    @NotNull
    private Boolean is_anonymous;

    public Boolean isIs_roll() {
        return is_roll;
    }

    public void setIs_roll(Boolean is_roll) {
        this.is_roll = is_roll;
    }

    public Boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(Boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }
}

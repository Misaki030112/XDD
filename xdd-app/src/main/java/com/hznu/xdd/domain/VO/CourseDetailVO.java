package com.hznu.xdd.domain.VO;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CourseDetailVO {
    private Integer id;
    private String name;
    private String college;
    private String school;
}

package com.hznu.xdd.domain.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TeacherCourseVO {
    private Integer id;
    private String name;
    private String comment;
    private String avg_credit;
}

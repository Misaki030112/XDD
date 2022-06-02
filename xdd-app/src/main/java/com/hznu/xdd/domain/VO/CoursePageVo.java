package com.hznu.xdd.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
public class CoursePageVo {
    private List<? extends Object> list;

    private Long total;
}

package com.hznu.xdd.domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class CourseDto {
    
    @NotNull
    private String teacher;
    @NotNull
    private Integer college_id;
    @NotNull
    private String course;
    
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseSearchDto{
        @NotNull
        private String key;
        @NotNull
        private String teacher;
        @NotNull
        private String college;
        @NotNull
        private String campus;
        @NotNull
        private String school;
        @NotNull
        private String order_by;
        @NotNull
        private Integer page;
        @NotNull
        private Integer offset;
    }
    
    
    
}

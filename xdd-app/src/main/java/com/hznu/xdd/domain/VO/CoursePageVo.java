package com.hznu.xdd.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CoursePageVo extends BasePageVO{

    @Data
    @Accessors(chain = true)
    public static class CourseDetailVO {
        private Integer id;
        private String name;
        private String college;
        private String school;
    }

    @Data
    @Accessors(chain = true)
    public static class DetailVO {
        private Integer id;
        private String name;
        private Integer college_id;
        private Integer school_id;
    }

    @Data
    @Accessors(chain = true)
    public static class CourseCommentVO {
        private Integer id;
        private String content;
        private Date time;
        private Boolean is_anonymous;
        private UserPageVO.UserVO user_info;
    
    
        public Boolean isIs_anonymous() {
            return is_anonymous;
        }
    
        public void setIs_anonymous(Boolean is_anonymous) {
            this.is_anonymous = is_anonymous;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class TeacherCourseVO {
        private Integer id;
        private String name;
        private String comment;
        private String avg_credit;
    }
    
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class CourseSearchVO{
        private Integer id;
        private String name;
        private BigDecimal avg_credit;
        private Long count_student;
        private String college;
    }
    
    
    
    
}

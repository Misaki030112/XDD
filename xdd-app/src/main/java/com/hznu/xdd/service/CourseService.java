package com.hznu.xdd.service;

import com.hznu.xdd.domain.VO.CourseDetailVO;
import com.hznu.xdd.domain.VO.CoursePageVo;

/**
 * 选课业务接口
 */
public interface CourseService {
    
//    //新建一条选课记录
//    private boolean addCourseRecord();

    CoursePageVo getTeacherAllCourse(Integer teacher_id);

    CoursePageVo getCommentList(Integer page, Integer offset, Integer course_id);

    CourseDetailVO getCourseDetail(Integer course_id);

    CoursePageVo getCourseAllTeacher(Integer course_id);
}

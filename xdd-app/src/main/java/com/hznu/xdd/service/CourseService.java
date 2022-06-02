package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.CourseCommentDto;
import com.hznu.xdd.domain.Dto.CourseDto;
import com.hznu.xdd.domain.VO.CoursePageVo;

/**
 * 选课业务接口
 */
public interface CourseService {
    
    boolean  addCourse(CourseDto courseDto);
    
    boolean commentCourse(CourseCommentDto courseCommentDto);
    
    CoursePageVo searchCourse(CourseDto.CourseSearchDto courseSearchDto);
    
    CoursePageVo getTeacherAllCourse(Integer teacher_id);

    CoursePageVo getCommentList(Integer page, Integer offset, Integer course_id);

    CoursePageVo.CourseDetailVO getCourseDetail(Integer course_id);

    CoursePageVo getCourseAllTeacher(Integer course_id);
    
    
    
}

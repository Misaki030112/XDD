package com.hznu.xdd.controller;


import com.hznu.xdd.base.StatusCode;
import com.hznu.xdd.domain.Result;
import com.hznu.xdd.domain.VO.CourseDetailVO;
import com.hznu.xdd.domain.VO.CoursePageVo;
import com.hznu.xdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(rollbackFor = Exception.class)
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/get/course/course",produces = {"application/json;charset=UTF-8"})
    public Result getTeacherAllCourse(@RequestParam("teacher_id") Integer teacher_id){
        CoursePageVo allCourse = courseService.getTeacherAllCourse(teacher_id);
        return Result.ok(allCourse,"获取成功");
    }

    @GetMapping(value = "/get/course/comment",produces = {"application/json;charset=UTF-8"})
    public Result getCommentList(@RequestParam("course_id") Integer course_id,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("offset") Integer offset){
        CoursePageVo commentList = courseService.getCommentList(page, offset, course_id);
        return Result.ok(commentList,"获取成功");
    }

    @GetMapping(value = "/get/course/detail",produces = {"application/json;charset=UTF-8"})
    public Result getCourseDetail(@RequestParam("course_id") Integer course_id){
        CourseDetailVO courseDetail = courseService.getCourseDetail(course_id);
        if (courseDetail != null){
            return Result.ok(courseDetail,"获取成功");
        }else{
            return new Result(StatusCode.NO_DATA);
        }
    }

    @GetMapping(value = "/get/course/teacher",produces = {"application/json;charset=UTF-8"})
    public Result getCourseAllTeacher(@RequestParam("course_id") Integer course_id){
        CoursePageVo courseAllTeacher = courseService.getCourseAllTeacher(course_id);
        return Result.ok(courseAllTeacher,"获取成功");
    }
}
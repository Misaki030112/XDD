package com.hznu.xdd.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hznu.xdd.dao.*;
import com.hznu.xdd.domain.VO.*;
import com.hznu.xdd.domain.pojoExam.*;
import com.hznu.xdd.dao.collegeDOMapper;
import com.hznu.xdd.domain.pojoExam.courseDOExample.Criteria;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    courseDOMapper courseDOMapper;

    @Autowired
    courseCommentDOMapper courseCommentMapper;

    @Autowired
    teacherDOMapper teacherDOMapper;

    @Autowired
    collegeDOMapper collegeDOMapper;

    @Autowired
    schoolDOMapper schoolDOMapper;

    @Autowired
    UserDOMapper userDOMapper;

    @Override
    public CoursePageVo getTeacherAllCourse(Integer teacher_id) {
        CoursePageVo coursePageVo = new CoursePageVo();
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        criteria.andTeachers_idLike("%" + teacher_id +"%");
        long count = courseDOMapper.countByExample(courseDOExample);
        coursePageVo.setTotal(count);
        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);
        ArrayList<TeacherCourseVO> teacherCourseVOS = new ArrayList<>();
        for (courseDO courseDO : courseDOS) {
            TeacherCourseVO teacherCourseVO = new TeacherCourseVO();
            teacherCourseVO.setId(teacher_id);
            teacherCourseVO.setName(courseDO.getName());
            courseCommentDOExample courseCommentDOExample = new courseCommentDOExample();
            com.hznu.xdd.domain.pojoExam.courseCommentDOExample.Criteria courseCommentDOExampleCriteria = courseCommentDOExample.createCriteria();
            courseCommentDOExampleCriteria.andCourse_idEqualTo(courseDO.getId()).andTeacher_idEqualTo(teacher_id);
            courseCommentDO courseCommentDO = new courseCommentDO().setCourse_id(courseDO.getId()).setTeacher_id(teacher_id);
            BigDecimal averageComment = courseCommentMapper.getAverageComment(courseCommentDO);
            BigDecimal averageCredit = courseCommentMapper.getAverageCredit(courseCommentDO);
            long l = courseCommentMapper.countByExample(courseCommentDOExample);
            double value_comment = averageComment.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double value_credit = averageCredit.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            teacherCourseVO.setComment(value_comment + "/" + l);
            teacherCourseVO.setAvg_credit(value_credit + "/" + l);
            teacherCourseVOS.add(teacherCourseVO);
        }
        coursePageVo.setList(teacherCourseVOS);
        return coursePageVo;
    }

    @Override
    public CoursePageVo getCommentList(Integer page, Integer offset, Integer course_id) {
        CoursePageVo coursePageVo = new CoursePageVo();
        courseCommentDOExample courseCommentDOExample = new courseCommentDOExample();
        com.hznu.xdd.domain.pojoExam.courseCommentDOExample.Criteria criteria = courseCommentDOExample.createCriteria();
        criteria.andCourse_idEqualTo(course_id);
        long l = courseCommentMapper.countByExample(courseCommentDOExample);
        coursePageVo.setTotal(l);
        courseCommentDOExample.setOrderByClause("create_time desc");
        courseCommentDOExample.page(page,offset);
        ArrayList<CourseCommentVO> courseCommentVOS = new ArrayList<>();
        List<courseCommentDO> courseCommentDOS = courseCommentMapper.selectByExample(courseCommentDOExample);
        for (courseCommentDO courseCommentDO : courseCommentDOS) {
            CourseCommentVO courseCommentVO = new CourseCommentVO();
            courseCommentVO.setContent(courseCommentDO.getContent())
                    .setId(courseCommentDO.getId())
                    .setTime(courseCommentDO.getCreate_time())
                    .setIs_anonymous(courseCommentDO.getIs_anonymous());
            if (!courseCommentDO.getIs_anonymous()){
                UserDO userDO = userDOMapper.selectByPrimaryKey(courseCommentDO.getId());
                courseCommentVO.setUser_info(new UserVO().setId(userDO.getId()).setNickname(userDO.getNickname()).setGender(userDO.getGender()));
            }
            courseCommentVOS.add(courseCommentVO);
        }
        coursePageVo.setList(courseCommentVOS);
        return coursePageVo;
    }

    @Override
    public CourseDetailVO getCourseDetail(Integer course_id) {
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        criteria.andIdEqualTo(course_id);
        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);
        if (!courseDOS.isEmpty()){
            CourseDetailVO courseDetailVO = new CourseDetailVO();
            courseDO courseDO = courseDOS.get(0);
            courseDetailVO.setId(courseDO.getId()).setName(courseDO.getName());
            collegeDOExample collegeDOExample = new collegeDOExample();
            com.hznu.xdd.domain.pojoExam.collegeDOExample.Criteria criteria1 = collegeDOExample.createCriteria();
            criteria1.andIdEqualTo(courseDO.getCollege_id());
            List<collegeDO> collegeDOS = collegeDOMapper.selectByExample(collegeDOExample);
            courseDetailVO.setCollege(collegeDOS.isEmpty() ? null : collegeDOS.get(0).getName());
            schoolDOExample schoolDOExample = new schoolDOExample();
            com.hznu.xdd.domain.pojoExam.schoolDOExample.Criteria criteria2 = schoolDOExample.createCriteria();
            criteria2.andIdEqualTo(courseDO.getSchool_id());
            List<schoolDO> schoolDOS = schoolDOMapper.selectByExample(schoolDOExample);
            courseDetailVO.setSchool(schoolDOS.isEmpty() ? null : schoolDOS.get(0).getName());
            return courseDetailVO;
        }else{
            return new CourseDetailVO();
        }
    }

    @Override
    public CoursePageVo getCourseAllTeacher(Integer course_id) {
        CoursePageVo coursePageVo = new CoursePageVo();
        courseCommentDO courseCommentDO = new courseCommentDO().setCourse_id(course_id);
        List<Integer> diffCourse = courseCommentMapper.getDiffCourse(courseCommentDO);
        coursePageVo.setTotal((long) diffCourse.size());
        ArrayList<TeacherCourseVO> teacherCourseVOS = new ArrayList<>();
        for (Integer integer : diffCourse) {
            TeacherCourseVO teacherCourseVO = new TeacherCourseVO();
            teacherDOExample teacherDOExample = new teacherDOExample();
            com.hznu.xdd.domain.pojoExam.teacherDOExample.Criteria criteria = teacherDOExample.createCriteria();
            criteria.andIdEqualTo(integer);
            List<teacherDO> teacherDOS = teacherDOMapper.selectByExample(teacherDOExample);
            if (!teacherDOS.isEmpty()){
                teacherCourseVO.setName(teacherDOS.get(0).getName());
                courseCommentDO commentDO = new courseCommentDO();
                commentDO.setCourse_id(course_id).setTeacher_id(teacherDOS.get(0).getId());
                BigDecimal averageComment = courseCommentMapper.getAverageComment(commentDO);
                BigDecimal averageCredit = courseCommentMapper.getAverageCredit(commentDO);
                double value_comment = averageComment.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double value_credit = averageCredit.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                courseCommentDOExample courseCommentDOExample = new courseCommentDOExample();
                com.hznu.xdd.domain.pojoExam.courseCommentDOExample.Criteria criteria1 = courseCommentDOExample.createCriteria();
                criteria1.andTeacher_idEqualTo(teacherDOS.get(0).getId()).andCourse_idEqualTo(course_id);
                long l = courseCommentMapper.countByExample(courseCommentDOExample);
                teacherCourseVO.setComment(value_comment + "/" + l);
                teacherCourseVO.setAvg_credit(value_credit + "/" + l);
                teacherCourseVOS.add(teacherCourseVO);
            }
        coursePageVo.setList(teacherCourseVOS);
        }
        return coursePageVo;
    }
}

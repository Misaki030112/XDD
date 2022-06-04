package com.hznu.xdd.service.impl;

import com.hznu.xdd.dao.*;
import com.hznu.xdd.domain.Dto.CourseCommentDto;
import com.hznu.xdd.domain.Dto.CourseDto;
import com.hznu.xdd.domain.VO.*;
import com.hznu.xdd.domain.pojoExam.*;
import com.hznu.xdd.domain.pojoExam.courseDOExample.Criteria;
import com.hznu.xdd.pojo.*;
import com.hznu.xdd.service.CourseService;
import com.hznu.xdd.util.HotSearchUtil;
import com.hznu.xdd.util.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    
   private final courseDOMapper courseDOMapper;
   private final courseCommentDOMapper courseCommentMapper;
   private final teacherDOMapper teacherDOMapper;
   private final collegeDOMapper collegeDOMapper;
   private final schoolDOMapper schoolDOMapper;
   private final UserDOMapper userDOMapper;
   private final UserInfoUtil userInfoUtil;
   private final campusDOMapper campusDOMapper;
   private final HotSearchUtil hotSearchUtil;
   
   private static final String HOT_COURSE_SEARCH="hot_courses_search";
   
    public CourseServiceImpl(com.hznu.xdd.dao.courseDOMapper courseDOMapper, courseCommentDOMapper courseCommentMapper, com.hznu.xdd.dao.teacherDOMapper teacherDOMapper, com.hznu.xdd.dao.collegeDOMapper collegeDOMapper,
                             com.hznu.xdd.dao.schoolDOMapper schoolDOMapper, UserDOMapper userDOMapper,UserInfoUtil userInfoUtil, campusDOMapper campusDOMapper,HotSearchUtil hotSearchUtil) {
        this.courseDOMapper = courseDOMapper;
        this.courseCommentMapper = courseCommentMapper;
        this.teacherDOMapper = teacherDOMapper;
        this.collegeDOMapper = collegeDOMapper;
        this.schoolDOMapper = schoolDOMapper;
        this.userDOMapper = userDOMapper;
        this.userInfoUtil=userInfoUtil;
        this.campusDOMapper=campusDOMapper;
        this.hotSearchUtil=hotSearchUtil;
    }

    @Override
    public boolean addCourse(CourseDto courseDto) {
        teacherDOExample example1 = new teacherDOExample();
        teacherDOExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andCollege_idEqualTo(courseDto.getCollege_id());
        criteria1.andNameEqualTo(courseDto.getTeacher());
        criteria1.andIs_deleteEqualTo(false);
        List<teacherDO> teacherDOList = teacherDOMapper.selectByExample(example1);
        collegeDO collegeDO = collegeDOMapper.selectByPrimaryKey(courseDto.getCollege_id());
        if(collegeDO==null){
            log.error("没有与此collegeId所对应的schoolID");
            throw new RuntimeException("collegeId 参数不正确!");
        }
        teacherDO teacher=null;
        if(teacherDOList.size()==0){
            teacher=new teacherDO();
            teacher.setName(courseDto.getTeacher());
            teacher.setCollege_id(courseDto.getCollege_id());
            teacher.setSchool_id(collegeDO.getSchool_id());
            teacherDOMapper.insertSelective(teacher);
            teacher=teacherDOMapper.selectByExample(example1).get(0);
        }else{
            teacher=teacherDOList.get(0);
        }
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        criteria.andNameEqualTo(courseDto.getCourse()).andCollege_idEqualTo(courseDto.getCollege_id()).andIs_deleteEqualTo(false).andSchool_idEqualTo(collegeDO.getSchool_id());
        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);
        if(courseDOS.size()==0){
            courseDO courseDO = new courseDO();
            courseDO.setCollege_id(courseDto.getCollege_id());
            courseDO.setTeachers_id(String.valueOf(teacher.getId()));
            courseDO.setName(courseDto.getCourse());
            courseDO.setSchool_id(collegeDO.getSchool_id());
            //campusId无法确定
            int i = courseDOMapper.insertSelective(courseDO);
            return i>0;
        }else{
            courseDO courseDO = courseDOS.get(0);
            final String teachId= String.valueOf(teacher.getId());
            boolean flag = Arrays.stream(courseDO.getTeachers_id().split(",")).anyMatch((a) -> a.equals(teachId));
            if(flag) return false;
            else{
                courseDO.setTeachers_id(courseDO.getTeachers_id()+","+teachId);
                courseDO.setUpdate_time(new Date());
                int i = courseDOMapper.updateByPrimaryKeySelective(courseDO);
                return i>0;
            }
        }
    }

    @Override
    public boolean commentCourse(CourseCommentDto courseCommentDto) {
        courseCommentDO commentDO = new courseCommentDO(courseCommentDto);
        commentDO.setUser_id(userInfoUtil.getUserId());
        int i = courseCommentMapper.insertSelective(commentDO);
        return i>0;
    }

    @Override
    public CoursePageVo getHotKeys() {
        List<String> hotSearch = new ArrayList<>();
        hotSearchUtil.getHotByRedis(HOT_COURSE_SEARCH, 20).forEach(h->hotSearch.add((String) h));
        CoursePageVo coursePageVo = new CoursePageVo();
        coursePageVo.setTotal((long) hotSearch.size());
        coursePageVo.setList(hotSearch);
        return coursePageVo;
    }


    /**
     * 注意热搜存活天数  7天
     * @param courseSearchDto
     * @return
     */
    @Override
    public CoursePageVo searchCourse(CourseDto.CourseSearchDto courseSearchDto) {
        CoursePageVo coursePageVo = new CoursePageVo();
        Integer college_id=null;
        Integer campus_id=null;
        Integer school_id=null;
        Integer teacher_id=null;
        if(!courseSearchDto.getTeacher().equals("")){
            teacher_id=teacherDOMapper.selectTeacherIdByTeacherNameAndSchoolNameAndCollegeName(courseSearchDto.getTeacher(),courseSearchDto.getSchool(),courseSearchDto.getCollege());
            if(teacher_id==null){
                coursePageVo.setTotal(0L);
                coursePageVo.setList(new ArrayList<>());
                return coursePageVo;
            }
        }
        if(!courseSearchDto.getCampus().equals("")){
            campus_id=campusDOMapper.selectByCampusNameAndSchoolName(courseSearchDto.getCampus(),courseSearchDto.getSchool());
            if(campus_id==null){
                coursePageVo.setTotal(0L);
                coursePageVo.setList(new ArrayList<>());
                return coursePageVo;
            }
        }
        if(!courseSearchDto.getSchool().equals("")){
            school_id=schoolDOMapper.selectIdBySchoolName(courseSearchDto.getSchool());
            if(school_id==null){
                coursePageVo.setTotal(0L);
                coursePageVo.setList(new ArrayList<>());
                return coursePageVo;
            }
        }
        if(!courseSearchDto.getCollege().equals("")){
            college_id=collegeDOMapper.selectCollegeIdAndSchoolName(courseSearchDto.getCollege(), courseSearchDto.getSchool());
            if(college_id==null){
                coursePageVo.setTotal(0L);
                coursePageVo.setList(new ArrayList<>());
                return coursePageVo;
            }
        }
        
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        if(teacher_id!=null) {
            criteria.andTeachers_idLike("%"+teacher_id+"%");
        }
        if(campus_id!=null){
            criteria.andCampus_idEqualTo(campus_id);
        }
        if(college_id!=null){
            criteria.andCollege_idEqualTo(college_id);
        }
        if(school_id!=null){
            criteria.andSchool_idEqualTo(school_id);
        }
        StringBuilder courseIdS = new StringBuilder();
        if(!courseSearchDto.getKey().equals("")){
            courseIdS.append(courseSearchDto.getKey()+":");
            criteria.andNameLike("%"+courseSearchDto.getKey()+"%");
        }
        courseDOExample.setOrderByClause(courseSearchDto.getOrder_by());
        courseDOExample.page(courseSearchDto.getPage(),courseSearchDto.getOffset());

        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);


        List<CoursePageVo.CourseSearchVO> searchVOS = new ArrayList<>();
        
        courseDOS.forEach(c->{
            CoursePageVo.CourseSearchVO searchVO = new CoursePageVo.CourseSearchVO();
            searchVO.setCollege(collegeDOMapper.selectByPrimaryKey(c.getCollege_id()).getName());
            searchVO.setName(c.getName());
            searchVO.setAvg_credit(courseCommentMapper.getAverageCredit(new courseCommentDO(c.getId())));
            searchVO.setId(c.getId());
            searchVO.setCount_student(courseCommentMapper.countByCourseId(c.getId()));
            searchVOS.add(searchVO);
            courseIdS.append(c.getId()+",");
        });
        courseIdS.deleteCharAt(courseIdS.length() - 1);

        /**
         * 热搜存储7天的时间
         */
        if(!courseSearchDto.getKey().equals("")){
            hotSearchUtil.saveToRedis(HOT_COURSE_SEARCH,courseIdS.toString(),7);
        }
        
        coursePageVo.setTotal((long) courseDOS.size());
        coursePageVo.setList(searchVOS);
        
        

        return coursePageVo;
    }
    
    
    
    


    @Override
    public CoursePageVo getTeacherAllCourse(Integer teacher_id) {
        CoursePageVo coursePageVo = new CoursePageVo();
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        criteria.andTeachers_idLike("%" + teacher_id +"%");
        long count = courseDOMapper.countByExample(courseDOExample);
        coursePageVo.setTotal(count);
        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);
        ArrayList<CoursePageVo.TeacherCourseVO> teacherCourseVOS = new ArrayList<>();
        for (courseDO courseDO : courseDOS) {
            CoursePageVo.TeacherCourseVO teacherCourseVO = new CoursePageVo.TeacherCourseVO();
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
        ArrayList<CoursePageVo.CourseCommentVO> courseCommentVOS = new ArrayList<>();
        List<courseCommentDO> courseCommentDOS = courseCommentMapper.selectByExample(courseCommentDOExample);
        for (courseCommentDO courseCommentDO : courseCommentDOS) {
            CoursePageVo.CourseCommentVO courseCommentVO = new CoursePageVo.CourseCommentVO();
            courseCommentVO.setContent(courseCommentDO.getContent())
                    .setId(courseCommentDO.getId())
                    .setTime(courseCommentDO.getCreate_time())
                    .setIs_anonymous(courseCommentDO.getIs_anonymous());
            if (!courseCommentDO.getIs_anonymous()){
                UserDO userDO = userDOMapper.selectByPrimaryKey(courseCommentDO.getUser_id());
                courseCommentVO.setUser_info(new UserPageVO.UserVO().setId(userDO.getId()).setNickname(userDO.getNickname()).setGender(userDO.getGender()));
            }
            courseCommentVOS.add(courseCommentVO);
        }
        coursePageVo.setList(courseCommentVOS);
        return coursePageVo;
    }

    @Override
    public CoursePageVo.CourseDetailVO getCourseDetail(Integer course_id) {
        courseDOExample courseDOExample = new courseDOExample();
        Criteria criteria = courseDOExample.createCriteria();
        criteria.andIdEqualTo(course_id);
        List<courseDO> courseDOS = courseDOMapper.selectByExample(courseDOExample);
        if (!courseDOS.isEmpty()){
            CoursePageVo.CourseDetailVO courseDetailVO = new CoursePageVo.CourseDetailVO();
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
            return new CoursePageVo.CourseDetailVO();
        }
    }

    @Override
    public CoursePageVo getCourseAllTeacher(Integer course_id) {
        CoursePageVo coursePageVo = new CoursePageVo();
        courseCommentDO courseCommentDO = new courseCommentDO().setCourse_id(course_id);
        List<Integer> diffCourse = courseCommentMapper.getDiffCourse(courseCommentDO);
        coursePageVo.setTotal((long) diffCourse.size());
        ArrayList<CoursePageVo.TeacherCourseVO> teacherCourseVOS = new ArrayList<>();
        for (Integer integer : diffCourse) {
            CoursePageVo.TeacherCourseVO teacherCourseVO = new CoursePageVo.TeacherCourseVO();
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

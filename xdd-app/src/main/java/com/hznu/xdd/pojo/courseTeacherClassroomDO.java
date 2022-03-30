package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class courseTeacherClassroomDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer course_id;

    private Integer teacher_id;

    private Integer classroom_id;

    private String section;

    private Byte term;

    private String year;

    private String week;

    private static final long serialVersionUID = 1L;
}
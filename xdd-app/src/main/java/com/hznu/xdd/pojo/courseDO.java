package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class courseDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String name;

    private Double credit;

    private Boolean is_online;

    private Integer school_id;

    private Integer college_id;

    private Integer campus_id;

    private String teachers_id;

    private Integer official_id;

    private static final long serialVersionUID = 1L;
}
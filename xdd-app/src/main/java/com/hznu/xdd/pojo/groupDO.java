package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class groupDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private String title;

    private String content;

    private String exposure;

    private String is_online;

    private String collect;

    private String comment;

    private String params;

    private String images;

    private String video;

    private String label;

    private String topic;

    private String location;

    private String is_open;

    private static final long serialVersionUID = 1L;
}
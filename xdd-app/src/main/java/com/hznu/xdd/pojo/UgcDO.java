package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UgcDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private String title;

    private String content;

    private String images;

    private String video;

    private String label;

    private String params;

    private Integer vote;

    private Integer exposure;

    private Integer collect;

    private Integer comment;

    private Boolean is_online;

    private Boolean anonymous;

    private String location;

    private String topic;

    private static final long serialVersionUID = 1L;
}
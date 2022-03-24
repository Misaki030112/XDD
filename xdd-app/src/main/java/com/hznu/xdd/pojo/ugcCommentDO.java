package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ugcCommentDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String relation_type;

    private Integer relation_id;

    private Integer parent_id;

    private String parent_type;

    private Integer user_id;

    private Boolean is_online;

    private Integer vote;

    private Integer reply_num;

    private String content;

    private String images;

    private String video;

    private Boolean anonymous;

    private static final long serialVersionUID = 1L;
}
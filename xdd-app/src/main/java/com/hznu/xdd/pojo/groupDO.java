package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class groupDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private String title;

    private String content;

    private Integer exposure;

    private Boolean is_online;

    private Integer collect;

    private Integer comment;

    private String params;

    private String images;

    private String video;

    private String label;

    private String topic;

    private String location;

    private Boolean is_open;

    private static final long serialVersionUID = 1L;
}
package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class verify_methodDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String title;

    private String content;

    private Boolean is_online;

    private String page_url;

    private static final long serialVersionUID = 1L;
}
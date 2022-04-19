package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class searchLogDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String content;

    private Integer user_id;

    private static final long serialVersionUID = 1L;
}
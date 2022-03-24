package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class collectLogDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private String collect_type;

    private Integer collect_to_id;

    private static final long serialVersionUID = 1L;
}
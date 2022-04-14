package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class groupJoinLogDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private Integer user_id;

    private Integer group_id;

    private Boolean is_cancel;

    private String cancel_type;

    private static final long serialVersionUID = 1L;
}
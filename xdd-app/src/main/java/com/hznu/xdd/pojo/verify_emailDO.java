package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class verify_emailDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String email;

    private String session_key;

    private Date expire_time;

    private static final long serialVersionUID = 1L;
}
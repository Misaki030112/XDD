package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class verify_imageDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String image;

    private Integer user_id;

    private static final long serialVersionUID = 1L;
}
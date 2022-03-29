package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class topicDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String title;

    private String sub_title;

    private String label;

    private String image;

    private static final long serialVersionUID = 1L;
}
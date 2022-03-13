package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class NewDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String desc;

    private String name;

    private String url;

    private String labels;

    private String images;

    private String cover;

    private Integer creater;

    private String type;

    private static final long serialVersionUID = 1L;
}
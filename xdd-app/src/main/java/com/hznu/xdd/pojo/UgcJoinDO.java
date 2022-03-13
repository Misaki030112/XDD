package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UgcJoinDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer ugcId;

    private String ugcType;

    private Integer userId;

    private Boolean join;

    private static final long serialVersionUID = 1L;
}
package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class FansLogDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private Integer fansId;

    private Boolean status;

    private static final long serialVersionUID = 1L;
}
package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CollectLogDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private String relationType;

    private Integer relationId;

    private Boolean isOnline;

    private static final long serialVersionUID = 1L;
}
package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class OperateLogDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String executor;

    private String action;

    private String params;

    private String router;

    private String remoteIp;

    private static final long serialVersionUID = 1L;
}
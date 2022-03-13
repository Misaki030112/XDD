package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class UserReportDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private Integer reportedUserId;

    private String content;

    private String imgs;

    private String labels;

    private Boolean online;

    private String status;

    private static final long serialVersionUID = 1L;
}
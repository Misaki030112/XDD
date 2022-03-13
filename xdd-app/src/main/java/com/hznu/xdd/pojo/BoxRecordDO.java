package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class BoxRecordDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer userId;

    private Integer boxId;

    private Boolean isOnline;

    private static final long serialVersionUID = 1L;
}
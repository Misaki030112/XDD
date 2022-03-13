package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ConfigCenterDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private String configKey;

    private String configValue;

    private Integer executor;

    private String extra;

    private static final long serialVersionUID = 1L;
}
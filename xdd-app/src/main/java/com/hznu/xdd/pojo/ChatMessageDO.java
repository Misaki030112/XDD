package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ChatMessageDO implements Serializable {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    private Integer fromId;

    private Integer toId;

    private String message;

    private String image;

    private String sendTime;

    private static final long serialVersionUID = 1L;
}
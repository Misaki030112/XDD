package com.hznu.xdd.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class reportDO implements Serializable {
    private Integer id;

    private Date create_time;

    private Date update_time;

    private Boolean is_delete;

    private String content;

    private String status;

    private Integer report_user_id;

    private Integer create_user_id;

    private Integer report_ugc_id;

    private String report_to_type;

    private String params;

    private static final long serialVersionUID = 1L;
}
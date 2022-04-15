package com.hznu.xdd.domain.VO;

import com.alibaba.fastjson.annotation.JSONField;
import com.hznu.xdd.domain.Dto.attachmentDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class UGCVO implements Serializable {
    private Integer id;

    private List<String> label;

    private String topic;

    private String title;

    private String content;

    private Date create_time;

    private Date update_time;

    private List<attachmentDto> attachment_list;

    private Integer vote;

    private Integer comment;

    private UserVO user;

    @JSONField(name = "is_vote")
    private boolean is_vote;

    @JSONField(name = "is_collect")
    private boolean is_collect;

    private Double score;

    public boolean isIs_vote() {
        return is_vote;
    }

    public void setIs_vote(boolean is_vote) {
        this.is_vote = is_vote;
    }

    public boolean isIs_collect() {
        return is_collect;
    }

    public void setIs_collect(boolean is_collect) {
        this.is_collect = is_collect;
    }
}

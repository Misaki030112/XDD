package com.hznu.xdd.domain.VO;

import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.pojo.UserDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UGCVO {
    private Integer id;

    private List<String> label;

    private String topic;

    private String title;

    private String content;

    private String create_time;

    private String update_time;

    private List<attachmentDto> attachmentList;

    private Integer vote;

    private Integer comment;

    private UserVO user;

    private boolean is_vote;

    private boolean is_collect;
}

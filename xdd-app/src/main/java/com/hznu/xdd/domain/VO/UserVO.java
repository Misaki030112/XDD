package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserVO implements Serializable {
    private Integer id;

    private String avatar;

    private String nickname;

    private Short gender;

    private String role;

}

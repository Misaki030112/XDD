package com.hznu.xdd.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeiXinOpenID implements Serializable {
    private String openId;
    private String sessionKey;
    private JSONObject token;
}

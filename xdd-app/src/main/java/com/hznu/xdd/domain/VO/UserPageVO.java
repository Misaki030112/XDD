package com.hznu.xdd.domain.VO;

import lombok.Data;

import java.util.List;

@Data
public class UserPageVO {
    private List<? extends Object> list;

    private Long total;
 }
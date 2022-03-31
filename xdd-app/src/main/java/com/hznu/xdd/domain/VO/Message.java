package com.hznu.xdd.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class Message implements Serializable {
    private String message;
}

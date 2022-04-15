package com.hznu.xdd.domain.VO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UgcPageVO implements Serializable {
    private List< ? extends Object> list;

    private Integer total;


}

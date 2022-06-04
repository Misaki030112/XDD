package com.hznu.xdd.domain.VO;

import java.io.Serializable;
import java.util.List;

public class BasePageVO implements Serializable {
    private List<?> list;

    private Long total;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

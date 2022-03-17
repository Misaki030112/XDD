package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.pojo.UgcDO;

import java.util.List;

public interface UGCService {
    List<UgcDO> listAllUGC();

    UgcDO findById(Integer id);

    Integer createUGC(UGCDto UGCDto);

    Integer deleteUGC(Integer id);

    Integer updateUGC(UGCDto UGCDto);

    List<UGCVO> listPublishUGCById(Integer id,String key,String label, String topic,String order_by);
}

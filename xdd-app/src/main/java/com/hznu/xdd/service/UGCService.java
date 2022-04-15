package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.ListVO;
import com.hznu.xdd.domain.VO.UGCVO;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.labelDO;
import com.hznu.xdd.pojo.topicDO;

import java.util.List;

public interface UGCService {
    List<UgcDO> listAllUGC();

    UgcDO findById(Integer id);

    Integer createUGC(UGCDto UGCDto);

    Integer deleteUGC(Integer id);

    Integer updateUGC(UGCDto UGCDto);

    ListVO listPublishUGCById(Integer user_id, String key, String label, String topic, String order_by, Integer page, Integer offset, Integer fun);

    ListVO getHotUGC(Integer page,Integer offset);

    Integer addComment(String content,Integer parent_id,String to_type,Integer to_id,Integer user_id);

    Integer voteUGC(Integer to_id,boolean status,Integer user_id);

    Integer collectUGC(Integer to_id,boolean status,Integer user_id);

    List<CommentVO> getCommentById(Integer id);

    ListVO getTopic(Integer page, Integer offset);

    ListVO getLabel(Integer page, Integer offset);

}

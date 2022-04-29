package com.hznu.xdd.service;

import com.hznu.xdd.domain.Dto.UGCDto;
import com.hznu.xdd.domain.VO.CommentVO;
import com.hznu.xdd.domain.VO.UgcPageVO;
import com.hznu.xdd.pojo.UgcDO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UGCService {
    List<UgcDO> listAllUGC();

    UgcDO findById(Integer id);

    Integer createUGC(UGCDto UGCDto);

    Integer deleteUGC(Integer id);

    Integer updateUGC(UGCDto UGCDto);

    UgcPageVO listPublishUGCById(Integer user_id, String key, String label, String topic, String order_by, Integer page, Integer offset, Integer fun,Integer uid);

    UgcPageVO getHotUGC(Integer page, Integer offset,Integer user_id);

    Integer addComment(String content, Integer parent_id, String to_type, Integer to_id, Integer user_id);

    Integer voteUGC(Integer to_id,boolean status,Integer user_id);

    Integer collectUGC(Integer to_id,boolean status,Integer user_id);

    List<CommentVO> getCommentById(Integer id);

    UgcPageVO getTopic(Integer page, Integer offset);

    UgcPageVO getLabel(Integer page, Integer offset);

    Integer saveSearch(String key,Integer user_id);

}

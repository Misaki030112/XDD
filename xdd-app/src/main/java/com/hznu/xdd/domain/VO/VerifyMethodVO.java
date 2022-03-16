package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.verify_methodDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class VerifyMethodVO {
    private Integer id;

    private String title;

    private String content;


    public VerifyMethodVO(verify_methodDO v) {
        this.id=v.getId();
        this.content=v.getContent();
        this.title=v.getTitle();
    }
}

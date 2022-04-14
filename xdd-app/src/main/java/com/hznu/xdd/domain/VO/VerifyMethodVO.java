package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.verify_methodDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class VerifyMethodVO implements Serializable {
    private Integer id;

    private String title;

    private String content;

    private String page_url;


    public VerifyMethodVO(verify_methodDO v) {
        this.id=v.getId();
        this.content=v.getContent();
        this.title=v.getTitle();
        this.page_url=v.getPage_url();
    }
}

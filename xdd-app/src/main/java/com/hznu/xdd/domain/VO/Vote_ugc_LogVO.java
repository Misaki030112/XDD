package com.hznu.xdd.domain.VO;

import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
public class Vote_ugc_LogVO implements Serializable {

    private Integer id;
    private Date create_time;
    private user_info user_info;
    private String content="点赞了你的评论/帖子";
    private ugc_info to;




    @Data
    class user_info implements Serializable{
        private Integer id;
        private String nickname;
        private String avatar;
        private String gender;
        public user_info(UserDO user){
            this.id=user.getId();
            this.nickname=user.getNickname();
            this.avatar=user.getAvatar();
            if(user.getGender()==null||user.getGender()==2){
                this.gender="未知";
            }
            else if(user.getGender()==0){
                this.gender="男";
            }else if(user.getGender()==1){
                this.gender="女";
            }
        }
    }

    @Data
    class ugc_info implements Serializable{
        private Integer id;
        private String content;
        private Date create_time;
        private String cover;
        public ugc_info(UgcDO ugc){
            this.id=ugc.getId();
            this.content=ugc.getContent();
            this.create_time=ugc.getCreate_time();
            this.cover=ugc.getImages().split(",")[0];
        }
    }

    public Vote_ugc_LogVO setUser_info(UserDO user) {
        this.user_info= new user_info(user);
        return this;
    }

    public Vote_ugc_LogVO setTo(UgcDO ugc) {
        this.to = new ugc_info(ugc);
        return this;
    }
}

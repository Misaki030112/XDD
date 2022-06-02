package com.hznu.xdd.domain.VO;

import com.alibaba.fastjson.annotation.JSONField;
import com.hznu.xdd.domain.Dto.attachmentDto;
import com.hznu.xdd.domain.Dto.locationDto;
import com.hznu.xdd.pojo.UgcDO;
import com.hznu.xdd.pojo.UserDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class UgcPageVO  extends BasePageVO{

    @Data
    @Accessors(chain = true)
    public static class UGCVO implements Serializable {
        private Integer id;
    
        private List<String> label;
    
        private String topic;
    
        private String title;
    
        private String content;
    
        private Date create_time;
    
        private Date update_time;
    
        private List<attachmentDto> attachment_list;
    
        private Integer vote;
    
        private Integer comment;
    
        private UserPageVO.UserVO user_info;
    
        private locationDto location;
    
        @JSONField(name = "is_vote")
        private boolean is_vote;
    
        private boolean is_collect;
    
        private Double score;
    
        public boolean isIs_vote() {
            return is_vote;
        }
    
        public void setIs_vote(boolean is_vote) {
            this.is_vote = is_vote;
        }
    
        public boolean isIs_collect() {
            return is_collect;
        }
    
        public void setIs_collect(boolean is_collect) {
            this.is_collect = is_collect;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class Collect_ugc_VO  implements Serializable {
        private Integer id;
        private Date create_time;
        private Collect_ugc_VO.user_info user_info;
        private String content="收藏了你的评论/帖子";
        private ugc_info to;
    
    
        @Data
        class user_info{
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
        class ugc_info{
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
    
        public Collect_ugc_VO setUser_info(UserDO user) {
            this.user_info= new user_info(user);
            return this;
        }
    
        public Collect_ugc_VO setTo(UgcDO ugc) {
            this.to = new ugc_info(ugc);
            return this;
        }
    }

    @Accessors(chain = true)
    @Data
    public static class Vote_ugc_LogVO implements Serializable {
    
        private Integer id;
        private Date create_time;
        private Vote_ugc_LogVO.user_info user_info;
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
}

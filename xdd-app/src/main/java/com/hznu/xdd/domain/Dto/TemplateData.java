package com.hznu.xdd.domain.Dto;

import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.JsonUtils;

import java.util.HashMap;

public class TemplateData {

    private String touser;
    private String template_id;
    private String url;
    private String topcolor;
    private TemplateObj miniprogram;
    private TemplateItem data;

    public static TemplateData New() {
        return new TemplateData();
    }

    private TemplateData() {
        this.data = new TemplateItem();
        this.miniprogram = new TemplateObj();
    }

    public String getTouser() {
        return touser;
    }

    public TemplateData setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public TemplateData setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TemplateData setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public TemplateData setTopcolor(String topcolor) {
        this.topcolor = topcolor;
        return this;
    }

    public TemplateItem getData() {
        return data;
    }

    public TemplateData add(String key, String value){
        data.put(key, new Item(value));
        return this;
    }

    public TemplateData add2(String key1,String key2, String value1,String value2){
        miniprogram.put(key1, value1);
        miniprogram.put(key2, "/pages/wall/wallDetail?ugc_id=" + value2);
        return this;
    }

    /**
     * 直接转化成jsonString
     * @return {String}
     */
    public String build() {
        return JSON.toJSONString(this);
    }

    public TemplateObj getMiniprogram() {
        return miniprogram;
    }


    public class TemplateItem extends HashMap<String, Item> {

        private static final long serialVersionUID = -3728490424738325020L;

        public TemplateItem() {}

        public TemplateItem(String key, Item item) {
            this.put(key, item);
        }
    }

    public class TemplateObj extends HashMap<String, String> {

        public TemplateObj() {}

        public TemplateObj(String key, String value) {
            this.put(key, value);
        }
    }

    public class Item {
        private Object value;

        public Object getValue() {
            return value;
        }
        public void setValue(Object value) {
            this.value = value;
        }

        public Item(Object value) {
            this.value = value;
        }
    }
}

package com.hznu.xdd.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class questionDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public questionDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public questionDOExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public questionDOExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public questionDOExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(Date value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(Date value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(Date value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<Date> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeEqualTo(Date value) {
            addCriterion("update_time =", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThan(Date value) {
            addCriterion("update_time >", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThan(Date value) {
            addCriterion("update_time <", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIn(List<Date> values) {
            addCriterion("update_time in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIs_deleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "is_delete");
            return (Criteria) this;
        }

        public Criteria andIs_deleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "is_delete");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idIsNull() {
            addCriterion("right_answer_id is null");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idIsNotNull() {
            addCriterion("right_answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idEqualTo(Integer value) {
            addCriterion("right_answer_id =", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idNotEqualTo(Integer value) {
            addCriterion("right_answer_id <>", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idGreaterThan(Integer value) {
            addCriterion("right_answer_id >", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("right_answer_id >=", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idLessThan(Integer value) {
            addCriterion("right_answer_id <", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idLessThanOrEqualTo(Integer value) {
            addCriterion("right_answer_id <=", value, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idIn(List<Integer> values) {
            addCriterion("right_answer_id in", values, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idNotIn(List<Integer> values) {
            addCriterion("right_answer_id not in", values, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idBetween(Integer value1, Integer value2) {
            addCriterion("right_answer_id between", value1, value2, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andRight_answer_idNotBetween(Integer value1, Integer value2) {
            addCriterion("right_answer_id not between", value1, value2, "right_answer_id");
            return (Criteria) this;
        }

        public Criteria andIs_onlineIsNull() {
            addCriterion("is_online is null");
            return (Criteria) this;
        }

        public Criteria andIs_onlineIsNotNull() {
            addCriterion("is_online is not null");
            return (Criteria) this;
        }

        public Criteria andIs_onlineEqualTo(Boolean value) {
            addCriterion("is_online =", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineNotEqualTo(Boolean value) {
            addCriterion("is_online <>", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineGreaterThan(Boolean value) {
            addCriterion("is_online >", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_online >=", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineLessThan(Boolean value) {
            addCriterion("is_online <", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineLessThanOrEqualTo(Boolean value) {
            addCriterion("is_online <=", value, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineIn(List<Boolean> values) {
            addCriterion("is_online in", values, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineNotIn(List<Boolean> values) {
            addCriterion("is_online not in", values, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineBetween(Boolean value1, Boolean value2) {
            addCriterion("is_online between", value1, value2, "is_online");
            return (Criteria) this;
        }

        public Criteria andIs_onlineNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_online not between", value1, value2, "is_online");
            return (Criteria) this;
        }

        public Criteria andSub_titleIsNull() {
            addCriterion("sub_title is null");
            return (Criteria) this;
        }

        public Criteria andSub_titleIsNotNull() {
            addCriterion("sub_title is not null");
            return (Criteria) this;
        }

        public Criteria andSub_titleEqualTo(String value) {
            addCriterion("sub_title =", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleNotEqualTo(String value) {
            addCriterion("sub_title <>", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleGreaterThan(String value) {
            addCriterion("sub_title >", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_title >=", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleLessThan(String value) {
            addCriterion("sub_title <", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleLessThanOrEqualTo(String value) {
            addCriterion("sub_title <=", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleLike(String value) {
            addCriterion("sub_title like", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleNotLike(String value) {
            addCriterion("sub_title not like", value, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleIn(List<String> values) {
            addCriterion("sub_title in", values, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleNotIn(List<String> values) {
            addCriterion("sub_title not in", values, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleBetween(String value1, String value2) {
            addCriterion("sub_title between", value1, value2, "sub_title");
            return (Criteria) this;
        }

        public Criteria andSub_titleNotBetween(String value1, String value2) {
            addCriterion("sub_title not between", value1, value2, "sub_title");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
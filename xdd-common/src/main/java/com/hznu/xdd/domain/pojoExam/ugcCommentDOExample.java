package com.hznu.xdd.domain.pojoExam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ugcCommentDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public ugcCommentDOExample() {
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

    public ugcCommentDOExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public ugcCommentDOExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public ugcCommentDOExample page(Integer page, Integer pageSize) {
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

        public Criteria andParent_idIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParent_idIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParent_idEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idLessThan(Integer value) {
            addCriterion("parent_id <", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parent_id");
            return (Criteria) this;
        }

        public Criteria andParent_idNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parent_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(Integer value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(Integer value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(Integer value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<Integer> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andVoteIsNull() {
            addCriterion("vote is null");
            return (Criteria) this;
        }

        public Criteria andVoteIsNotNull() {
            addCriterion("vote is not null");
            return (Criteria) this;
        }

        public Criteria andVoteEqualTo(Integer value) {
            addCriterion("vote =", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotEqualTo(Integer value) {
            addCriterion("vote <>", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThan(Integer value) {
            addCriterion("vote >", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote >=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThan(Integer value) {
            addCriterion("vote <", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteLessThanOrEqualTo(Integer value) {
            addCriterion("vote <=", value, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteIn(List<Integer> values) {
            addCriterion("vote in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotIn(List<Integer> values) {
            addCriterion("vote not in", values, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteBetween(Integer value1, Integer value2) {
            addCriterion("vote between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andVoteNotBetween(Integer value1, Integer value2) {
            addCriterion("vote not between", value1, value2, "vote");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andUgc_idIsNull() {
            addCriterion("ugc_id is null");
            return (Criteria) this;
        }

        public Criteria andUgc_idIsNotNull() {
            addCriterion("ugc_id is not null");
            return (Criteria) this;
        }

        public Criteria andUgc_idEqualTo(Integer value) {
            addCriterion("ugc_id =", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idNotEqualTo(Integer value) {
            addCriterion("ugc_id <>", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idGreaterThan(Integer value) {
            addCriterion("ugc_id >", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("ugc_id >=", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idLessThan(Integer value) {
            addCriterion("ugc_id <", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idLessThanOrEqualTo(Integer value) {
            addCriterion("ugc_id <=", value, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idIn(List<Integer> values) {
            addCriterion("ugc_id in", values, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idNotIn(List<Integer> values) {
            addCriterion("ugc_id not in", values, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idBetween(Integer value1, Integer value2) {
            addCriterion("ugc_id between", value1, value2, "ugc_id");
            return (Criteria) this;
        }

        public Criteria andUgc_idNotBetween(Integer value1, Integer value2) {
            addCriterion("ugc_id not between", value1, value2, "ugc_id");
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
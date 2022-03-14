package com.hznu.xdd.domain.pojoExam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class reportDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public reportDOExample() {
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

    public reportDOExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public reportDOExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public reportDOExample page(Integer page, Integer pageSize) {
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andReport_user_idIsNull() {
            addCriterion("report_user_id is null");
            return (Criteria) this;
        }

        public Criteria andReport_user_idIsNotNull() {
            addCriterion("report_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andReport_user_idEqualTo(Integer value) {
            addCriterion("report_user_id =", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idNotEqualTo(Integer value) {
            addCriterion("report_user_id <>", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idGreaterThan(Integer value) {
            addCriterion("report_user_id >", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_user_id >=", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idLessThan(Integer value) {
            addCriterion("report_user_id <", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idLessThanOrEqualTo(Integer value) {
            addCriterion("report_user_id <=", value, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idIn(List<Integer> values) {
            addCriterion("report_user_id in", values, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idNotIn(List<Integer> values) {
            addCriterion("report_user_id not in", values, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idBetween(Integer value1, Integer value2) {
            addCriterion("report_user_id between", value1, value2, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_user_idNotBetween(Integer value1, Integer value2) {
            addCriterion("report_user_id not between", value1, value2, "report_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idLessThan(Integer value) {
            addCriterion("create_user_id <", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andCreate_user_idNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "create_user_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idIsNull() {
            addCriterion("report_ugc_id is null");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idIsNotNull() {
            addCriterion("report_ugc_id is not null");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idEqualTo(Integer value) {
            addCriterion("report_ugc_id =", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idNotEqualTo(Integer value) {
            addCriterion("report_ugc_id <>", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idGreaterThan(Integer value) {
            addCriterion("report_ugc_id >", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("report_ugc_id >=", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idLessThan(Integer value) {
            addCriterion("report_ugc_id <", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idLessThanOrEqualTo(Integer value) {
            addCriterion("report_ugc_id <=", value, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idIn(List<Integer> values) {
            addCriterion("report_ugc_id in", values, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idNotIn(List<Integer> values) {
            addCriterion("report_ugc_id not in", values, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idBetween(Integer value1, Integer value2) {
            addCriterion("report_ugc_id between", value1, value2, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_ugc_idNotBetween(Integer value1, Integer value2) {
            addCriterion("report_ugc_id not between", value1, value2, "report_ugc_id");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeIsNull() {
            addCriterion("report_to_type is null");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeIsNotNull() {
            addCriterion("report_to_type is not null");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeEqualTo(String value) {
            addCriterion("report_to_type =", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeNotEqualTo(String value) {
            addCriterion("report_to_type <>", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeGreaterThan(String value) {
            addCriterion("report_to_type >", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeGreaterThanOrEqualTo(String value) {
            addCriterion("report_to_type >=", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeLessThan(String value) {
            addCriterion("report_to_type <", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeLessThanOrEqualTo(String value) {
            addCriterion("report_to_type <=", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeLike(String value) {
            addCriterion("report_to_type like", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeNotLike(String value) {
            addCriterion("report_to_type not like", value, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeIn(List<String> values) {
            addCriterion("report_to_type in", values, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeNotIn(List<String> values) {
            addCriterion("report_to_type not in", values, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeBetween(String value1, String value2) {
            addCriterion("report_to_type between", value1, value2, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andReport_to_typeNotBetween(String value1, String value2) {
            addCriterion("report_to_type not between", value1, value2, "report_to_type");
            return (Criteria) this;
        }

        public Criteria andParamsIsNull() {
            addCriterion("params is null");
            return (Criteria) this;
        }

        public Criteria andParamsIsNotNull() {
            addCriterion("params is not null");
            return (Criteria) this;
        }

        public Criteria andParamsEqualTo(String value) {
            addCriterion("params =", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotEqualTo(String value) {
            addCriterion("params <>", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThan(String value) {
            addCriterion("params >", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsGreaterThanOrEqualTo(String value) {
            addCriterion("params >=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThan(String value) {
            addCriterion("params <", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLessThanOrEqualTo(String value) {
            addCriterion("params <=", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsLike(String value) {
            addCriterion("params like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotLike(String value) {
            addCriterion("params not like", value, "params");
            return (Criteria) this;
        }

        public Criteria andParamsIn(List<String> values) {
            addCriterion("params in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotIn(List<String> values) {
            addCriterion("params not in", values, "params");
            return (Criteria) this;
        }

        public Criteria andParamsBetween(String value1, String value2) {
            addCriterion("params between", value1, value2, "params");
            return (Criteria) this;
        }

        public Criteria andParamsNotBetween(String value1, String value2) {
            addCriterion("params not between", value1, value2, "params");
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
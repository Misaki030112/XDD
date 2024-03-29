package com.hznu.xdd.domain.pojoExam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class courseDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public courseDOExample() {
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

    public courseDOExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public courseDOExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public courseDOExample page(Integer page, Integer pageSize) {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Double value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Double value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Double value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Double value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Double value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Double> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Double> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Double value1, Double value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Double value1, Double value2) {
            addCriterion("credit not between", value1, value2, "credit");
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

        public Criteria andSchool_idIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchool_idIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchool_idEqualTo(Integer value) {
            addCriterion("school_id =", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idGreaterThan(Integer value) {
            addCriterion("school_id >", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idLessThan(Integer value) {
            addCriterion("school_id <", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idIn(List<Integer> values) {
            addCriterion("school_id in", values, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "school_id");
            return (Criteria) this;
        }

        public Criteria andSchool_idNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "school_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idIsNull() {
            addCriterion("college_id is null");
            return (Criteria) this;
        }

        public Criteria andCollege_idIsNotNull() {
            addCriterion("college_id is not null");
            return (Criteria) this;
        }

        public Criteria andCollege_idEqualTo(Integer value) {
            addCriterion("college_id =", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idNotEqualTo(Integer value) {
            addCriterion("college_id <>", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idGreaterThan(Integer value) {
            addCriterion("college_id >", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("college_id >=", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idLessThan(Integer value) {
            addCriterion("college_id <", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idLessThanOrEqualTo(Integer value) {
            addCriterion("college_id <=", value, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idIn(List<Integer> values) {
            addCriterion("college_id in", values, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idNotIn(List<Integer> values) {
            addCriterion("college_id not in", values, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idBetween(Integer value1, Integer value2) {
            addCriterion("college_id between", value1, value2, "college_id");
            return (Criteria) this;
        }

        public Criteria andCollege_idNotBetween(Integer value1, Integer value2) {
            addCriterion("college_id not between", value1, value2, "college_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idIsNull() {
            addCriterion("campus_id is null");
            return (Criteria) this;
        }

        public Criteria andCampus_idIsNotNull() {
            addCriterion("campus_id is not null");
            return (Criteria) this;
        }

        public Criteria andCampus_idEqualTo(Integer value) {
            addCriterion("campus_id =", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idNotEqualTo(Integer value) {
            addCriterion("campus_id <>", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idGreaterThan(Integer value) {
            addCriterion("campus_id >", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("campus_id >=", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idLessThan(Integer value) {
            addCriterion("campus_id <", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idLessThanOrEqualTo(Integer value) {
            addCriterion("campus_id <=", value, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idIn(List<Integer> values) {
            addCriterion("campus_id in", values, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idNotIn(List<Integer> values) {
            addCriterion("campus_id not in", values, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idBetween(Integer value1, Integer value2) {
            addCriterion("campus_id between", value1, value2, "campus_id");
            return (Criteria) this;
        }

        public Criteria andCampus_idNotBetween(Integer value1, Integer value2) {
            addCriterion("campus_id not between", value1, value2, "campus_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idIsNull() {
            addCriterion("teachers_id is null");
            return (Criteria) this;
        }

        public Criteria andTeachers_idIsNotNull() {
            addCriterion("teachers_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeachers_idEqualTo(String value) {
            addCriterion("teachers_id =", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idNotEqualTo(String value) {
            addCriterion("teachers_id <>", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idGreaterThan(String value) {
            addCriterion("teachers_id >", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idGreaterThanOrEqualTo(String value) {
            addCriterion("teachers_id >=", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idLessThan(String value) {
            addCriterion("teachers_id <", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idLessThanOrEqualTo(String value) {
            addCriterion("teachers_id <=", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idLike(String value) {
            addCriterion("teachers_id like", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idNotLike(String value) {
            addCriterion("teachers_id not like", value, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idIn(List<String> values) {
            addCriterion("teachers_id in", values, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idNotIn(List<String> values) {
            addCriterion("teachers_id not in", values, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idBetween(String value1, String value2) {
            addCriterion("teachers_id between", value1, value2, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andTeachers_idNotBetween(String value1, String value2) {
            addCriterion("teachers_id not between", value1, value2, "teachers_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idIsNull() {
            addCriterion("official_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficial_idIsNotNull() {
            addCriterion("official_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficial_idEqualTo(Integer value) {
            addCriterion("official_id =", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idNotEqualTo(Integer value) {
            addCriterion("official_id <>", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idGreaterThan(Integer value) {
            addCriterion("official_id >", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("official_id >=", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idLessThan(Integer value) {
            addCriterion("official_id <", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idLessThanOrEqualTo(Integer value) {
            addCriterion("official_id <=", value, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idIn(List<Integer> values) {
            addCriterion("official_id in", values, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idNotIn(List<Integer> values) {
            addCriterion("official_id not in", values, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idBetween(Integer value1, Integer value2) {
            addCriterion("official_id between", value1, value2, "official_id");
            return (Criteria) this;
        }

        public Criteria andOfficial_idNotBetween(Integer value1, Integer value2) {
            addCriterion("official_id not between", value1, value2, "official_id");
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
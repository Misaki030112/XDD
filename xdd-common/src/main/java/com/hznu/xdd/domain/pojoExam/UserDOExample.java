package com.hznu.xdd.domain.pojoExam;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public UserDOExample() {
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

    public UserDOExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public UserDOExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public UserDOExample page(Integer page, Integer pageSize) {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andOpen_id_xiaododo_miniIsNull() {
            addCriterion("open_id_xiaododo_mini is null");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniIsNotNull() {
            addCriterion("open_id_xiaododo_mini is not null");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniEqualTo(String value) {
            addCriterion("open_id_xiaododo_mini =", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniNotEqualTo(String value) {
            addCriterion("open_id_xiaododo_mini <>", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniGreaterThan(String value) {
            addCriterion("open_id_xiaododo_mini >", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniGreaterThanOrEqualTo(String value) {
            addCriterion("open_id_xiaododo_mini >=", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniLessThan(String value) {
            addCriterion("open_id_xiaododo_mini <", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniLessThanOrEqualTo(String value) {
            addCriterion("open_id_xiaododo_mini <=", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniLike(String value) {
            addCriterion("open_id_xiaododo_mini like", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniNotLike(String value) {
            addCriterion("open_id_xiaododo_mini not like", value, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniIn(List<String> values) {
            addCriterion("open_id_xiaododo_mini in", values, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniNotIn(List<String> values) {
            addCriterion("open_id_xiaododo_mini not in", values, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniBetween(String value1, String value2) {
            addCriterion("open_id_xiaododo_mini between", value1, value2, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_miniNotBetween(String value1, String value2) {
            addCriterion("open_id_xiaododo_mini not between", value1, value2, "open_id_xiaododo_mini");
            return (Criteria) this;
        }

        public Criteria andUnion_idIsNull() {
            addCriterion("union_id is null");
            return (Criteria) this;
        }

        public Criteria andUnion_idIsNotNull() {
            addCriterion("union_id is not null");
            return (Criteria) this;
        }

        public Criteria andUnion_idEqualTo(String value) {
            addCriterion("union_id =", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idNotEqualTo(String value) {
            addCriterion("union_id <>", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idGreaterThan(String value) {
            addCriterion("union_id >", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idGreaterThanOrEqualTo(String value) {
            addCriterion("union_id >=", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idLessThan(String value) {
            addCriterion("union_id <", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idLessThanOrEqualTo(String value) {
            addCriterion("union_id <=", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idLike(String value) {
            addCriterion("union_id like", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idNotLike(String value) {
            addCriterion("union_id not like", value, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idIn(List<String> values) {
            addCriterion("union_id in", values, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idNotIn(List<String> values) {
            addCriterion("union_id not in", values, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idBetween(String value1, String value2) {
            addCriterion("union_id between", value1, value2, "union_id");
            return (Criteria) this;
        }

        public Criteria andUnion_idNotBetween(String value1, String value2) {
            addCriterion("union_id not between", value1, value2, "union_id");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountIsNull() {
            addCriterion("open_id_xiaododo_official_account is null");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountIsNotNull() {
            addCriterion("open_id_xiaododo_official_account is not null");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountEqualTo(String value) {
            addCriterion("open_id_xiaododo_official_account =", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountNotEqualTo(String value) {
            addCriterion("open_id_xiaododo_official_account <>", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountGreaterThan(String value) {
            addCriterion("open_id_xiaododo_official_account >", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountGreaterThanOrEqualTo(String value) {
            addCriterion("open_id_xiaododo_official_account >=", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountLessThan(String value) {
            addCriterion("open_id_xiaododo_official_account <", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountLessThanOrEqualTo(String value) {
            addCriterion("open_id_xiaododo_official_account <=", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountLike(String value) {
            addCriterion("open_id_xiaododo_official_account like", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountNotLike(String value) {
            addCriterion("open_id_xiaododo_official_account not like", value, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountIn(List<String> values) {
            addCriterion("open_id_xiaododo_official_account in", values, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountNotIn(List<String> values) {
            addCriterion("open_id_xiaododo_official_account not in", values, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountBetween(String value1, String value2) {
            addCriterion("open_id_xiaododo_official_account between", value1, value2, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andOpen_id_xiaododo_official_accountNotBetween(String value1, String value2) {
            addCriterion("open_id_xiaododo_official_account not between", value1, value2, "open_id_xiaododo_official_account");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Short value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Short value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Short value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Short value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Short value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Short value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Short> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Short> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Short value1, Short value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Short value1, Short value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("district is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("district is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("district =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("district <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("district >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("district >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("district <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("district <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("district like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("district not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("district in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("district not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("district between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("district not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNull() {
            addCriterion("school is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNotNull() {
            addCriterion("school is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolEqualTo(String value) {
            addCriterion("school =", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotEqualTo(String value) {
            addCriterion("school <>", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThan(String value) {
            addCriterion("school >", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("school >=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThan(String value) {
            addCriterion("school <", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThanOrEqualTo(String value) {
            addCriterion("school <=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLike(String value) {
            addCriterion("school like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotLike(String value) {
            addCriterion("school not like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolIn(List<String> values) {
            addCriterion("school in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotIn(List<String> values) {
            addCriterion("school not in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolBetween(String value1, String value2) {
            addCriterion("school between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotBetween(String value1, String value2) {
            addCriterion("school not between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andCollegeIsNull() {
            addCriterion("college is null");
            return (Criteria) this;
        }

        public Criteria andCollegeIsNotNull() {
            addCriterion("college is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeEqualTo(String value) {
            addCriterion("college =", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotEqualTo(String value) {
            addCriterion("college <>", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeGreaterThan(String value) {
            addCriterion("college >", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeGreaterThanOrEqualTo(String value) {
            addCriterion("college >=", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLessThan(String value) {
            addCriterion("college <", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLessThanOrEqualTo(String value) {
            addCriterion("college <=", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeLike(String value) {
            addCriterion("college like", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotLike(String value) {
            addCriterion("college not like", value, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeIn(List<String> values) {
            addCriterion("college in", values, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotIn(List<String> values) {
            addCriterion("college not in", values, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeBetween(String value1, String value2) {
            addCriterion("college between", value1, value2, "college");
            return (Criteria) this;
        }

        public Criteria andCollegeNotBetween(String value1, String value2) {
            addCriterion("college not between", value1, value2, "college");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNull() {
            addCriterion("signature is null");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNotNull() {
            addCriterion("signature is not null");
            return (Criteria) this;
        }

        public Criteria andSignatureEqualTo(String value) {
            addCriterion("signature =", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotEqualTo(String value) {
            addCriterion("signature <>", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThan(String value) {
            addCriterion("signature >", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThanOrEqualTo(String value) {
            addCriterion("signature >=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThan(String value) {
            addCriterion("signature <", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThanOrEqualTo(String value) {
            addCriterion("signature <=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLike(String value) {
            addCriterion("signature like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotLike(String value) {
            addCriterion("signature not like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureIn(List<String> values) {
            addCriterion("signature in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotIn(List<String> values) {
            addCriterion("signature not in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureBetween(String value1, String value2) {
            addCriterion("signature between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotBetween(String value1, String value2) {
            addCriterion("signature not between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andFollow_numIsNull() {
            addCriterion("follow_num is null");
            return (Criteria) this;
        }

        public Criteria andFollow_numIsNotNull() {
            addCriterion("follow_num is not null");
            return (Criteria) this;
        }

        public Criteria andFollow_numEqualTo(Integer value) {
            addCriterion("follow_num =", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numNotEqualTo(Integer value) {
            addCriterion("follow_num <>", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numGreaterThan(Integer value) {
            addCriterion("follow_num >", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("follow_num >=", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numLessThan(Integer value) {
            addCriterion("follow_num <", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numLessThanOrEqualTo(Integer value) {
            addCriterion("follow_num <=", value, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numIn(List<Integer> values) {
            addCriterion("follow_num in", values, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numNotIn(List<Integer> values) {
            addCriterion("follow_num not in", values, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numBetween(Integer value1, Integer value2) {
            addCriterion("follow_num between", value1, value2, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFollow_numNotBetween(Integer value1, Integer value2) {
            addCriterion("follow_num not between", value1, value2, "follow_num");
            return (Criteria) this;
        }

        public Criteria andFan_numIsNull() {
            addCriterion("fan_num is null");
            return (Criteria) this;
        }

        public Criteria andFan_numIsNotNull() {
            addCriterion("fan_num is not null");
            return (Criteria) this;
        }

        public Criteria andFan_numEqualTo(Integer value) {
            addCriterion("fan_num =", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numNotEqualTo(Integer value) {
            addCriterion("fan_num <>", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numGreaterThan(Integer value) {
            addCriterion("fan_num >", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("fan_num >=", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numLessThan(Integer value) {
            addCriterion("fan_num <", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numLessThanOrEqualTo(Integer value) {
            addCriterion("fan_num <=", value, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numIn(List<Integer> values) {
            addCriterion("fan_num in", values, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numNotIn(List<Integer> values) {
            addCriterion("fan_num not in", values, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numBetween(Integer value1, Integer value2) {
            addCriterion("fan_num between", value1, value2, "fan_num");
            return (Criteria) this;
        }

        public Criteria andFan_numNotBetween(Integer value1, Integer value2) {
            addCriterion("fan_num not between", value1, value2, "fan_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numIsNull() {
            addCriterion("publish_num is null");
            return (Criteria) this;
        }

        public Criteria andPublish_numIsNotNull() {
            addCriterion("publish_num is not null");
            return (Criteria) this;
        }

        public Criteria andPublish_numEqualTo(Integer value) {
            addCriterion("publish_num =", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numNotEqualTo(Integer value) {
            addCriterion("publish_num <>", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numGreaterThan(Integer value) {
            addCriterion("publish_num >", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish_num >=", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numLessThan(Integer value) {
            addCriterion("publish_num <", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numLessThanOrEqualTo(Integer value) {
            addCriterion("publish_num <=", value, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numIn(List<Integer> values) {
            addCriterion("publish_num in", values, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numNotIn(List<Integer> values) {
            addCriterion("publish_num not in", values, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numBetween(Integer value1, Integer value2) {
            addCriterion("publish_num between", value1, value2, "publish_num");
            return (Criteria) this;
        }

        public Criteria andPublish_numNotBetween(Integer value1, Integer value2) {
            addCriterion("publish_num not between", value1, value2, "publish_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numIsNull() {
            addCriterion("collect_num is null");
            return (Criteria) this;
        }

        public Criteria andCollect_numIsNotNull() {
            addCriterion("collect_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollect_numEqualTo(Integer value) {
            addCriterion("collect_num =", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numNotEqualTo(Integer value) {
            addCriterion("collect_num <>", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numGreaterThan(Integer value) {
            addCriterion("collect_num >", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_num >=", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numLessThan(Integer value) {
            addCriterion("collect_num <", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numLessThanOrEqualTo(Integer value) {
            addCriterion("collect_num <=", value, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numIn(List<Integer> values) {
            addCriterion("collect_num in", values, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numNotIn(List<Integer> values) {
            addCriterion("collect_num not in", values, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numBetween(Integer value1, Integer value2) {
            addCriterion("collect_num between", value1, value2, "collect_num");
            return (Criteria) this;
        }

        public Criteria andCollect_numNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_num not between", value1, value2, "collect_num");
            return (Criteria) this;
        }

        public Criteria andVote_numIsNull() {
            addCriterion("vote_num is null");
            return (Criteria) this;
        }

        public Criteria andVote_numIsNotNull() {
            addCriterion("vote_num is not null");
            return (Criteria) this;
        }

        public Criteria andVote_numEqualTo(Integer value) {
            addCriterion("vote_num =", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numNotEqualTo(Integer value) {
            addCriterion("vote_num <>", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numGreaterThan(Integer value) {
            addCriterion("vote_num >", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("vote_num >=", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numLessThan(Integer value) {
            addCriterion("vote_num <", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numLessThanOrEqualTo(Integer value) {
            addCriterion("vote_num <=", value, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numIn(List<Integer> values) {
            addCriterion("vote_num in", values, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numNotIn(List<Integer> values) {
            addCriterion("vote_num not in", values, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numBetween(Integer value1, Integer value2) {
            addCriterion("vote_num between", value1, value2, "vote_num");
            return (Criteria) this;
        }

        public Criteria andVote_numNotBetween(Integer value1, Integer value2) {
            addCriterion("vote_num not between", value1, value2, "vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numIsNull() {
            addCriterion("receive_vote_num is null");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numIsNotNull() {
            addCriterion("receive_vote_num is not null");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numEqualTo(Integer value) {
            addCriterion("receive_vote_num =", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numNotEqualTo(Integer value) {
            addCriterion("receive_vote_num <>", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numGreaterThan(Integer value) {
            addCriterion("receive_vote_num >", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("receive_vote_num >=", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numLessThan(Integer value) {
            addCriterion("receive_vote_num <", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numLessThanOrEqualTo(Integer value) {
            addCriterion("receive_vote_num <=", value, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numIn(List<Integer> values) {
            addCriterion("receive_vote_num in", values, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numNotIn(List<Integer> values) {
            addCriterion("receive_vote_num not in", values, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numBetween(Integer value1, Integer value2) {
            addCriterion("receive_vote_num between", value1, value2, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andReceive_vote_numNotBetween(Integer value1, Integer value2) {
            addCriterion("receive_vote_num not between", value1, value2, "receive_vote_num");
            return (Criteria) this;
        }

        public Criteria andAccount_statusIsNull() {
            addCriterion("account_status is null");
            return (Criteria) this;
        }

        public Criteria andAccount_statusIsNotNull() {
            addCriterion("account_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_statusEqualTo(Integer value) {
            addCriterion("account_status =", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusNotEqualTo(Integer value) {
            addCriterion("account_status <>", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusGreaterThan(Integer value) {
            addCriterion("account_status >", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_status >=", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusLessThan(Integer value) {
            addCriterion("account_status <", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusLessThanOrEqualTo(Integer value) {
            addCriterion("account_status <=", value, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusIn(List<Integer> values) {
            addCriterion("account_status in", values, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusNotIn(List<Integer> values) {
            addCriterion("account_status not in", values, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusBetween(Integer value1, Integer value2) {
            addCriterion("account_status between", value1, value2, "account_status");
            return (Criteria) this;
        }

        public Criteria andAccount_statusNotBetween(Integer value1, Integer value2) {
            addCriterion("account_status not between", value1, value2, "account_status");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Integer value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Integer value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Integer value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Integer value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Integer value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Integer> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Integer> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Integer value1, Integer value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Integer value1, Integer value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andAccess_yearIsNull() {
            addCriterion("access_year is null");
            return (Criteria) this;
        }

        public Criteria andAccess_yearIsNotNull() {
            addCriterion("access_year is not null");
            return (Criteria) this;
        }

        public Criteria andAccess_yearEqualTo(String value) {
            addCriterion("access_year =", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearNotEqualTo(String value) {
            addCriterion("access_year <>", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearGreaterThan(String value) {
            addCriterion("access_year >", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearGreaterThanOrEqualTo(String value) {
            addCriterion("access_year >=", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearLessThan(String value) {
            addCriterion("access_year <", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearLessThanOrEqualTo(String value) {
            addCriterion("access_year <=", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearLike(String value) {
            addCriterion("access_year like", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearNotLike(String value) {
            addCriterion("access_year not like", value, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearIn(List<String> values) {
            addCriterion("access_year in", values, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearNotIn(List<String> values) {
            addCriterion("access_year not in", values, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearBetween(String value1, String value2) {
            addCriterion("access_year between", value1, value2, "access_year");
            return (Criteria) this;
        }

        public Criteria andAccess_yearNotBetween(String value1, String value2) {
            addCriterion("access_year not between", value1, value2, "access_year");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("role like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("role not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneIsNull() {
            addCriterion("subscribe_scene is null");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneIsNotNull() {
            addCriterion("subscribe_scene is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneEqualTo(String value) {
            addCriterion("subscribe_scene =", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneNotEqualTo(String value) {
            addCriterion("subscribe_scene <>", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneGreaterThan(String value) {
            addCriterion("subscribe_scene >", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_scene >=", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneLessThan(String value) {
            addCriterion("subscribe_scene <", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneLessThanOrEqualTo(String value) {
            addCriterion("subscribe_scene <=", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneLike(String value) {
            addCriterion("subscribe_scene like", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneNotLike(String value) {
            addCriterion("subscribe_scene not like", value, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneIn(List<String> values) {
            addCriterion("subscribe_scene in", values, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneNotIn(List<String> values) {
            addCriterion("subscribe_scene not in", values, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneBetween(String value1, String value2) {
            addCriterion("subscribe_scene between", value1, value2, "subscribe_scene");
            return (Criteria) this;
        }

        public Criteria andSubscribe_sceneNotBetween(String value1, String value2) {
            addCriterion("subscribe_scene not between", value1, value2, "subscribe_scene");
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
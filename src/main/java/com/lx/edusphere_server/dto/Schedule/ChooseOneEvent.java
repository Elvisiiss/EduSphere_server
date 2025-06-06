package com.lx.edusphere_server.dto.Schedule;

import com.lx.edusphere_server.entity.RepeatConfig;

import java.time.LocalDate;
import java.util.Map;
/*
{
    "msg": "创建新日程",
    "user_token": "7624fbe6-7878-49f1-8481-167022d17187",
    "event_name": "跑步",
    "event_describe": "饭后走一走，活到一千九",
    "repeat_type": "monthly_week",
    "repeat_config":
    {
        "week": 2,
        "day": 5
    },
    "start_date": "2025-06-05",
    "end_type": 'never',
    "end_value": null,
    "degree_of_importance": 1,
    "img_url": "www.baidu.com"
}

CREATE TABLE schedule_event (
  event_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
  belong_user BIGINT NOT NULL COMMENT '事件归属用户',
  event_name VARCHAR(255) NOT NULL COMMENT '事件名',
  event_describe TEXT COMMENT '事件描述',

  -- 重复规则核心字段 --
  repeat_type ENUM('none', 'daily', 'weekly', 'monthly_date', 'monthly_week', 'yearly', 'custom')
    NOT NULL DEFAULT 'none' COMMENT '重复类型',
  repeat_config JSON NOT NULL COMMENT '重复规则配置（JSON格式）',

  -- 触发时间管理 --
  start_date DATE NOT NULL COMMENT '首次触发日期',
  next_trigger_date DATE NOT NULL COMMENT '下次触发日期',

  -- 终止条件 --
  end_type ENUM('never', 'after_occurrences', 'on_date')
    NOT NULL DEFAULT 'never' COMMENT '结束类型',
  end_value VARCHAR(20) COMMENT '结束值（次数或日期）',

  -- 状态管理 --
  state ENUM('active', 'completed', 'cancelled', 'deleted')
    NOT NULL DEFAULT 'active' COMMENT '事件状态',
  occurrence_count INT NOT NULL DEFAULT 0 COMMENT '已发生次数',

  -- 其他字段 --
  degree_of_importance INT NOT NULL DEFAULT 4
    COMMENT '1：高优先级，2：中，3：低，4：无',
  img_url VARCHAR(2000) COMMENT '事件图片'
);

1. 每10天重复一次
sql
repeat_type = 'custom'
repeat_config = '{"unit":"day", "interval":10}'
2. 每周二和周四重复
sql
repeat_type = 'weekly'
repeat_config = '{"days":[2,4]}'  -- 0=周日,1=周一,2=周二...6=周六
3. 其他复杂重复模式示例
每2周的周一、周三、周五：

sql
repeat_type = 'custom'
repeat_config = '{"unit":"week", "interval":2, "days":[1,3,5]}'
每月第3个周二：

sql
repeat_type = 'monthly_week'
repeat_config = '{"week":3, "day":2}'  -- week:1-5, day:0-6


重复类型	        repeat_config 格式	                        示例值	                                    说明
none	        {} 或 null	                                {}	                                        不重复
daily	        {}	                                        {}	                                        每天重复
weekly	        {"days":[d1,d2...]}	                        {"days":[1,3,5]}	                        每周的指定星期几
monthly_date	{"day":D}	                                {"day":15}	                                每月第D天
monthly_week	{"week":W, "day":D}	                        {"week":2, "day":5}	                        每月第W个星期D
yearly	        {"month":M, "day":D}	                    {"month":12, "day":24}	                    每年M月D日
custom	        {"unit":"day/week/month", "interval":N}	    {"unit":"day", "interval":10}	            自定义间隔重复
custom	        {"unit":"week", "interval":N, "days":[...]}	{"unit":"week","interval":2,"days":[2,4]}	每N周的指定星期几


* */



public class ChooseOneEvent {
    //使用者的token
    private String user_token;
    //以下是被选择者的information
    private Long event_id;
    private Long belong_user;
    private String event_name;
    private String event_describe;
    private String repeat_type;
    private String repeat_config;
    private RepeatConfig repeat_config_json;
    private LocalDate start_date;
    private LocalDate next_trigger_date;
    private String end_type;
    private String end_value;
    private String state;
    private Long occurrence_count;
    private Integer degree_of_importance;
    private String img_url;
    private Boolean is_prediction = false;

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public Long getBelong_user() {
        return belong_user;
    }

    public void setBelong_user(Long belong_user) {
        this.belong_user = belong_user;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_describe() {
        return event_describe;
    }

    public void setEvent_describe(String event_describe) {
        this.event_describe = event_describe;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public void setRepeat_type(String repeat_type) {
        this.repeat_type = repeat_type;
    }

    public String getRepeat_config() {
        return repeat_config;
    }

    public void setRepeat_config(String repeat_config) {
        this.repeat_config = repeat_config;
    }

    public RepeatConfig getRepeat_config_json() {
        return repeat_config_json;
    }

    public void setRepeat_config_json(RepeatConfig repeat_config_json) {
        this.repeat_config_json = repeat_config_json;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getNext_trigger_date() {
        return next_trigger_date;
    }

    public void setNext_trigger_date(LocalDate next_trigger_date) {
        this.next_trigger_date = next_trigger_date;
    }

    public String getEnd_type() {
        return end_type;
    }

    public void setEnd_type(String end_type) {
        this.end_type = end_type;
    }

    public String getEnd_value() {
        return end_value;
    }

    public void setEnd_value(String end_value) {
        this.end_value = end_value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOccurrence_count() {
        return occurrence_count;
    }

    public void setOccurrence_count(Long occurrence_count) {
        this.occurrence_count = occurrence_count;
    }

    public Integer getDegree_of_importance() {
        return degree_of_importance;
    }

    public void setDegree_of_importance(Integer degree_of_importance) {
        this.degree_of_importance = degree_of_importance;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Boolean getIs_prediction() {
        return is_prediction;
    }

    public void setIs_prediction(Boolean is_prediction) {
        this.is_prediction = is_prediction;
    }
}

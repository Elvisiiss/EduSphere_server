package com.lx.edusphere_server.entity;

import java.time.LocalDate;

public class Event {
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

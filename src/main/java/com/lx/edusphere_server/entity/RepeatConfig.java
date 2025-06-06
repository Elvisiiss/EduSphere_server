package com.lx.edusphere_server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RepeatConfig {
    // 通用字段
    private String unit;        // "day", "week", "month"
    private Integer interval;   // 间隔数值
    private List<Integer> days; // 周几 [0=周日,1=周一...6=周六]

    // 月/年特定字段
    private Integer day;        // 每月几号 (1-31)
    private Integer week;       // 第几周 (1-5)
    private Integer month;      // 月份 (1-12)

    // 无参构造器
    public RepeatConfig() {}

    // 从JSON字符串解析
    public static RepeatConfig fromJson(String json) {
        if (json == null || json.isEmpty()) return null;
        try {
            return new ObjectMapper().readValue(json, RepeatConfig.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid repeat_config JSON", e);
        }
    }

    // 转换为JSON字符串
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing repeat_config", e);
        }
    }

    // Getter & Setter
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Integer getInterval() { return interval; }
    public void setInterval(Integer interval) { this.interval = interval; }

    public List<Integer> getDays() { return days; }
    public void setDays(List<Integer> days) { this.days = days; }

    public Integer getDay() { return day; }
    public void setDay(Integer day) { this.day = day; }

    public Integer getWeek() { return week; }
    public void setWeek(Integer week) { this.week = week; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }
}

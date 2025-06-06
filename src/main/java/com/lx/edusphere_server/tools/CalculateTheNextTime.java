package com.lx.edusphere_server.tools;

import com.lx.edusphere_server.entity.RepeatConfig;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class CalculateTheNextTime {

    public static LocalDate next_time(LocalDate come, String repeat_type, RepeatConfig repeat_config) {
        if (come == null) {
            return null;
        }

        switch (repeat_type) {
            case "none":
                return null;

            case "daily":
                return come.plusDays(1);

            case "weekly":
                return handleWeekly(come, repeat_config);

            case "monthly_date":
                return handleMonthlyDate(come, repeat_config);

            case "monthly_week":
                return handleMonthlyWeek(come, repeat_config);

            case "yearly":
                return handleYearly(come, repeat_config);

            case "custom":
                return handleCustom(come, repeat_config);

            default:
                throw new IllegalArgumentException("Unsupported repeat type: " + repeat_type);
        }
    }

    private static LocalDate handleWeekly(LocalDate come, RepeatConfig config) {
        List<Integer> days = config.getDays();
        if (days == null || days.isEmpty()) {
            return null;
        }

        int currentDayOfWeek = come.getDayOfWeek().getValue() % 7;
        Integer nextDay = null;

        for (int day : days) {
            if (day > currentDayOfWeek) {
                if (nextDay == null || day < nextDay) {
                    nextDay = day;
                }
            }
        }

        if (nextDay != null) {
            return come.plusDays(nextDay - currentDayOfWeek);
        }

        int firstDay = days.stream().min(Integer::compare).orElse(0);
        return come.plusDays(7 - currentDayOfWeek + firstDay);
    }

    private static LocalDate handleMonthlyDate(LocalDate come, RepeatConfig config) {
        int dayOfMonth = config.getDay();
        // 计算本月的目标日期
        LocalDate thisMonth = adjustToValidDayOfMonth(come.withDayOfMonth(1), dayOfMonth);

        // 检查本月目标日期是否在未来（排除当天）
        if (thisMonth.isAfter(come)) {
            return thisMonth;
        }
        // 否则返回下个月的目标日期
        LocalDate nextMonth = come.plusMonths(1).withDayOfMonth(1);
        return adjustToValidDayOfMonth(nextMonth, dayOfMonth);
    }

    private static LocalDate handleMonthlyWeek(LocalDate come, RepeatConfig config) {
        int ordinal = config.getWeek();
        int dayOfWeek = config.getDay();
        DayOfWeek targetDow = DayOfWeek.of(dayOfWeek == 0 ? 7 : dayOfWeek);

        // 计算本月的目标日期
        LocalDate baseThisMonth = come.withDayOfMonth(1);
        LocalDate candidateThisMonth = baseThisMonth.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, targetDow));
        if (candidateThisMonth.getMonth() != baseThisMonth.getMonth()) {
            candidateThisMonth = baseThisMonth.with(TemporalAdjusters.lastInMonth(targetDow));
        }

        // 检查本月目标日期是否在未来（排除当天）
        if (candidateThisMonth.isAfter(come)) {
            return candidateThisMonth;
        }

        // 否则计算下个月的目标日期
        LocalDate baseNextMonth = come.plusMonths(1).withDayOfMonth(1);
        LocalDate candidateNextMonth = baseNextMonth.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, targetDow));
        if (candidateNextMonth.getMonth() != baseNextMonth.getMonth()) {
            candidateNextMonth = baseNextMonth.with(TemporalAdjusters.lastInMonth(targetDow));
        }
        return candidateNextMonth;
    }

    private static LocalDate handleYearly(LocalDate come, RepeatConfig config) {
        int month = config.getMonth();
        int day = config.getDay();

        // 计算今年的目标日期
        LocalDate thisYear = adjustToValidDayOfMonth(come.withMonth(month).withDayOfMonth(1), day);

        // 检查今年目标日期是否在未来（排除当天）
        if (thisYear.isAfter(come)) {
            return thisYear;
        }
        // 否则返回明年的目标日期
        LocalDate nextYear = come.plusYears(1).withMonth(month).withDayOfMonth(1);
        return adjustToValidDayOfMonth(nextYear, day);
    }

    private static LocalDate handleCustom(LocalDate come, RepeatConfig config) {
        String unit = config.getUnit();
        Integer interval = config.getInterval();
        if (interval == null || interval <= 0) {
            return null;
        }

        switch (unit) {
            case "day":
                return come.plusDays(interval);
            case "week":
                return handleCustomWeek(come, config);
            case "month":
                return come.plusMonths(interval);
            default:
                throw new IllegalArgumentException("Unsupported custom unit: " + unit);
        }
    }

    private static LocalDate handleCustomWeek(LocalDate come, RepeatConfig config) {
        List<Integer> days = config.getDays();
        int interval = config.getInterval();
        if (days == null || days.isEmpty()) {
            return null;
        }

        // 先检查本周剩余日期
        int currentDayOfWeek = come.getDayOfWeek().getValue() % 7;
        for (int i = 1; i <= 7 - currentDayOfWeek; i++) {
            LocalDate candidate = come.plusDays(i);
            int candidateDow = candidate.getDayOfWeek().getValue() % 7;
            if (days.contains(candidateDow)) {
                return candidate;
            }
        }

        // 计算目标周的开始日期（下interval周的周一）
        LocalDate base = come.plusWeeks(interval)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // 在目标周中查找第一个匹配的日期
        for (int i = 0; i < 7; i++) {
            LocalDate candidate = base.plusDays(i);
            int candidateDow = candidate.getDayOfWeek().getValue() % 7;
            if (days.contains(candidateDow)) {
                return candidate;
            }
        }
        return null;
    }

    private static LocalDate adjustToValidDayOfMonth(LocalDate date, int targetDay) {
        int maxDay = date.lengthOfMonth();
        int adjustedDay = Math.min(targetDay, maxDay);
        return date.withDayOfMonth(adjustedDay);
    }
}

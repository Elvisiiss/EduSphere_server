package com.lx.edusphere_server.tools;

import com.lx.edusphere_server.entity.Event;
import com.lx.edusphere_server.entity.RepeatConfig;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcludeItemsFromTheListThatAreNotTargeted {
    public static List<Event> exclude_items_from_the_list_that_are_not_targeted(List<Event> events, LocalDate targetDate) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (isEventOccurringOnDate(event, targetDate)) {
                result.add(event);
            }
        }
        return result;
    }

    private static boolean isEventOccurringOnDate(Event event, LocalDate targetDate) {
        // 检查目标日期是否在事件开始日期之前
        if (targetDate.isBefore(event.getStart_date())) {
            return false;
        }

        // 检查结束条件
        if (event.getEnd_type().equals("on_date")) {
            LocalDate endDate = LocalDate.parse(event.getEnd_value());
            if (targetDate.isAfter(endDate)) {
                return false;
            }
        } else if (event.getEnd_type().equals("after_occurrences")) {
            int maxOccurrences = Integer.parseInt(event.getEnd_value());
            if (calculateOccurrenceCount(event, targetDate) > maxOccurrences) {
                return false;
            }
        }

        // 根据重复类型进行匹配
        switch (event.getRepeat_type()) {
            case "none":
                return targetDate.equals(event.getStart_date());
            case "daily":
                return matchesDaily(event, targetDate);
            case "weekly":
                return matchesWeekly(event, targetDate);
            case "monthly_date":
                return matchesMonthlyDate(event, targetDate);
            case "monthly_week":
                return matchesMonthlyWeek(event, targetDate);
            case "yearly":
                return matchesYearly(event, targetDate);
            case "custom":
                return matchesCustom(event, targetDate);
            default:
                return false;
        }
    }

    private static boolean matchesDaily(Event event, LocalDate targetDate) {
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
                event.getStart_date(), targetDate
        );
        return daysBetween % 1 == 0; // 每天重复，总是匹配
    }

    private static boolean matchesWeekly(Event event, LocalDate targetDate) {
        RepeatConfig config = event.getRepeat_config_json();
        List<Integer> daysOfWeek = config.getDays();
        int targetDayOfWeek = targetDate.getDayOfWeek().getValue() % 7; // 转换为0-6(周日=0)
        return daysOfWeek.contains(targetDayOfWeek);
    }

    private static boolean matchesMonthlyDate(Event event, LocalDate targetDate) {
        int dayOfMonth = event.getRepeat_config_json().getDay();
        return targetDate.getDayOfMonth() == dayOfMonth;
    }

    private static boolean matchesMonthlyWeek(Event event, LocalDate targetDate) {
        RepeatConfig config = event.getRepeat_config_json();
        int week = config.getWeek(); // 第几周 (1-5)
        int day = config.getDay();   // 周几 (0-6, 0=周日)

        // 计算当月的第一个目标星期几
        LocalDate firstDay = targetDate.withDayOfMonth(1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;

        int offset = day - firstDayOfWeek;
        if (offset < 0) offset += 7;
        LocalDate occurrence = firstDay.plusDays(offset + (week - 1) * 7);

        // 检查是否仍在同一个月
        return occurrence.getMonth() == targetDate.getMonth() &&
                occurrence.equals(targetDate);
    }

    private static boolean matchesYearly(Event event, LocalDate targetDate) {
        RepeatConfig config = event.getRepeat_config_json();
        return targetDate.getMonthValue() == config.getMonth() &&
                targetDate.getDayOfMonth() == config.getDay();
    }

    private static boolean matchesCustom(Event event, LocalDate targetDate) {
        RepeatConfig config = event.getRepeat_config_json();
        String unit = config.getUnit();
        int interval = config.getInterval();

        switch (unit) {
            case "day":
                long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(
                        event.getStart_date(), targetDate
                );
                return daysBetween % interval == 0;
            case "week":
                if (config.getDays() != null) {
                    int targetDayOfWeek = targetDate.getDayOfWeek().getValue() % 7;
                    if (!config.getDays().contains(targetDayOfWeek)) {
                        return false;
                    }
                }
                long weeksBetween = java.time.temporal.ChronoUnit.WEEKS.between(
                        event.getStart_date(), targetDate
                );
                return weeksBetween % interval == 0;
            case "month":
                long monthsBetween = java.time.temporal.ChronoUnit.MONTHS.between(
                        event.getStart_date().withDayOfMonth(1),
                        targetDate.withDayOfMonth(1)
                );
                return monthsBetween % interval == 0;
            default:
                return false;
        }
    }

    private static int calculateOccurrenceCount(Event event, LocalDate targetDate) {
        // 简化实现：计算从开始日期到目标日期的事件发生次数
        // 注意：实际实现可能需要更精确的计算
        switch (event.getRepeat_type()) {
            case "daily":
                long days = java.time.temporal.ChronoUnit.DAYS.between(
                        event.getStart_date(), targetDate
                );
                return (int) days + 1;
            case "weekly":
                long weeks = java.time.temporal.ChronoUnit.WEEKS.between(
                        event.getStart_date(), targetDate
                );
                return (int) weeks + 1;
            default:
                // 对于其他类型，返回一个足够大的值确保不会提前结束
                return Integer.MAX_VALUE;
        }
    }
}

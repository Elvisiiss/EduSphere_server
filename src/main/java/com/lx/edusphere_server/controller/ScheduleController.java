package com.lx.edusphere_server.controller;

import com.lx.edusphere_server.dto.*;
import com.lx.edusphere_server.dto.Schedule.*;
import com.lx.edusphere_server.entity.Event;
import com.lx.edusphere_server.entity.Memo;
import com.lx.edusphere_server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "*") // 实际应用中应该限制跨域来源
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    // 获取时间范围内的日程
    @PostMapping("/get_the_schedule_within_the_time_range")
    public List<Events> get_the_schedule_within_the_time_range(@RequestBody get_schedules_by_range get_schedules_by_range) {
        return scheduleService.get_the_schedule_within_the_time_range(get_schedules_by_range);
    }

    // 创建新日程
    @PostMapping("/create_a_new_schedule")
    public BaseResponse create_schedule(@RequestBody ChooseOneEvent ChooseOneEvent) {
        return scheduleService.create_schedule(ChooseOneEvent);
    }

    // 更新日程
    @PostMapping("/update_schedule")
    public BaseResponse update_schedule(@RequestBody ChooseOneEvent choose_one_event) {
        return scheduleService.update_schedule(choose_one_event);
    }

    // 获取特定日期的日程
    @PostMapping("/get_schedules_by_date")
    public List<Events> get_schedules_by_date(@RequestBody ChooseOneDay choose_one_day) {
        return scheduleService.get_schedules_by_date(choose_one_day);
    }

    // 完成事件
    @PostMapping("/finish_event")
    public BaseResponse finish_event(@RequestBody ChooseOneEvent choose_one_event) {
        return scheduleService.finish_event(choose_one_event);
    }

    // 删除日程
    @PostMapping("/delete_schedule")
    public BaseResponse delete_schedule(@RequestBody ChooseOneEvent choose_one_event) {
        return scheduleService.delete_schedule(choose_one_event);
    }

    // 取消日程
    @PostMapping("/cancel_event")
    public BaseResponse cancel_schedule(@RequestBody ChooseOneEvent choose_one_event) {
        return scheduleService.cancel_schedule(choose_one_event);
    }

    // 还原日程
    @PostMapping("/restore_schedule")
    public BaseResponse restore_schedule(@RequestBody ChooseOneEvent choose_one_event) {
        return scheduleService.restore_schedule(choose_one_event);
    }

    // 搜索事件通过关键词
    @PostMapping("/find_events_by_key_word")
    public List<Events> find_events_by_key_word(@RequestBody ChooseOneString choose_one_string) {
        return scheduleService.find_events_by_key_word(choose_one_string);
    }

    // 获取我的所有便签
    @PostMapping("/get_all_memo")
    public List<Memo> get_all_memo(@RequestBody OnlyToken only_token) {
        return scheduleService.get_all_memo(only_token);
    }

    // 修改我的某个便签
    @PostMapping("/update_memo")
    public BaseResponse update_memo(@RequestBody ChooseOneMemo choose_one_memo) {
        return scheduleService.update_memo(choose_one_memo);
    }

    // 删除我的某个便签
    @PostMapping("/delete_memo")
    public BaseResponse delete_memo(@RequestBody ChooseOneMemo choose_one_memo) {
        return scheduleService.delete_memo(choose_one_memo);
    }

    // 增加一个便签
    @PostMapping("/add_memo")
    public BaseResponse add_memo(@RequestBody ChooseOneMemo choose_one_memo) {
        return scheduleService.add_memo(choose_one_memo);
    }
}

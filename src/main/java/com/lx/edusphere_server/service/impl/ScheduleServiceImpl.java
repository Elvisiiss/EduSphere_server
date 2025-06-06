package com.lx.edusphere_server.service.impl;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.Schedule.*;
import com.lx.edusphere_server.entity.Event;
import com.lx.edusphere_server.entity.RepeatConfig;
import com.lx.edusphere_server.mapper.ScheduleMapper;
import com.lx.edusphere_server.mapper.UserMapper;
import com.lx.edusphere_server.service.ScheduleService;
import com.lx.edusphere_server.tools.CalculateTheNextTime;
import com.lx.edusphere_server.tools.ExcludeItemsFromTheListThatAreNotTargeted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleMapper scheduleMapper;
    private final UserMapper userMapper;

    @Autowired
    public ScheduleServiceImpl(ScheduleMapper scheduleMapper,
                               UserMapper userMapper) {
        this.scheduleMapper = scheduleMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Events> get_the_schedule_within_the_time_range(get_schedules_by_range get_schedules_by_range) {
        // 通过token获取用户ID
        Long user_id = userMapper.get_user_id_by_user_token(get_schedules_by_range.getUser_token());
        if (user_id == null) {
            return null;
        }

        // 获取时间范围内的日程事件
        List<Event> events = scheduleMapper.findEventsByUserIdAndDateRange(
                user_id,
                get_schedules_by_range.getStart_date(),
                get_schedules_by_range.getEnd_date()
        );

        // 转换为Events对象列表

        List<Events> result = new ArrayList<>();
        for (Event event : events) {
            event.setRepeat_config_json(RepeatConfig.fromJson(event.getRepeat_config()));
            Events e = new Events();
            e.setDate(event.getStart_date());
            e.setEvent(event);
            result.add(e);
        }

        return result;
    }

    @Override
    public BaseResponse create_schedule(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("创建日程失败");
        }
        if(choose_one_event.getRepeat_type().equals("none")){
            scheduleMapper.create_schedule(choose_one_event);
            return BaseResponse.success("创建日程成功");
        }
        choose_one_event.setRepeat_config_json(RepeatConfig.fromJson(choose_one_event.getRepeat_config()));
        LocalDate next_trigger_date = CalculateTheNextTime.next_time(choose_one_event.getStart_date(),choose_one_event.getRepeat_type(),choose_one_event.getRepeat_config_json());
        choose_one_event.setNext_trigger_date(next_trigger_date);
        scheduleMapper.create_schedule(choose_one_event);
        return BaseResponse.success("创建日程成功");
    }

    @Override
    public BaseResponse update_schedule(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("修改日程失败，无该用户");
        }
        if(!scheduleMapper.did_this_schedule_belong_this_user(choose_one_event.getEvent_id(), choose_one_event.getBelong_user())){
            return BaseResponse.error("修改日程失败，试图篡改其他用户日程");
        }
        choose_one_event.setRepeat_config_json(RepeatConfig.fromJson(choose_one_event.getRepeat_config()));
        LocalDate next_trigger_date = CalculateTheNextTime.next_time(choose_one_event.getStart_date(),choose_one_event.getRepeat_type(),choose_one_event.getRepeat_config_json());
        choose_one_event.setNext_trigger_date(next_trigger_date);
        scheduleMapper.update_schedule(choose_one_event);
        return BaseResponse.success("修改日程成功");
    }

    @Override
    public BaseResponse delete_schedule(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("删除日程失败，无该用户");
        }
        if(!scheduleMapper.did_this_schedule_belong_this_user(choose_one_event.getEvent_id(), choose_one_event.getBelong_user())){
            return BaseResponse.error("删除日程失败，试图篡改其他用户日程");
        }
        scheduleMapper.delete_schedule(choose_one_event);
        return BaseResponse.success("删除日程成功");
    }

    @Override
    public BaseResponse cancel_schedule(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("取消日程失败，无该用户");
        }
        if(!scheduleMapper.did_this_schedule_belong_this_user(choose_one_event.getEvent_id(), choose_one_event.getBelong_user())){
            return BaseResponse.error("取消日程失败，试图篡改其他用户日程");
        }
        scheduleMapper.cancel_schedule(choose_one_event);
        return BaseResponse.success("取消日程成功");
    }

    @Override
    public BaseResponse restore_schedule(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("还原日程失败，无该用户");
        }
        if(!scheduleMapper.did_this_schedule_belong_this_user(choose_one_event.getEvent_id(), choose_one_event.getBelong_user())){
            return BaseResponse.error("还原日程失败，试图篡改其他用户日程");
        }
        scheduleMapper.restore_schedule(choose_one_event);
        return BaseResponse.success("删除日程成功");
    }

    @Override
    public BaseResponse finish_event(ChooseOneEvent choose_one_event) {
        choose_one_event.setBelong_user(userMapper.get_user_id_by_user_token(choose_one_event.getUser_token()));
        if(choose_one_event.getBelong_user() == null){
            return BaseResponse.error("完成日程失败，无该用户");
        }
        if(!scheduleMapper.did_this_schedule_belong_this_user(choose_one_event.getEvent_id(), choose_one_event.getBelong_user())){
            return BaseResponse.error("完成日程失败，试图篡改其他用户日程");
        }
        Event event = scheduleMapper.get_one_event(choose_one_event.getEvent_id());
        event.setOccurrence_count(event.getOccurrence_count() + 1);
        event.setBelong_user(choose_one_event.getBelong_user());

        event.setStart_date(event.getNext_trigger_date());

        event.setRepeat_config_json(RepeatConfig.fromJson(event.getRepeat_config()));
        event.setNext_trigger_date(CalculateTheNextTime.next_time(event.getNext_trigger_date(),event.getRepeat_type(),event.getRepeat_config_json()));

        if(event.getEnd_type().equals("after_occurrences")){
            if(!event.getEnd_value().equals("1")){
                event.setEnd_value(String.valueOf(Integer.parseInt(event.getEnd_value()) - 1));
            }else{
                event.setNext_trigger_date(null);
            }
        }

        if(event.getNext_trigger_date() == null){
            scheduleMapper.en_completed_event(event.getEvent_id());
            return BaseResponse.success("完成日程成功");
        }

        if(event.getEnd_type().equals("on_date")){
            LocalDate targetDate = LocalDate.parse(event.getEnd_value(), DateTimeFormatter.ISO_LOCAL_DATE);
            if(event.getNext_trigger_date().isAfter(targetDate)){
                event.setNext_trigger_date(null);
            }
        }


        scheduleMapper.insert_repeat_event(event);
        scheduleMapper.en_completed_event(event.getEvent_id());
        return BaseResponse.success("完成日程成功");
    }

    @Override
    public List<Events> get_schedules_by_date(ChooseOneDay choose_one_day) {
        // 通过token获取用户ID
        Long user_id = userMapper.get_user_id_by_user_token(choose_one_day.getUser_token());
        if (user_id == null) {
            return null;
        }
        List<Event> event_or = new ArrayList<>();
        if(!choose_one_day.getIs_prediction()){
            // 获取规定时间的日程事件
            event_or =  scheduleMapper.get_schedules_by_date_without_prediction(user_id,choose_one_day.getDate());
        }else{
            List<Event> list_event = scheduleMapper.get_all_my_schedule(user_id);
            for (Event event : list_event) {
                event.setRepeat_config_json(RepeatConfig.fromJson(event.getRepeat_config()));
            }
            event_or =  ExcludeItemsFromTheListThatAreNotTargeted.exclude_items_from_the_list_that_are_not_targeted(list_event,choose_one_day.getDate());
        }
        List<Events> result = new ArrayList<>();
        LocalDate date = choose_one_day.getDate();
        for (Event event : event_or) {
            event.setRepeat_config_json(RepeatConfig.fromJson(event.getRepeat_config()));
            if(!event.getStart_date().equals(date)){
                event.setIs_prediction(true);
            }
            Events e = new Events();
            e.setDate(date);
            e.setEvent(event);
            result.add(e);
        }

        return result;
    }

    // 带字符串的
    @Override
    public List<Events> find_events_by_key_word(ChooseOneString choose_one_string) {
        // 通过token获取用户ID
        Long user_id = userMapper.get_user_id_by_user_token(choose_one_string.getUser_token());
        if (user_id == null) {
            return null;
        }
        List<Event> events = null;
        String a = "不指定日程";
        if(choose_one_string.getDate()!=null){
            // 获取某个规定日期的日程事件
            events = scheduleMapper.get_one_day_event_with_str(
                    user_id,
                    choose_one_string.getDate(),
                    choose_one_string.getStr()
            );
            a = "指定某天";
        }

        if(choose_one_string.getStart_date()!=null){
            // 获取时间范围内的日程事件
            events = scheduleMapper.get_range_days_event_with_str(
                    user_id,
                    choose_one_string.getStart_date(),
                    choose_one_string.getEnd_date(),
                    choose_one_string.getStr()
            );
            a = "指定某个时间段";
        }
        if(a.equals("不指定日程")){
            events = scheduleMapper.get_all_days_event_with_str(
                    user_id,
                    choose_one_string.getStr()
            );
        }
        // 转换为Events对象列表
        List<Events> result = new ArrayList<>();
        for (Event event : events) {
            event.setRepeat_config_json(RepeatConfig.fromJson(event.getRepeat_config()));
            Events e = new Events();
            e.setDate(event.getStart_date());
            e.setEvent(event);
            result.add(e);
        }

        return result;
    }
}

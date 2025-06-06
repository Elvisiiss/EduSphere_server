package com.lx.edusphere_server.mapper;


import com.lx.edusphere_server.dto.Schedule.ChooseOneEvent;
import com.lx.edusphere_server.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<Event> findEventsByUserIdAndDateRange(
            @Param("user_id") Long user_id,
            @Param("start_date") LocalDate start_date,
            @Param("end_date") LocalDate end_date
    );

    void create_schedule(ChooseOneEvent ChooseOneEvent);

    Boolean did_this_schedule_belong_this_user(Long event_id, Long user_id);

    void update_schedule(ChooseOneEvent choose_one_event);

    void insert_repeat_event(Event event);

    void delete_schedule(ChooseOneEvent choose_one_event);

    Event get_one_event(Long eventId);

    void en_completed_event(Long event_id);

    List<Event> get_schedules_by_date_without_prediction(Long user_id, LocalDate date);

    List<Event> get_all_my_schedule(Long user_id);

    List<Event> get_one_day_event_with_str(Long user_id, LocalDate date, String str);

    List<Event> get_range_days_event_with_str(Long user_id, LocalDate start_date, LocalDate end_date, String str);

    List<Event> get_all_days_event_with_str(Long user_id, String str);

    void cancel_schedule(ChooseOneEvent choose_one_event);

    void restore_schedule(ChooseOneEvent choose_one_event);
}

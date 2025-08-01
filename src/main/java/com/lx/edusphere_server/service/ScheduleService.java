package com.lx.edusphere_server.service;

import com.lx.edusphere_server.dto.BaseResponse;
import com.lx.edusphere_server.dto.OnlyToken;
import com.lx.edusphere_server.dto.Schedule.*;
import com.lx.edusphere_server.entity.Event;
import com.lx.edusphere_server.entity.Memo;


import java.util.List;

public interface ScheduleService {

    List<Events> get_the_schedule_within_the_time_range(get_schedules_by_range get_schedules_by_range);

    BaseResponse create_schedule(ChooseOneEvent choose_one_event);

    BaseResponse update_schedule(ChooseOneEvent choose_one_event);

    BaseResponse delete_schedule(ChooseOneEvent choose_one_event);

    BaseResponse finish_event(ChooseOneEvent choose_one_event);

    List<Events> get_schedules_by_date(ChooseOneDay choose_one_day);

    List<Events> find_events_by_key_word(ChooseOneString choose_one_string);

    BaseResponse cancel_schedule(ChooseOneEvent choose_one_event);

    BaseResponse restore_schedule(ChooseOneEvent choose_one_event);

    List<Memo> get_all_memo(OnlyToken only_token);

    BaseResponse update_memo(ChooseOneMemo choose_one_memo);

    BaseResponse delete_memo(ChooseOneMemo choose_one_memo);

    BaseResponse add_memo(ChooseOneMemo choose_one_memo);
}

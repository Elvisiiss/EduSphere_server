package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.subject.ChooseOneSubject;
import com.lx.edusphere_server.entity.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper {
    void add_one_subject(ChooseOneSubject chooseOneSubject);

    List<Subject> get_all_subjects();

    void update_subject(ChooseOneSubject chooseOneSubject);

    Boolean is_there_a_class_that_has_this_course(Long subject_id);
    void delete_subject(Long subject_id);



}

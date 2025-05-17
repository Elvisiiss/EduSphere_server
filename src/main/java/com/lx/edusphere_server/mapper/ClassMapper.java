package com.lx.edusphere_server.mapper;

import com.lx.edusphere_server.dto.classes.ChooseOneClass;
import com.lx.edusphere_server.dto.classes.GetOneClass;
import com.lx.edusphere_server.entity.subject_teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface ClassMapper {
    void add_one_class(ChooseOneClass chooseOneClass);


    // 获取所有班级基本信息
    List<GetOneClass> selectAllClassesBasicInfo();

    // 获取指定班级的学生ID集合
    Set<Integer> selectStudentIdsByClassId(Long classId);

    // 获取指定班级的学科ID集合
    Set<Integer> selectSubjectIdsByClassId(Long classId);

    // 获取指定班级的学科教师关联信息
    Set<subject_teacher> selectSubjectTeachersByClassId(Long classId);

    void for_class_add_subject_teacher(Long class_id, Long teacher_id, Long subject_id);

    Long get_class_id_by_class_name(String class_name);

    void update_class(ChooseOneClass chooseOneClass);



    void delete_class_exam_scores(Long class_id);
    void delete_class_in_students(Long class_id);
    void delete_class_in_teacher(Long class_id);
    void delete_class(Long class_id);

}

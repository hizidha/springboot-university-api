package com.devland.exercise.universityapi.classroom.model.dto;

import com.devland.exercise.universityapi.course.model.dto.CourseResponseDTO;
import com.devland.exercise.universityapi.student.model.dto.StudentClassroomResponseDTO;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherResponseDTO;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomResponseDTO {
    private long id;
    private String classroomCode;
    private CourseResponseDTO course;
    private TeacherResponseDTO teacher;
    private List<StudentClassroomResponseDTO> students;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
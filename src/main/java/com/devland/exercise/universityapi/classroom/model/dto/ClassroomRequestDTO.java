package com.devland.exercise.universityapi.classroom.model.dto;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.course.model.Course;
import com.devland.exercise.universityapi.course.model.dto.CourseClassroomRequestDTO;
import com.devland.exercise.universityapi.student.model.Student;
import com.devland.exercise.universityapi.teacher.model.Teacher;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherClassroomRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomRequestDTO {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotNull(message = "Classroom code details are required")
    private String classroomCode;

    @Valid
    private CourseClassroomRequestDTO course;

    @Valid
    private TeacherClassroomRequestDTO teacher;

    private List<Long> studentIds;

    public Classroom convertToEntity(List<Student> students) {
        Course existingCourse = this.course.convertToEntity();
        Teacher existingTeacher = this.teacher.convertToEntity();

        return Classroom.builder()
                .id(this.id)
                .classroomCode(this.classroomCode)
                .course(existingCourse)
                .teacher(existingTeacher)
                .students(students)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
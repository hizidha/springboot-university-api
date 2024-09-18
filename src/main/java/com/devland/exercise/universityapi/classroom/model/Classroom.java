package com.devland.exercise.universityapi.classroom.model;

import com.devland.exercise.universityapi.classroom.model.dto.ClassroomResponseDTO;
import com.devland.exercise.universityapi.course.model.Course;
import com.devland.exercise.universityapi.course.model.dto.CourseResponseDTO;
import com.devland.exercise.universityapi.student.model.Student;
import com.devland.exercise.universityapi.student.model.dto.StudentClassroomResponseDTO;
import com.devland.exercise.universityapi.teacher.model.Teacher;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String classroomCode;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany
    private List<Student> students;

    public ClassroomResponseDTO convertToResponse() {
        CourseResponseDTO courseResponseDTO = this.course.convertToResponse();
        TeacherResponseDTO teacherResponseDTO = this.teacher.convertToResponse();
        List<StudentClassroomResponseDTO> studentResponseDTOs = this.students.stream()
                .map(student -> StudentClassroomResponseDTO.builder()
                        .id(student.getId())
                        .registrationNumber(student.getRegistrationNumber())
                        .name(student.getName())
                        .build())
                .collect(Collectors.toList());

        return ClassroomResponseDTO.builder()
                .id(this.id)
                .classroomCode(this.classroomCode)
                .course(courseResponseDTO)
                .teacher(teacherResponseDTO)
                .students(studentResponseDTOs)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
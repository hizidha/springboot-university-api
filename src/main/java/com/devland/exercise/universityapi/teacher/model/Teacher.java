package com.devland.exercise.universityapi.teacher.model;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherResponseDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String phoneNumber;
    private String nationalTeacherNumber;
    private String workUnit;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "teacher")
    private List<Classroom> classrooms;

    public TeacherResponseDTO convertToResponse() {
        return TeacherResponseDTO.builder()
                .id(this.id)
                .nationalTeacherNumber(this.nationalTeacherNumber)
                .workUnit(this.workUnit)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
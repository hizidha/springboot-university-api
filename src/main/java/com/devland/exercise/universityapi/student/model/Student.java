package com.devland.exercise.universityapi.student.model;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.student.model.dto.StudentClassroomResponseDTO;
import com.devland.exercise.universityapi.student.model.dto.StudentResponseDTO;
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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String registrationNumber;
    private String name;
    private String email;
    private String phoneNumber;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToMany(mappedBy = "students")
    private List<Classroom> classrooms;

    public StudentResponseDTO convertToResponse() {
        return StudentResponseDTO.builder()
                .id(this.id)
                .registrationNumber(this.registrationNumber)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public StudentClassroomResponseDTO convertToClassroomResponse() {
        return StudentClassroomResponseDTO.builder()
                .id(this.id)
                .registrationNumber(this.registrationNumber)
                .name(this.name)
                .build();
    }
}
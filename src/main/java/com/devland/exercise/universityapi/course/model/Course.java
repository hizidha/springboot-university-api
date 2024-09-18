package com.devland.exercise.universityapi.course.model;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.course.model.dto.CourseResponseDTO;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int credit;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "course")
    private List<Classroom> classrooms;

    public CourseResponseDTO convertToResponse() {
        return CourseResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .credit(this.credit)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
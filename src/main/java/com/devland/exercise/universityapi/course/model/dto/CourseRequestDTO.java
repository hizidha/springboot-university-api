package com.devland.exercise.universityapi.course.model.dto;

import com.devland.exercise.universityapi.course.model.Course;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    private long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotBlank(message = "Course Name is required")
    private String name;

    @Min(value = 0, message = "quantity must be positive number")
    @NotNull(message = "Course Credit is required")
    private int credit;

    public Course convertToEntity() {
        return Course.builder()
                .id(this.id)
                .name(this.name)
                .credit(this.credit)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
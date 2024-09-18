package com.devland.exercise.universityapi.course.model.dto;

import com.devland.exercise.universityapi.course.model.Course;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseClassroomRequestDTO {
    @Positive(message = "ID must be positive number or not zero")
    @NotNull(message = "ID is required")
    private long id;

    public Course convertToEntity() {
        return Course.builder()
                .id(this.id)
                .build();
    }
}
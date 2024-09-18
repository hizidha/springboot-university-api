package com.devland.exercise.universityapi.teacher.model.dto;

import com.devland.exercise.universityapi.teacher.model.Teacher;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherClassroomRequestDTO {
    @Positive(message = "ID must be positive number or not zero")
    @NotNull(message = "ID is required")
    private long id;

    public Teacher convertToEntity() {
        return Teacher.builder()
                .id(this.id)
                .build();
    }
}

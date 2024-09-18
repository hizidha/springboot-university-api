package com.devland.exercise.universityapi.teacher.model.dto;

import com.devland.exercise.universityapi.teacher.model.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {
    private long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotBlank(message = "National Teacher Number is required")
    @Pattern(regexp = "^[0-9]{15}$", message = "National Teacher is invalid, must be exactly 15 digits")
    private String nationalTeacherNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Work Unit is required")
    private String workUnit;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,14}$", message = "Phone Number is invalid, must be 10 to 14 characters long")
    private String phoneNumber;

    public Teacher convertToEntity() {
        return Teacher.builder()
                .id(this.id)
                .nationalTeacherNumber(this.nationalTeacherNumber)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
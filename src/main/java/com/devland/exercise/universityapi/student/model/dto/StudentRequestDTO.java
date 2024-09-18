package com.devland.exercise.universityapi.student.model.dto;

import com.devland.exercise.universityapi.student.model.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @NotBlank(message = "Registration Number is required")
    @Pattern(regexp = "^[0-9]{13}$", message = "Registration Number is invalid, must be exactly 13 digits")
    private String registrationNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^\\+?[0-9. ()-]{10,14}$", message = "Phone Number is invalid, must be 10 to 14 characters long")
    private String phoneNumber;

    public Student convertToEntity() {
        return Student.builder()
                .id(this.id)
                .registrationNumber(this.registrationNumber)
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
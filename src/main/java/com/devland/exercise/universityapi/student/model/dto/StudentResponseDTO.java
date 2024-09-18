package com.devland.exercise.universityapi.student.model.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private long id;
    private String registrationNumber;
    private String name;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
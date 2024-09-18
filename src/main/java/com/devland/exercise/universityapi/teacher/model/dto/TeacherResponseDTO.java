package com.devland.exercise.universityapi.teacher.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDTO {
    private long id;
    private String nationalTeacherNumber;
    private String workUnit;
    private String name;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
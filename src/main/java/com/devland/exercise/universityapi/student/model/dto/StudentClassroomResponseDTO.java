package com.devland.exercise.universityapi.student.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassroomResponseDTO {
    private long id;
    private String registrationNumber;
    private String name;
}

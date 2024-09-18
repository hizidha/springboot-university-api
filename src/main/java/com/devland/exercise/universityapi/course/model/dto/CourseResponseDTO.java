package com.devland.exercise.universityapi.course.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private long id;
    private String name;
    private int credit;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
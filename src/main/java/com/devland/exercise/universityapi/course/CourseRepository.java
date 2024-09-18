package com.devland.exercise.universityapi.course;

import com.devland.exercise.universityapi.course.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository <Course, Long> {
    Optional<Course> findByName(String name);

    @Query(value = "SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',?1,'%')) "
                    + "OR (c.credit >= ?2 AND c.credit >= ?3)")
    Page<Course> findAll(String name, int minCredit, int maxCredit, Pageable pageable);
}
package com.devland.exercise.universityapi.teacher;

import com.devland.exercise.universityapi.teacher.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    @Query("SELECT t FROM Teacher t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%',?1,'%')) " +
            "OR LOWER(t.nationalTeacherNumber) LIKE LOWER(CONCAT('%',?2,'%')) " +
            "OR LOWER(t.workUnit) LIKE LOWER(CONCAT('%',?3,'%'))")
    Page<Teacher> findAll(String name, String nationalNumber, String workUnit, Pageable pageable);
}
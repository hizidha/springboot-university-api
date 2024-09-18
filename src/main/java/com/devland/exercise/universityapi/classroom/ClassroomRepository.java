package com.devland.exercise.universityapi.classroom;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByClassroomCode(String classroomCode);

    Page<Classroom> findAllByClassroomCode(String classroomCode, Pageable pageable);
}
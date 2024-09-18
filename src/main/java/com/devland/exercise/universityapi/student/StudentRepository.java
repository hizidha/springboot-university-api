package com.devland.exercise.universityapi.student;

import com.devland.exercise.universityapi.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegistrationNumber(String registrationNumber);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',?1,'%'))" +
            " AND LOWER(s.registrationNumber) LIKE LOWER(CONCAT('%',?2,'%'))")
    Page<Student> findAll(String name, String registrationNumber, Pageable pageable);
}
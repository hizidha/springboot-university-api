package com.devland.exercise.universityapi.student;

import com.devland.exercise.universityapi.classroom.model.dto.ClassroomRequestDTO;
import com.devland.exercise.universityapi.student.exception.StudentAlreadyExistException;
import com.devland.exercise.universityapi.student.exception.StudentNotFoundException;
import com.devland.exercise.universityapi.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getStudentList(ClassroomRequestDTO classroomRequestDTO) {
        if (classroomRequestDTO.getStudentIds() != null && !classroomRequestDTO.getStudentIds().isEmpty()) {
            return classroomRequestDTO.getStudentIds().stream()
                    .map(this::getOneBy)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public Page<Student> findAll(String name, String registrationNumber, Pageable pageable) {
        return this.studentRepository.findAll(name, registrationNumber, pageable);
    }

    public Student getOneBy(long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
    }

    public Student create(Student newStudent) {
        Optional<Student> existingStudent = this.studentRepository.findByRegistrationNumber(newStudent.getRegistrationNumber());

        if (existingStudent.isPresent()) {
            throw new StudentAlreadyExistException("Student with registration number " + newStudent.getRegistrationNumber() + " already exist");
        }

        return this.studentRepository.save(newStudent);
    }

    public Student update(Student updatedStudent) {
        Student existingStudent = this.getOneBy(updatedStudent.getId());
        updatedStudent.setId(existingStudent.getId());

        return this.studentRepository.save(updatedStudent);
    }

    public void delete(long id) {
        this.studentRepository.deleteById(this.getOneBy(id).getId());
    }
}
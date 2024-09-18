package com.devland.exercise.universityapi.student;

import com.devland.exercise.universityapi.student.model.Student;
import com.devland.exercise.universityapi.student.model.dto.StudentRequestDTO;
import com.devland.exercise.universityapi.student.model.dto.StudentResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<Page<StudentResponseDTO>> getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "registration_number", defaultValue = "0") String registrationNumber,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Student> pageStudents = this.studentService.findAll(name, registrationNumber, pageable);
        Page<StudentResponseDTO> studentResponseDTOs = pageStudents.map(Student::convertToResponse);

        return ResponseEntity.ok(studentResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getOne(@PathVariable("id") long id) {
        Student existingStudent = this.studentService.getOneBy(id);
        StudentResponseDTO studentResponseDTO = existingStudent.convertToResponse();

        return ResponseEntity.ok(studentResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<StudentResponseDTO> create(
            @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        Student newStudent = studentRequestDTO.convertToEntity();
        Student savedStudent = this.studentService.create(newStudent);
        StudentResponseDTO studentResponseDTO = savedStudent.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(
            @PathVariable("id") long id,
            @RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        Student updatedStudent = studentRequestDTO.convertToEntity();
        updatedStudent.setId(id);

        Student savedStudent = this.studentService.update(updatedStudent);
        StudentResponseDTO studentResponseDTO = savedStudent.convertToResponse();

        return ResponseEntity.ok().body(studentResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.studentService.delete(id);

        return ResponseEntity.ok().build();
    }
}
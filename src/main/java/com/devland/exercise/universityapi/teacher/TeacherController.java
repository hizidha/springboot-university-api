package com.devland.exercise.universityapi.teacher;

import com.devland.exercise.universityapi.teacher.model.Teacher;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherRequestDTO;
import com.devland.exercise.universityapi.teacher.model.dto.TeacherResponseDTO;
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
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping()
    public ResponseEntity<Page<TeacherResponseDTO>> getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "national_number", defaultValue = "0") String nationalNumber,
            @RequestParam(value = "work_unit", defaultValue = "") String workUnit,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Teacher> pageTeachers = this.teacherService.findAll(name, nationalNumber, workUnit, pageable);
        Page<TeacherResponseDTO> teacherResponseDTOs = pageTeachers.map(Teacher::convertToResponse);

        return ResponseEntity.ok(teacherResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getOne(@PathVariable("id") long id) {
        Teacher existingTeacher = this.teacherService.getOneBy(id);
        TeacherResponseDTO teacherResponseDTO = existingTeacher.convertToResponse();

        return ResponseEntity.ok(teacherResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<TeacherResponseDTO> create(
            @RequestBody @Valid TeacherRequestDTO teacherRequestDTO) {
        Teacher newTeacher = teacherRequestDTO.convertToEntity();
        Teacher savedTeacher = this.teacherService.create(newTeacher);
        TeacherResponseDTO teacherResponseDTO = savedTeacher.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> update(
            @PathVariable("id") long id,
            @RequestBody @Valid TeacherRequestDTO teacherRequestDTO) {
        Teacher updatedTeacher = teacherRequestDTO.convertToEntity();
        updatedTeacher.setId(id);

        Teacher savedTeacher = this.teacherService.update(updatedTeacher);
        TeacherResponseDTO teacherResponseDTO = savedTeacher.convertToResponse();

        return ResponseEntity.ok().body(teacherResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.teacherService.delete(id);

        return ResponseEntity.ok().build();
    }
}
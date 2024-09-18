package com.devland.exercise.universityapi.classroom;

import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.classroom.model.dto.ClassroomRequestDTO;
import com.devland.exercise.universityapi.classroom.model.dto.ClassroomResponseDTO;
import com.devland.exercise.universityapi.student.StudentService;
import com.devland.exercise.universityapi.student.model.Student;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
    private final StudentService studentService;

    @GetMapping()
    public ResponseEntity<Page<ClassroomResponseDTO>> getAll(
            @RequestParam(value = "classroom_code") Optional<String> optionalClassroomCode,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "3") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Classroom> pageClassrooms = this.classroomService.findAll(optionalClassroomCode, pageable);
        Page<ClassroomResponseDTO> classroomResponseDTOs = pageClassrooms.map(Classroom::convertToResponse);

        return ResponseEntity.ok(classroomResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> getOne(@PathVariable("id") long id) {
        Classroom existingClassroom = this.classroomService.getOneBy(id);
        ClassroomResponseDTO classroomResponseDTO = existingClassroom.convertToResponse();

        return ResponseEntity.ok(classroomResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<ClassroomResponseDTO> create(
            @RequestBody @Valid ClassroomRequestDTO classroomRequestDTO) {
        List<Student> students = this.studentService.getStudentList(classroomRequestDTO);
        Classroom newClassroom = classroomRequestDTO.convertToEntity(students);

        Classroom savedClassroom = classroomService.create(newClassroom);
        ClassroomResponseDTO classroomResponseDTO = savedClassroom.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(classroomResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> update(
            @PathVariable("id") long id,
            @RequestBody @Valid ClassroomRequestDTO classroomRequestDTO) {
        List<Student> students = this.studentService.getStudentList(classroomRequestDTO);
        Classroom updatedClassroom = classroomRequestDTO.convertToEntity(students);
        updatedClassroom.setId(id);

        Classroom savedClassroom = this.classroomService.update(updatedClassroom);
        ClassroomResponseDTO classroomResponseDTO = savedClassroom.convertToResponse();

        return ResponseEntity.ok().body(classroomResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.classroomService.delete(id);

        return ResponseEntity.ok().build();
    }
}
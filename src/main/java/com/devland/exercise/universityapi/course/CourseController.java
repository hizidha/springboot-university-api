package com.devland.exercise.universityapi.course;

import com.devland.exercise.universityapi.course.model.Course;
import com.devland.exercise.universityapi.course.model.dto.CourseRequestDTO;
import com.devland.exercise.universityapi.course.model.dto.CourseResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping()
    public ResponseEntity<Page<CourseResponseDTO>> getAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "min_credit", defaultValue = "0") Integer minCredit,
            @RequestParam(value = "max_credit", defaultValue = "6") Integer maxCredit,
            @RequestParam(value = "sort", defaultValue = "ASC") String sortString,
            @RequestParam(value = "order_by", defaultValue = "id") String orderBy,
            @RequestParam(value = "limit", defaultValue = "5") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortString.toUpperCase()), orderBy);
        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Course> pageCourses = this.courseService.findAll(name, minCredit, maxCredit, pageable);
        Page<CourseResponseDTO> courseResponseDTOs = pageCourses.map(Course::convertToResponse);

        return ResponseEntity.ok(courseResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getOne(@PathVariable("id") long id) {
        Course existingCourse = this.courseService.getOneBy(id);
        CourseResponseDTO courseResponseDTO = existingCourse.convertToResponse();

        return ResponseEntity.ok(courseResponseDTO);
    }

    @PostMapping()
    public ResponseEntity<CourseResponseDTO> create(
            @RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        Course newCourse = courseRequestDTO.convertToEntity();
        Course courseCourse = this.courseService.create(newCourse);
        CourseResponseDTO courseResponseDTO = courseCourse.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(
            @PathVariable("id") long id,
            @RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        Course updatedCourse = courseRequestDTO.convertToEntity();
        updatedCourse.setId(id);

        Course savedCourse = this.courseService.update(updatedCourse);
        CourseResponseDTO courseResponseDTO = savedCourse.convertToResponse();

        return ResponseEntity.ok().body(courseResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        this.courseService.delete(id);

        return ResponseEntity.ok().build();
    }
}
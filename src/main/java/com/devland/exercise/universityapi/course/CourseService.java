package com.devland.exercise.universityapi.course;

import com.devland.exercise.universityapi.course.exception.CourseAlreadyExistException;
import com.devland.exercise.universityapi.course.exception.CourseNotFoundException;
import com.devland.exercise.universityapi.course.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public Page<Course> findAll(String name, Integer minCredit, Integer maxCredit, Pageable pageable) {
        return this.courseRepository.findAll(name, minCredit, maxCredit, pageable);
    }

    public Course getOneBy(long id) {
        return this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with ID " + id + " not found"));
    }

    public Course create(Course newCourse) {
        Optional<Course> existingCourse = this.courseRepository.findByName(newCourse.getName());

        if (existingCourse.isPresent()) {
            throw new CourseAlreadyExistException("Course with name " + newCourse.getName() + " already exist");
        }

        return this.courseRepository.save(newCourse);
    }

    public Course update(Course updatedCourse) {
        Course existingCourse = this.getOneBy(updatedCourse.getId());
        updatedCourse.setId(existingCourse.getId());

        return this.courseRepository.save(updatedCourse);
    }

    public void delete(long id) {
        this.courseRepository.deleteById(this.getOneBy(id).getId());
    }
}
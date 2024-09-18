package com.devland.exercise.universityapi.classroom;

import com.devland.exercise.universityapi.classroom.exception.ClassroomNotFoundException;
import com.devland.exercise.universityapi.classroom.model.Classroom;
import com.devland.exercise.universityapi.course.CourseService;
import com.devland.exercise.universityapi.course.exception.CourseAlreadyExistException;
import com.devland.exercise.universityapi.teacher.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public Page<Classroom> findAll(Optional<String> optionalClassroomCode, Pageable pageable) {
        if (optionalClassroomCode.isPresent()) {
            return this.classroomRepository.findAllByClassroomCode(optionalClassroomCode.get(), pageable);
        }
        return this.classroomRepository.findAll(pageable);
    }

    public Classroom getOneBy(long id) {
        return this.classroomRepository.findById(id)
                .orElseThrow(() -> new ClassroomNotFoundException("Classroom with ID " + id + " not found"));
    }

    public Classroom create(Classroom newClassroom) {
        Optional<Classroom> existingClassroom = this.classroomRepository.findByClassroomCode(newClassroom.getClassroomCode());

        if (existingClassroom.isPresent()) {
            throw new CourseAlreadyExistException("Classroom with code " + newClassroom.getClassroomCode() + " already exist");
        }

        newClassroom.setCourse(this.courseService.getOneBy(newClassroom.getCourse().getId()));
        newClassroom.setTeacher(this.teacherService.getOneBy(newClassroom.getTeacher().getId()));

        return this.classroomRepository.save(newClassroom);
    }

    public Classroom update(Classroom updatedClassroom) {
        Classroom existingClassroom = this.getOneBy(updatedClassroom.getId());
        updatedClassroom.setId(existingClassroom.getId());

        return this.classroomRepository.save(updatedClassroom);
    }

    public void delete(long id) {
        this.classroomRepository.deleteById(this.getOneBy(id).getId());
    }
}
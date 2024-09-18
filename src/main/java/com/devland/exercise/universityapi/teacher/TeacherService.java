package com.devland.exercise.universityapi.teacher;

import com.devland.exercise.universityapi.teacher.exception.TeacherAlreadyExistException;
import com.devland.exercise.universityapi.teacher.exception.TeacherNotFoundException;
import com.devland.exercise.universityapi.teacher.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public Page<Teacher> findAll(String name, String nationalNumber, String workUnit, Pageable pageable) {
        return this.teacherRepository.findAll(name, nationalNumber, workUnit, pageable);
    }

    public Teacher getOneBy(long id) {
        return this.teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher with ID " + id + " not found"));
    }

    public Teacher create(Teacher newTeacher) {
        Optional<Teacher> existingTeacher = this.teacherRepository.findByEmail(newTeacher.getEmail());

        if (existingTeacher.isPresent()) {
            throw new TeacherAlreadyExistException("Teacher with email " + newTeacher.getEmail() + " already exist");
        }
        return this.teacherRepository.save(newTeacher);
    }

    public Teacher update(Teacher updatedTeacher) {
        Teacher existingTeacher = this.getOneBy(updatedTeacher.getId());
        updatedTeacher.setId(existingTeacher.getId());

        return this.teacherRepository.save(updatedTeacher);
    }

    public void delete(long id) {
        this.teacherRepository.deleteById(this.getOneBy(id).getId());
    }
}
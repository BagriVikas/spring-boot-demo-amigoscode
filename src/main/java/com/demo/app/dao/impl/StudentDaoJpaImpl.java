package com.demo.app.dao.impl;

import com.demo.app.dao.StudentDAO;
import com.demo.app.dao.StudentRepository;
import com.demo.app.entity.Student;
import com.demo.app.exception.ResourceAlreadyExistsException;
import com.demo.app.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StudentDaoJpaImpl implements StudentDAO {

    private final StudentRepository studentRepository;
    @Override
    public Long saveStudent(Student student) {
        return studentRepository.save(student).getId();
    }

    @Override
    public Student getStudent(Long id) {
        return checkForStudent(id);
    }

    @Override
    public Long updateStudent(Student student) {
        return studentRepository.save(student).getId();
    }

    @Override
    public String deleteStudent(Student student) {
        studentRepository.delete(student);
        return "Student deleted successfully";
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void checkForEmail(String email) {
        Boolean emailExists = studentRepository.existsStudentByEmail(email);
        if (emailExists) {
            throw new ResourceAlreadyExistsException("Email already taken");
        }
    }

    @Override
    public Student checkForStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id %s does not exist".formatted(id)));
    }
}

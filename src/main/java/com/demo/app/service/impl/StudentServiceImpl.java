package com.demo.app.service.impl;

import com.demo.app.dto.StudentDataUpdate;
import com.demo.app.dto.StudentRegistrationData;
import com.demo.app.entity.Student;
import com.demo.app.exception.NoChangesFoundException;
import com.demo.app.exception.ResourceAlreadyExistsException;
import com.demo.app.exception.ResourceNotFoundException;
import com.demo.app.repository.StudentRepository;
import com.demo.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Long saveStudent(StudentRegistrationData studentRegistrationData) {

        Boolean emailExists = studentRepository.existsStudentByEmail(studentRegistrationData.email());
        if (emailExists) {
            throw new ResourceAlreadyExistsException("Email already taken");
        }
        return studentRepository.save(new Student(studentRegistrationData)).getId();

    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id %s does not exist".formatted(id)));
    }

    @Override
    public Long updateStudent(Long id, StudentDataUpdate studentDataUpdate) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id %s does not exist".formatted(id)));
        boolean hasChanges = false;

        if (Objects.nonNull(studentDataUpdate.name()) && !student.getName().equals(studentDataUpdate.name())) {
            student.setName(studentDataUpdate.name());
            hasChanges = true;
        }

        if (Objects.nonNull(studentDataUpdate.email()) && !student.getEmail().equals(studentDataUpdate.email())) {
            student.setEmail(studentDataUpdate.email());
            hasChanges = true;
        }

        if (Objects.nonNull(studentDataUpdate.age()) && !student.getAge().equals(studentDataUpdate.age())) {
            student.setAge(studentDataUpdate.age());
            hasChanges = true;
        }

        if (!hasChanges) {
            throw new NoChangesFoundException("No changes found");
        }

        return studentRepository.save(student).getId();

    }

    @Override
    public String deleteStudent(Long id) {

        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id %s does not exist".formatted(id)));
        studentRepository.delete(student);
        return "Student deleted successfully";

    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}

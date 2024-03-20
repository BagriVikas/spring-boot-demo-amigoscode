package com.demo.app.service;

import com.demo.app.dao.StudentDAO;
import com.demo.app.dto.StudentDataUpdate;
import com.demo.app.dto.StudentRegistrationData;
import com.demo.app.entity.Student;
import com.demo.app.exception.NoChangesFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentDAO studentDAO;

    public Long saveStudent(StudentRegistrationData studentRegistrationData) {

        studentDAO.checkForEmail(studentRegistrationData.email());
        Student student = new Student(studentRegistrationData.name(), studentRegistrationData.email(), studentRegistrationData.age());
        return studentDAO.saveStudent(student);

    }

    public Student getStudent(Long id) {

        Student student = studentDAO.checkForStudent(id);
        return student;

    }

    public Long updateStudent(Long id, StudentDataUpdate studentDataUpdate) {

        Student student = studentDAO.checkForStudent(id);
        boolean hasChanges = false;

        if (Objects.nonNull(studentDataUpdate.name()) && !student.getName().equals(studentDataUpdate.name())) {
            student.setName(studentDataUpdate.name());
            hasChanges = true;
        }

        if (Objects.nonNull(studentDataUpdate.email()) && !student.getEmail().equals(studentDataUpdate.email())) {
            studentDAO.checkForEmail(studentDataUpdate.email());
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

        return studentDAO.updateStudent(student);

    }

    public String deleteStudent(Long id) {

        Student student = studentDAO.checkForStudent(id);
        return studentDAO.deleteStudent(student);

    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

}

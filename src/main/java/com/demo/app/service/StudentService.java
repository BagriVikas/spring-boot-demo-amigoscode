package com.demo.app.service;

import com.demo.app.dto.StudentDataUpdate;
import com.demo.app.dto.StudentRegistrationData;
import com.demo.app.entity.Student;

import java.util.List;

public interface StudentService {

    Long saveStudent(StudentRegistrationData studentRegistrationData);

    Student getStudent(Long id);

    Long updateStudent(Long id, StudentDataUpdate studentDataUpdate);

    String deleteStudent(Long id);

    List<Student> getAllStudents();

}

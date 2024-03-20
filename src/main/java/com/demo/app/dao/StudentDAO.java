package com.demo.app.dao;

import com.demo.app.entity.Student;

import java.util.List;

public interface StudentDAO {

    Long saveStudent(Student student);

    Student getStudent(Long id);

    Long updateStudent(Student student);

    String deleteStudent(Student student);

    List<Student> getAllStudents();

    void checkForEmail(String email);

    Student checkForStudent(Long id);

}

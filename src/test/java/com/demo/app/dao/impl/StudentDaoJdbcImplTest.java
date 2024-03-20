package com.demo.app.dao.impl;

import com.demo.app.AbstractTestContainers;
import com.demo.app.entity.Student;
import com.demo.app.jdbc.utils.StudentRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class StudentDaoJdbcImplTest extends AbstractTestContainers {

    private StudentDaoJdbcImpl studentDaoJdbcImpl;
    private final StudentRowMapper studentRowMapper = new StudentRowMapper();

    @BeforeEach
    void setUp() {
        // setting up fresh new object before executing any test
        // so that we get fresh and clean object of 'StudentDaoJdbcImpl'
        // for each test case
        // which ensures that even if any test case changes the state of
        // 'studentDaoJdbcImpl' in any way possible
        // then it won't be affecting any other test case
        studentDaoJdbcImpl = new StudentDaoJdbcImpl(getJdbcTemplate(), studentRowMapper);
    }

    @Test
    void saveStudent() {
    }

    @Test
    void getStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getAllStudents() {

        Student student = new Student(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress(),
                new Random().nextInt(21, 67)
        );

        studentDaoJdbcImpl.saveStudent(student);

        List<Student> students = studentDaoJdbcImpl.getAllStudents();

        assertThat(students).isNotEmpty();

    }
}
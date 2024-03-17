package com.demo.app.service.impl;

import com.demo.app.dto.StudentDataUpdate;
import com.demo.app.dto.StudentRegistrationData;
import com.demo.app.entity.Student;
import com.demo.app.exception.ResourceAlreadyExistsException;
import com.demo.app.exception.ResourceNotFoundException;
import com.demo.app.jdbc.utils.StudentRowMapper;
import com.demo.app.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("studentServiceJDBC")
public class StudentServiceJDBCImpl implements StudentService {

    private final JdbcTemplate jdbcTemplate;

    private final StudentRowMapper studentRowMapper;

    @Override
    public Long saveStudent(StudentRegistrationData studentRegistrationData) {

        String checkEmailSql = """
                SELECT id, name, email, age FROM student
                WHERE email = ?
                """;
        List<Student> students = jdbcTemplate.query(checkEmailSql, studentRowMapper, studentRegistrationData.email());
        if (!students.isEmpty()) {
            throw new ResourceAlreadyExistsException("Email already taken");
        }

        String sql = """
                INSERT INTO student( name, email, age )
                VALUES( ?, ?, ? )
                """;
        jdbcTemplate.update(sql, studentRegistrationData.name(), studentRegistrationData.email(), studentRegistrationData.age());
        return 1L; // returning random value

    }

    @Override
    public Student getStudent(Long id) {

        String sql = """
                SELECT id, name, email, age FROM student
                WHERE id = ?
                """;
        List<Student> students = jdbcTemplate.query(sql, studentRowMapper, id);
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("Student with %s does not exist".formatted(id));
        }
        return students.get(0);

    }

    @Override
    public Long updateStudent(Long id, StudentDataUpdate studentDataUpdate) {
        return null;
    }

    @Override
    public String deleteStudent(Long id) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {

        String sql = """
                SELECT id, name, email, age FROM student
                """;
        return jdbcTemplate.query(sql, studentRowMapper);

    }
}

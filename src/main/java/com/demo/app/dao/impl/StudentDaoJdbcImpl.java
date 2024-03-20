package com.demo.app.dao.impl;

import com.demo.app.dao.StudentDAO;
import com.demo.app.entity.Student;
import com.demo.app.exception.ResourceAlreadyExistsException;
import com.demo.app.exception.ResourceNotFoundException;
import com.demo.app.jdbc.utils.StudentRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Primary
public class StudentDaoJdbcImpl implements StudentDAO {

    private final JdbcTemplate jdbcTemplate;

    private final StudentRowMapper studentRowMapper;

    @Override
    public Long saveStudent(Student student) {

        String sql = """
                INSERT INTO student( name, email, age )
                VALUES( ?, ?, ? )
                """;
        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge());
        return 1L; // returning random value
    }

    @Override
    public Student getStudent(Long id) {
        return checkForStudent(id);
    }

    @Override
    public Long updateStudent(Student student) {

        boolean hasChanges = false;

        String sql = """
                UPDATE student
                SET name = ?, email = ?, age = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getAge(), student.getId());

        return 1L; // garbage value
    }

    @Override
    public String deleteStudent(Student student) {
        String sql = """
                DELETE FROM student WHERE id = ?
                """;
        jdbcTemplate.update(sql, student.getId());
        return "Student with id %s is deleted successfully".formatted(student.getId());
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = """
                SELECT id, name, email, age FROM student
                """;
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public void checkForEmail(String email) {

        String checkEmailSql = """
                SELECT COUNT( id ) FROM student
                WHERE email = ?
                """;
        long count = jdbcTemplate.queryForObject(checkEmailSql, Long.class, email);
        if (count > 0) {
            throw new ResourceAlreadyExistsException("Email already taken");
        }

    }

    @Override
    public Student checkForStudent(Long id) {

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

}

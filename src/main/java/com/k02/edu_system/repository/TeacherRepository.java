package com.k02.edu_system.repository;

import com.k02.edu_system.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeacherRepository {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Teacher> mapper = (resultSet, rowNum) ->
            Teacher.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .build();

    public List<Teacher> findAll() {
        return jdbcTemplate.query("SELECT * FROM teacher ORDER BY name", mapper);
    }

    public Teacher findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM teacher WHERE id = ?", mapper, id);
    }

    public int save(Teacher teacher) {
        return jdbcTemplate.update(
                "INSERT INTO teacher (name) VALUES (?)", teacher.getName()
        );
    }

    public int update(Teacher teacher) {
        return jdbcTemplate.update(
                "UPDATE teacher SET name = ? WHERE id = ?", teacher.getName(), teacher.getId()
        );
    }

    public int deleteById(int id){
        return jdbcTemplate.update(
                "DELETE FROM teacher WHERE id = ?", id);
    }
}
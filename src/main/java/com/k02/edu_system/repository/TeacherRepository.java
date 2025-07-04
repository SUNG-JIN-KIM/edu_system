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

    public int save(Teacher teacher){
        return jdbcTemplate.update(
                "INSERT INTO teacher (name) VALUES (?)", teacher.getName()
        );
    }
}

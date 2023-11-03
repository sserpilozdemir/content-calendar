package com.serpilozdemir.
 contentcalendar.repository;


import com.serpilozdemir.contentcalendar.model.Content;
import com.serpilozdemir.contentcalendar.model.Status;
import com.serpilozdemir.contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;


    public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Content mapRow(ResultSet resultSet, int rowNum) throws SQLException {//this throwing exception is not necessary | just code readable
        return new Content(resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getString("desc"),
                Status.valueOf(resultSet.getString("status")),
                Type.valueOf(resultSet.getString("content-type")),
                resultSet.getObject("date_created", LocalDateTime.class),
                resultSet.getObject("data_updated", LocalDateTime.class),
                resultSet.getString("url")
                );
    }

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        List<Content> contents = jdbcTemplate.query(sql, (resultSet, rowNum) -> ContentJdbcTemplateRepository.mapRow(resultSet, rowNum));
        return contents;
    }

    public void createContent(String title, String desc, Status status, Type content_type, String URL){
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status, content_type, URL);
    }


    public void updateContent(int id, String title, String desc, Status status, Type content_type, String URL){
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id?";
        jdbcTemplate.update(sql, id, title, desc, status, content_type, URL);
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
    public Content getContent(int id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        Content content = jdbcTemplate.queryForObject(sql, new Object[]{id},  (resultSet, rowNum) -> ContentJdbcTemplateRepository.mapRow(resultSet, rowNum));
        return content;
    }

}

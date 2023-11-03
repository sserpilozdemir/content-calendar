package com.serpilozdemir.contentcalendar.repository;

import com.serpilozdemir.contentcalendar.model.Content;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ContentRepository  extends ListCrudRepository<Content, Integer> {
    List<Content> findAllByTitleContains(String type);

//    @Query("""
//        SELECT * FROM Content
//        WHERE status = :status
//""")
//    List<Content> listByStatus(@Param("status")Status status);

}

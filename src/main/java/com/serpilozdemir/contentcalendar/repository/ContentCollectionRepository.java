package com.serpilozdemir.contentcalendar.repository;


import com.serpilozdemir.contentcalendar.model.Content;
import com.serpilozdemir.contentcalendar.model.Status;
import com.serpilozdemir.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();


    public ContentCollectionRepository() {
    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        contentList.removeIf(s -> s.id().equals(content.id()));
        contentList.add(content);
    }


    @PostConstruct
    private void init() {
        Content content = new Content(
                1,
                "My First Blog",
                "Mersenne Twister",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;

    }


    public void delete(Integer id){
        contentList.removeIf(s -> s.id().equals(id));
    }
}

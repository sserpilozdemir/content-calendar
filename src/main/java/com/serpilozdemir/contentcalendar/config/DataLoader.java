package com.serpilozdemir.contentcalendar.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.serpilozdemir.contentcalendar.model.Content;
import com.serpilozdemir.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running DataLoader...");
        if(repository.count() == 0) {
            System.out.println("Attempting to load data...");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
                if (inputStream == null) {
                    System.out.println("Cannot find /data/content.json");
                    return;
                }
                System.out.println("Reading JSON data...");
                repository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
                System.out.println("Data loaded successfully!");
            }
        } else {
            System.out.println("Data already exists. Skipping data loading.");
        }
    }


}

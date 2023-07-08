package org.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * A component to read the json files which are the source of truth.
 */
@Component
public class JsonFileResourceReader {

    private final ResourceLoader resourceLoader;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public JsonFileResourceReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * A function taking in the filepath relative to classpath and returning a response of given type target class
     * @param filepath
     * @param targetType
     * @return an object of type target type with data parsed from json file
     * @param <T>
     * @throws IOException
     */
    public <T> T readFile(String filepath, Class<T> targetType) throws IOException {
        String filePath = "classpath:"+filepath; // Path to your JSON file in the resource folder
        Resource resource = resourceLoader.getResource(filePath);
        return objectMapper.readValue(resource.getInputStream(), targetType);

    }
}

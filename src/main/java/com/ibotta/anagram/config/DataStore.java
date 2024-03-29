package com.ibotta.anagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

@Configuration
public class DataStore {

    @Bean
    public List<String> dictionary() throws IOException {
        List<String> dictionary = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("dictionary.txt"));
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                dictionary.add(line);
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
        } finally {
            bufferedReader.close();
        }
        List<String> threadSafeDictionary = createThreadSafeDataStructure(dictionary);

        return threadSafeDictionary;
    }

    private List<String> createThreadSafeDataStructure(List<String> dictionary) {
        return synchronizedList(dictionary);
    }
}

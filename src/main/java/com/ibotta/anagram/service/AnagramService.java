package com.ibotta.anagram.service;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class AnagramService {

    private List<String> dictionary;

    @Autowired
    public AnagramService(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public ResponseEntity<AddWordsResponse> addWords(AddWordsRequest request) {
        List<String> wordsToAdd = request.getWords();
        wordsToAdd.stream()
                .forEach(word -> {
                    if(!dictionary.contains(word))
                        dictionary.add(word);
                });
        System.out.println("dictionary = " + dictionary);
        return ResponseEntity.created(null).build();
    }

    public AnagramsFoundResponse findAnagrams(String word) {
        AnagramsFoundResponse response = new AnagramsFoundResponse(asList("hi"));

        return response;
    }

    public AnagramsFoundResponse deleteWord(String word) {
        AnagramsFoundResponse response = new AnagramsFoundResponse(asList("hi"));

        return response;
    }
    public AnagramsFoundResponse deleteAll(String word) {
        AnagramsFoundResponse response = new AnagramsFoundResponse(asList("hi"));

        return response;
    }
}

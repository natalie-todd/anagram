package com.ibotta.anagram.service;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public String alphabetizeString(String word) {
        char alphabetizedWordArray[] = word.toCharArray();
        Arrays.sort(alphabetizedWordArray);
        return new String(alphabetizedWordArray);
    }

    public AnagramsFoundResponse findAnagrams(String word) {
        String alphabetizedWord = alphabetizeString(word);

        List<String> anagrams = dictionary.stream()
                .filter(entry -> alphabetizeString(entry) == alphabetizedWord)
                .collect(Collectors.toList());

        AnagramsFoundResponse response = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(anagrams).build();

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

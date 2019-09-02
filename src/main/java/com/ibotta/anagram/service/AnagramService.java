package com.ibotta.anagram.service;

import com.ibotta.anagram.controller.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import com.ibotta.anagram.model.builder.CountResponseBuilder;
import com.ibotta.anagram.utilities.AnagramHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ibotta.anagram.utilities.AnagramHelper.limitIt;
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
                    if (!dictionary.contains(word))
                        dictionary.add(word);
                });
        return ResponseEntity.created(null).build();
    }

    private String alphabetizeString(String word) {
        char alphabetizedWordArray[] = word.toCharArray();
        Arrays.sort(alphabetizedWordArray);
        return new String(alphabetizedWordArray);
    }

    public AnagramsFoundResponse findAnagrams(String word, Integer limit) {
        String alphabetizedWord = alphabetizeString(word);

        List<String> anagrams = dictionary.stream()
                .filter(entry -> alphabetizeString(entry).equalsIgnoreCase(alphabetizedWord))
                .filter(entry -> !word.equalsIgnoreCase(entry))
                .collect(Collectors.toList());

        AnagramsFoundResponse response = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(limitIt(anagrams, limit)).build();

        return response;
    }


    public ResponseEntity deleteWord(String word) {

        dictionary.remove(word);

        return ResponseEntity.noContent().build();
    }
//
    public ResponseEntity deleteAll() {
        dictionary.clear();
        return ResponseEntity.noContent().build();
    }

    public CountResponse countWords() {

        int length = dictionary.size();

        CountResponse response = CountResponseBuilder.countResponseBuilder().corpusTotal(length).build();

        return response;
    }
}

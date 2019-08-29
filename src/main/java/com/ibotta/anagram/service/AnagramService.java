package com.ibotta.anagram.service;

import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import com.ibotta.anagram.model.builder.CountResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    private List<String> dictionary;

    @Autowired
    public AnagramService(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public ResponseEntity addWords(AddWordsRequest request) {
        List<String> wordsToAdd = request.getWords();
        wordsToAdd.stream()
                .forEach(word -> {
                    if (!dictionary.contains(word))
                        dictionary.add(word);
                });
        return ResponseEntity.created(null).build();
    }

    public String alphabetizeString(String word) {
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

        ArrayList<String> myList = new ArrayList<String>();
        if ((limit != null) && (dictionary.size() > 0)) {
            int i = 0;
            do {
                i++;
                myList.add(anagrams.get(i));
            } while (i < limit);
        } else if ((limit == null) && (dictionary.size() > 0)) {
            myList = new ArrayList<String>(anagrams);
        } else {
            myList = new ArrayList<String>();
        }

        AnagramsFoundResponse response = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(myList).build();

        return response;
    }

    public ResponseEntity deleteWord(String word) {

        dictionary.remove(word);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity deleteAll() {

        dictionary.removeAll(dictionary);

        return ResponseEntity.noContent().build();
    }

    public CountResponse countWords() {

        int length = dictionary.size();

        CountResponse response = CountResponseBuilder.countResponseBuilder().corpusTotal(length).build();

        return response;
    }
}

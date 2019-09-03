package com.ibotta.anagram.service;

import com.ibotta.anagram.controller.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import com.ibotta.anagram.model.builder.CountResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ibotta.anagram.utilities.AnagramHelper.limitIt;

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

    public ResponseEntity deleteAll() {
        dictionary.clear();
        return ResponseEntity.noContent().build();
    }

    private Integer median(List<Integer> clone) {
        Integer median;
        Integer index;
        List<Integer> sorted = clone.stream().sorted().collect(Collectors.toList());
        Integer length = sorted.size();

        if (length % 2 == 0) {
            index = (length / 2) + (length - 1) / 2;
            median = clone.get(index);
        } else {
            index = (length - 1) / 2;
            median = sorted.get(index);
        }
        return median;
    }

    public CountResponse countWords() {

        int length = dictionary.size();
        List<Integer> clone = dictionary.stream().map(str -> str.length()).collect(Collectors.toList());

        Integer min = clone.stream().findFirst().orElse(0);

        Integer max = clone.get(clone.size() - 1);

        Integer sum = clone.stream().sorted().map(str -> str).reduce((x, y) -> x + y).orElse(0);

        Integer average = sum / length;

        CountResponse response = CountResponseBuilder.countResponseBuilder()
                .corpusTotal(length)
                .min(min)
                .max(max)
                .median(median(clone))
                .average(average).build();

        return response;
    }

    public ResponseEntity deleteAnagrams(String word) {
        String alphabetizedWord = alphabetizeString(word);

        List<String> wordAndAnagrams = dictionary.stream()
                .filter(entry -> alphabetizeString(entry).equalsIgnoreCase(alphabetizedWord))
                .collect(Collectors.toList());

        dictionary.removeAll(wordAndAnagrams);

        return ResponseEntity.noContent().build();
    }
}

package com.ibotta.anagram.controller;

import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.GroupResponse;
import com.ibotta.anagram.service.AnagramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.stereotype.Controller
@Validated
public class WordsController {
    private AnagramService anagramService;

    @Autowired
    public WordsController(
            AnagramService anagramService
    ) {
        this.anagramService = anagramService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<Void> addWords(@Valid @RequestBody AddWordsRequest request) {
    ResponseEntity response = anagramService.addWords(request);
        System.out.println("----->"+response);
    return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagrams/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    public ResponseEntity<AnagramsFoundResponse> findAnagrams(
            @PathVariable("word") String word,
            @RequestParam(required = false) Integer limit
    ) {
        AnagramsFoundResponse response = anagramService.findAnagrams(word, limit);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWord(
            @PathVariable("word") String word
    ) {
        ResponseEntity<Void> response = anagramService.deleteWord(word);

        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAll() {
        ResponseEntity<Void> response = anagramService.deleteAll();

        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/count.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    public ResponseEntity<CountResponse> findAnagrams(
    ) {
        CountResponse response = anagramService.countWords();

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagramGroup.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    public ResponseEntity<GroupResponse> evaluateWords(
            @RequestParam List<String> words
    ) {
        GroupResponse response = anagramService.evaluateWords(words);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagrams/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAnagrams(
            @PathVariable("word") String word
    ) {
        ResponseEntity<Void> response = anagramService.deleteAnagrams(word);

        return response;
    }
}


package com.ibotta.anagram.controller;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ibotta.anagram.service.AnagramService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@org.springframework.stereotype.Controller
@Validated
public class Controller {
    private AnagramService anagramService;

    @Autowired
    public Controller(
            AnagramService anagramService
    ) {
        this.anagramService = anagramService;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<AddWordsResponse> addWords(@Valid @RequestBody AddWordsRequest request) {
    ResponseEntity response = anagramService.addWords(request);
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
}


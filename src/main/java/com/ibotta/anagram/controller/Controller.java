package com.ibotta.anagram.controller;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.AnagramService;

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
    @RequestMapping(value = "/words.json", method = RequestMethod.POST)
    public ResponseEntity<AddWordsResponse> addWords(@Valid @RequestBody AddWordsRequest request) {
    ResponseEntity response = anagramService.addWords(request);

    return response;
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagrams/:word.json")
    public ResponseEntity<AnagramsFoundResponse> findAnagrams(
    @NotEmpty @RequestHeader(value = "word") String word
    ) {
        ResponseEntity<AnagramsFoundResponse> response = anagramService.findAnagrams(word);

        return response;
    }
}


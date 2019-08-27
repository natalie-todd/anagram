package com.ibotta.anagram.controller;

import com.ibotta.anagram.model.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.AnagramService;

import javax.validation.Valid;

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
}


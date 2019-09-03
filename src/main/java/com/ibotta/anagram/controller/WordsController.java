package com.ibotta.anagram.controller;

import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.GroupResponse;
import com.ibotta.anagram.service.AnagramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "/anagrams", description = "Evaluate anagrams within dictionary")
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
    @ResponseBody
    @ApiOperation(value = "Add anagrams",
            httpMethod = "POST",
            produces = "application/json",
            consumes = "application/json",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<Void> addWords(@Valid @RequestBody AddWordsRequest request) {
    ResponseEntity response = anagramService.addWords(request);
        System.out.println("----->"+response);
    return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagrams/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Find anagrams",
            httpMethod = "GET",
            produces = "application/json",
            consumes = "application/json",
            response = AnagramsFoundResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found anagrams."),
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<AnagramsFoundResponse> findAnagrams(
            @PathVariable("word") String word,
            @RequestParam(required = false) Integer limit
    ) {
        AnagramsFoundResponse response = anagramService.findAnagrams(word, limit);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "Delete anagram",
            httpMethod = "DELETE",
            produces = "application/json",
            consumes = "application/json",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<Void> deleteWord(
            @PathVariable("word") String word
    ) {
        ResponseEntity<Void> response = anagramService.deleteWord(word);

        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/words.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "Delete all anagrams",
            httpMethod = "DELETE",
            produces = "application/json",
            consumes = "application/json",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<Void> deleteAll() {
        ResponseEntity<Void> response = anagramService.deleteAll();

        return response;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/count.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get anagram count info",
            httpMethod = "GET",
            produces = "application/json",
            consumes = "application/json",
            response = CountResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched anagram count info."),
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<CountResponse> findAnagrams(
    ) {
        CountResponse response = anagramService.countWords();

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagramGroup.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Evaluate anagram group",
            httpMethod = "GET",
            produces = "application/json",
            consumes = "application/json",
            response = GroupResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted anagram."),
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<GroupResponse> evaluateWords(
            @RequestParam List<String> words
    ) {
        GroupResponse response = anagramService.evaluateWords(words);

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/anagrams/{word}.json", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "Delete word and anagrams",
            httpMethod = "DELETE",
            produces = "application/json",
            consumes = "application/json",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted word and anagrams."),
            @ApiResponse(code = 400, message = "Invalid anagram request")
    })
    public ResponseEntity<Void> deleteAnagrams(
            @PathVariable("word") String word
    ) {
        ResponseEntity<Void> response = anagramService.deleteAnagrams(word);

        return response;
    }
}


package com.ibotta.anagram.service;

import com.ibotta.anagram.controller.AddWordsResponse;
import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.builder.AddWordsRequestBuilder;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnagramServiceTest {

    private AnagramService anagramService;

    private List<String> dictionary = new ArrayList<>();

    @Before
    public void setUp() {

        anagramService = new AnagramService(asList("read", "dare", "dear"));
    }

    @Test
    public void addWords_addsNewWordsToDictionary_fromRequest() {
        AddWordsRequest request = AddWordsRequestBuilder.addWordsRequestBuilder()
                .words(asList("dare", "charles")).build();

        List<String> myDictionary = new ArrayList<String>(){{add("read");add("dare");add("dear");}};

        anagramService = new AnagramService(myDictionary);

        ResponseEntity<AddWordsResponse> addWordsResponseResponseEntity = anagramService.addWords(request);

        List<String> expectedDictionary = asList("read", "dare", "dear", "charles");

        assertThat(myDictionary, equalTo(expectedDictionary));
    }

    @Test
    public void findAnagrams_returnsAllAnagrams_fromGivenWordWithoutLimit() {

        AnagramsFoundResponse response = anagramService.findAnagrams("read", null);

        AnagramsFoundResponse expected = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(asList("dare", "dear")).build();

        assertThat(response, equalTo(expected));
    }

    @Test
    public void findAnagrams_returnsLimitOfAnagrams_fromGivenWordWithLimit() {

        AnagramsFoundResponse response = anagramService.findAnagrams("read", 1);

        AnagramsFoundResponse expected = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(asList("dare")).build();

        assertThat(response, equalTo(expected));
    }

    @Test
    public void findAnagrams_returnsAllAnagrams_fromGivenWordWithLimitLargerThanAnagramCount() {

        AnagramsFoundResponse response = anagramService.findAnagrams("read", 4);

        AnagramsFoundResponse expected = AnagramsFoundResponseBuilder.anagramsFoundResponseBuilder()
                .anagrams(asList("dare", "dear")).build();

        assertThat(response, equalTo(expected));
    }

}

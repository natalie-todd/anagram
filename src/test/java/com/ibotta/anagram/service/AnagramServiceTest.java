package com.ibotta.anagram.service;

import com.ibotta.anagram.model.AddWordsRequest;
import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;
import com.ibotta.anagram.model.GroupResponse;
import com.ibotta.anagram.model.builder.AddWordsRequestBuilder;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import com.ibotta.anagram.model.builder.CountResponseBuilder;
import com.ibotta.anagram.model.builder.GroupResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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

        ResponseEntity<Void> addWordsResponseResponseEntity = anagramService.addWords(request);

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

    @Test
    public void deleteWord_removesWordFromDictionary() {

        List<String> myDictionary = new ArrayList<String>(){{add("read");add("dare");add("dear");}};

        anagramService = new AnagramService(myDictionary);

        ResponseEntity<Void> response = anagramService.deleteWord("read");

        List<String> expectedDictionary = asList("dare", "dear");

        assertThat(myDictionary, equalTo(expectedDictionary));
    }
    //TODO: what should happen when you try to delete a word that is not there
    @Test
    public void deleteAll_emptiesDictionary() {

        List<String> myDictionary = new ArrayList<String>(){{add("read");add("dare");add("dear");}};

        anagramService = new AnagramService(myDictionary);

        ResponseEntity<Void> response = anagramService.deleteAll();

        List<String> expectedDictionary = asList();

        assertThat(myDictionary, equalTo(expectedDictionary));
    }

    @Test
    public void countWords_returnsDictionaryTotal() {

        List<String> myDictionary = new ArrayList<String>()
        {{add("a");add("bee");add("camera");add("dog");add("elephant");}};

        anagramService = new AnagramService(myDictionary);

        CountResponse actualResponse = anagramService.countWords();

        List<String> expectedDictionary = asList("a", "bee", "camera", "dog", "elephant");

        CountResponse expectedResponse = CountResponseBuilder.countResponseBuilder()
                .corpusTotal(expectedDictionary.size())
                .min(1)
                .max(8)
                .median(3)
                .average(4).build();

        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void evaluateWords_returnsTrue_whenPassedGroupOfAnagrams() {

        GroupResponse actualResponse = anagramService.evaluateWords(asList("read", "dare", "dear"));

        GroupResponse expectedResponse = GroupResponseBuilder.groupResponseBuilder()
                .areAnagrams(true).build();

        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void evaluateWords_returnsFalse_whenPassedGroupOfWordsThatAreNotAnagrams() {

        GroupResponse actualResponse = anagramService.evaluateWords(asList("read", "dare", "cat"));

        GroupResponse expectedResponse = GroupResponseBuilder.groupResponseBuilder()
                .areAnagrams(false).build();

        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void deleteAnagrams_removesWordAndItsAnagramsFromDictionary() {

        List<String> myDictionary = new ArrayList<String>(){{add("read");add("dare");add("dear");add("cat");}};

        anagramService = new AnagramService(myDictionary);

        ResponseEntity<Void> response = anagramService.deleteAnagrams("read");

        List<String> expectedDictionary = asList("cat");

        assertThat(myDictionary, equalTo(expectedDictionary));
    }
}

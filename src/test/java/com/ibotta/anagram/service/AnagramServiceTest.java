package com.ibotta.anagram.service;

import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.builder.AnagramsFoundResponseBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnagramServiceTest {

    private AnagramService anagramService;

    private List<String> dictionary = new ArrayList<>();

//write a test for the post endpoint checking to see if 200 is returned

    @Before
    public void setUp() {

        anagramService = new AnagramService(asList("read", "dare", "dear"));
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

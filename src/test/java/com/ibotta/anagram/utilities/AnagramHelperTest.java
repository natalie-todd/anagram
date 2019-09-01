package com.ibotta.anagram.utilities;

import com.ibotta.anagram.service.AnagramService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class AnagramHelperTest {

    private AnagramService anagramService;

    private AnagramHelper anagramHelper;

    @Before
    public void setUp() {

        anagramService = new AnagramService(asList("read", "dare", "dear"));

    }

    @Test
    public void limitIt() {
        List<String> anagrams = anagramHelper.limitIt(asList("dare", "dear"), 1);

        assertThat(anagrams, equalTo(asList("dare")));
    }
}

package com.ibotta.anagram.model.builder;

import com.ibotta.anagram.model.AnagramsFoundResponse;

import java.util.List;

public final class AnagramsFoundResponseBuilder {
    private List<String> anagrams;

    public AnagramsFoundResponseBuilder() {}

    public static AnagramsFoundResponseBuilder anagramsFoundResponseBuilder() { return new AnagramsFoundResponseBuilder(); }

    public AnagramsFoundResponseBuilder anagrams(List<String> anagrams) {
        this.anagrams = anagrams;
        return this;
    }

    public AnagramsFoundResponse build() {
        return new AnagramsFoundResponse(anagrams);
    }
}

package com.ibotta.anagram.model.builder;

import com.ibotta.anagram.model.AddWordsRequest;

import java.util.List;

public final class AddWordsRequestBuilder {
    private List<String> words;

    public AddWordsRequestBuilder() {}

    public static AddWordsRequestBuilder addWordsRequestBuilder() { return new AddWordsRequestBuilder(); }

    public AddWordsRequestBuilder words(List<String> words) {
        this.words = words;
        return this;
    }

    public AddWordsRequest build() {
        return new AddWordsRequest(words);
    }
}

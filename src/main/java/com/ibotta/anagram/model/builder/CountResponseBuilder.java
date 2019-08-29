package com.ibotta.anagram.model.builder;

import com.ibotta.anagram.model.AnagramsFoundResponse;
import com.ibotta.anagram.model.CountResponse;

import java.util.List;

public final class CountResponseBuilder {
    private Integer corpusTotal;

    public CountResponseBuilder() {}

    public static CountResponseBuilder countResponseBuilder() { return new CountResponseBuilder(); }

    public CountResponseBuilder corpusTotal(Integer corpusTotal) {
        this.corpusTotal = corpusTotal;
        return this;
    }

    public CountResponse build() {
        return new CountResponse(corpusTotal);
    }
}

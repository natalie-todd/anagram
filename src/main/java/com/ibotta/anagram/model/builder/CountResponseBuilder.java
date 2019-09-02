package com.ibotta.anagram.model.builder;

import com.ibotta.anagram.model.CountResponse;

public final class CountResponseBuilder {
    private Integer corpusTotal;
    private Integer min;
    private Integer max;
    private Integer median;
    private Integer average;

    public CountResponseBuilder() {}

    public static CountResponseBuilder countResponseBuilder() { return new CountResponseBuilder(); }

    public CountResponseBuilder corpusTotal(Integer corpusTotal) {
        this.corpusTotal = corpusTotal;
        return this;
    }

    public CountResponseBuilder min(Integer min) {
        this.min = min;
        return this;
    }

    public CountResponseBuilder max(Integer max) {
        this.max = max;
        return this;
    }

    public CountResponseBuilder median(Integer median) {
        this.median = median;
        return this;
    }

    public CountResponseBuilder average(Integer average) {
        this.average = average;
        return this;
    }

    public CountResponse build() {
        return new CountResponse(corpusTotal,
                min,
                max,
                median,
                average
        );
    }
}

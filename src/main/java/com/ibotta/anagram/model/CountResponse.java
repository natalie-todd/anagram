package com.ibotta.anagram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"corpusTotal"})
public class CountResponse {
    @Valid
    private Integer corpusTotal;
    @Valid
    private Integer min;
    @Valid
    private Integer max;
    @Valid
    private Integer median;
    @Valid
    private Integer average;

    @JsonCreator
    public CountResponse(@Valid @NotNull Integer corpusTotal,
                         @Valid @NotNull Integer min,
                         @Valid @NotNull Integer max,
                         @Valid @NotNull Integer median,
                         @Valid @NotNull Integer average
    ) {
        this.corpusTotal = corpusTotal;
        this.min = min;
        this.max = max;
        this.median = median;
        this.average = average;
    }

    public Integer getCorpusTotal() {
        return corpusTotal;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getMedian() {
        return median;
    }

    public Integer getAverage() {
        return average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountResponse that = (CountResponse) o;
        return Objects.equals(corpusTotal, that.corpusTotal) &&
                Objects.equals(min, that.min) &&
                Objects.equals(max, that.max) &&
                Objects.equals(median, that.median) &&
                Objects.equals(average, that.average);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corpusTotal, min, max, median, average);
    }

    @Override
    public String toString() {
        return "CountResponse{" +
                "corpusTotal=" + corpusTotal +
                ", min=" + min +
                ", max=" + max +
                ", median=" + median +
                ", average=" + average +
                '}';
    }
}

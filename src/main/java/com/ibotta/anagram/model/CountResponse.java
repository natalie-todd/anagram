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
    @NotNull
    private Integer corpusTotal;

    @JsonCreator
    public CountResponse(@Valid @NotNull Integer corpusTotal) {
        this.corpusTotal = corpusTotal;
    }

    public Integer getCorpusTotal() {
        return corpusTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountResponse that = (CountResponse) o;
        return Objects.equals(corpusTotal, that.corpusTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corpusTotal);
    }

    @Override
    public String toString() {
        return "CountResponse{" +
                "corpusTotal=" + corpusTotal +
                '}';
    }
}

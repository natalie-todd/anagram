package com.ibotta.anagram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"wordsToAdd"})
public final class AddWordsRequest {
    @Valid
    @NotNull
    private final List<String> wordsToAdd;

    @JsonCreator
    public AddWordsRequest(@JsonProperty List<String> wordsToAdd) {
        this.wordsToAdd = wordsToAdd;
    }

    public List<String> getWordsToAdd() {
        return wordsToAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddWordsRequest that = (AddWordsRequest) o;
        return Objects.equals(wordsToAdd, that.wordsToAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordsToAdd);
    }

    @Override
    public String toString() {
        return "AddWordsRequest{" +
                "wordsToAdd=" + wordsToAdd +
                '}';
    }
}

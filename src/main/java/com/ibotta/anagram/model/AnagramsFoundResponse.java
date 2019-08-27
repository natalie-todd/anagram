package com.ibotta.anagram.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Anagrams"})
public class AnagramsFoundResponse {
    @Valid
    private final List<String> anagrams;

    public AnagramsFoundResponse(List<String> anagrams) {
        this.anagrams = anagrams;
    }

    public List<String> getAnagrams() {
        return anagrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnagramsFoundResponse that = (AnagramsFoundResponse) o;
        return Objects.equals(anagrams, that.anagrams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anagrams);
    }

    @Override
    public String toString() {
        return "AnagramsFoundResponse{" +
                "anagrams=" + anagrams +
                '}';
    }
}

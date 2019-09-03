package com.ibotta.anagram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class GroupResponse {
    @Valid
    @NotNull
    private boolean areAnagrams;

    @JsonCreator
    public GroupResponse(@JsonProperty boolean areAnagrams) {
        this.areAnagrams = areAnagrams;
    }

    public boolean areAnagrams() {
        return areAnagrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupResponse that = (GroupResponse) o;
        return areAnagrams == that.areAnagrams;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areAnagrams);
    }

    @Override
    public String toString() {
        return "EvaluateGroupResponse{" +
                "areAnagrams=" + areAnagrams +
                '}';
    }
}

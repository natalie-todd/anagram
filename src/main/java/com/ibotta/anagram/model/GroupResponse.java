package com.ibotta.anagram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GroupResponse {
    private Boolean areAnagrams;

    @JsonCreator
    public GroupResponse(@JsonProperty("areAnagrams") Boolean areAnagrams) {
        this.areAnagrams = areAnagrams;
    }

    public Boolean getAreAnagrams() {
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

package com.ibotta.anagram.model.builder;

import com.ibotta.anagram.model.GroupResponse;

public class GroupResponseBuilder {
    private Boolean areAnagrams;

    public GroupResponseBuilder() {}

    public static GroupResponseBuilder groupResponseBuilder() { return new GroupResponseBuilder(); }

    public GroupResponseBuilder areAnagrams(Boolean areAnagrams) {
        this.areAnagrams = areAnagrams;
        return this;
    }

    public GroupResponse build() {
        return new GroupResponse(areAnagrams);
    }
}

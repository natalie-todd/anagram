package com.ibotta.anagram.utilities;

import java.util.List;
import java.util.stream.Collectors;

public class AnagramHelper {
    public static List<String> limitIt(List<String> anagrams, Integer limit) {
        if((limit == null) || (limit == 0)) {
            return anagrams;
        } else {
            return anagrams.stream().limit(limit).collect(Collectors.toList());
        }
    }
}

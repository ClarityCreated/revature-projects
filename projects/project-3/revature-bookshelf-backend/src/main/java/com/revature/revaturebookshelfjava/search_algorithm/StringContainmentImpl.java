package com.revature.revaturebookshelfjava.search_algorithm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StringContainmentImpl implements StringContainment{

    public int contains(String userInput, String bookTitle) {
        int count = 0;
        List<String> blackList = Arrays.asList("a","an","and","the","by","for","of","with","to","but","at","in","on","so");
        List<String> s1Words = new ArrayList<>(List.of(userInput.split("\\s+")));
        String lowerBookTitle = bookTitle.toLowerCase();
        List<String> userInputWords = new ArrayList<>();
        for (String word: s1Words) {
            word = word.toLowerCase();
            userInputWords.add(word);
            if (s1Words.size() >= 3 && blackList.contains(word)) {
                userInputWords.remove(word);
            }
        }
        for (String word : userInputWords) {
            if (lowerBookTitle.contains(word)) {
                count++;
            }
        }
        return count;
    }
}

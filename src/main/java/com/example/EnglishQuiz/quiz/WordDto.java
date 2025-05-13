package com.example.EnglishQuiz.quiz;

import lombok.Getter;

@Getter
public class WordDto {
    private Integer id;
    private String word;
    private String meaning;

    public WordDto(Integer id, String word, String meaning) {
        this.id=id;
        this.word=word;
        this.meaning=meaning;
    }
}

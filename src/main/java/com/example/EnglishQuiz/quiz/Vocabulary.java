package com.example.EnglishQuiz.quiz;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Vocabulary {
    //영단어
    //뜻
    //날짜
    //ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vId;


    private String word;

    private String meaning;

    @Enumerated(EnumType.STRING)
    private DayType dayType;

}

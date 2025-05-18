package com.example.EnglishQuiz;

import com.example.EnglishQuiz.quiz.DayType;
import com.example.EnglishQuiz.quiz.QuizRepository;
import com.example.EnglishQuiz.quiz.Vocabulary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class getDayOfWordList {
    @Autowired
    QuizRepository quizRepository;
    @Test
    void get() {

        Vocabulary voca = new Vocabulary();
        voca.setDayType(DayType.DAY1);
        voca.setWord("apple");
        voca.setMeaning("사과");
        quizRepository.save(voca);


        Optional<List<Vocabulary>> temp = quizRepository.findByDayType(DayType.DAY1);
        if(temp.isPresent()) {
            List<Vocabulary> wordList = temp.get();
            for (int i = 0; i < wordList.size(); i++) {
                System.out.println(wordList.get(i));
            }
            Map<String, String> vocaQuiz = new HashMap<>();
            for (int i = 0; i < wordList.size(); i++) {
                vocaQuiz.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
            }
            System.out.println(vocaQuiz.keySet());


        } else {
            System.out.println("empty");
        }
    }
}

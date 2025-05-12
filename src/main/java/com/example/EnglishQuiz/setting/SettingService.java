package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.quiz.DayType;
import com.example.EnglishQuiz.quiz.QuizRepository;
import com.example.EnglishQuiz.quiz.Vocabulary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final QuizRepository quizRepository;

    /**
     * setting 새로운 단어 저장
     * @param dayType
     * @param word
     * @param meaning
     */
    public void saveNewVoca(DayType dayType, String word, String meaning) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(word);
        vocabulary.setMeaning(meaning);
        vocabulary.setDayType(dayType);
        this.quizRepository.save(vocabulary);
    }
}

package com.example.EnglishQuiz.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;


    //영어로 문제, 한글로 맞히기
    public Map<String, String> koreanQuiz (String quizType, DayType dayType) {
        Optional<List<Vocabulary>> dayWordListTemp = quizRepository.findByDayType(dayType);
        if(!dayWordListTemp.isPresent()) {
            return null;
        }
            List<Vocabulary> dayWordList = dayWordListTemp.get();

            //랜덤 순서 섞기
            Collections.shuffle(dayWordList);

            //맵에 넣기
            Map<String, String> vocaQuizAnswerKr = new LinkedHashMap<>();
            for (int i = 0; i < dayWordList.size(); i++) {
                vocaQuizAnswerKr.put(dayWordList.get(i).getWord(), dayWordList.get(i).getMeaning());
            }
        return vocaQuizAnswerKr;



    }
}

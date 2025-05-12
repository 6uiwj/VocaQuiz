package com.example.EnglishQuiz.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class quizService {
    private final QuizRepository quizRepository;


}

package com.example.EnglishQuiz.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @GetMapping
    public String quiz() {
        return "/quizMain";
    }

    @GetMapping("/selectType")
    public String quizType() {
        return "/quizType";
    }

}

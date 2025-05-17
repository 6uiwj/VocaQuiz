package com.example.EnglishQuiz.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    @GetMapping
    public String quiz() {
        return "/selectQuizDay";
    }

    @GetMapping("/selectType")
    public String quizType() {
        return "/quizType";
    }

    @GetMapping("/{type}/{day}")
    public String solveQuizPage(@PathVariable("type") String type, @PathVariable("day") String day, Model model) {
        model.addAttribute("quizType",type);
        model.addAttribute("quizDay", day);
        return "/solveTheQuiz";
    }

}

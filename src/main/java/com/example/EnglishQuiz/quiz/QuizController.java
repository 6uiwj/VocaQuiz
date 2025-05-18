package com.example.EnglishQuiz.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

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
        DayType dayType = DayType.valueOf(day.toUpperCase());
        model.addAttribute("quizType",type);
        model.addAttribute("quizDay", day);
        if(type.equals("korean")) {
            Map<String, String> quizAnswerToKr = quizService.koreanQuiz(type, dayType);
            model.addAttribute("quizAnswerToKr", quizAnswerToKr);
        } else if(type.equals("english")) {
            //TODO
        }
        return "/solveTheQuiz";
    }



}

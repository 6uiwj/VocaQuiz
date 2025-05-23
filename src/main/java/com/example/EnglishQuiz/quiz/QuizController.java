package com.example.EnglishQuiz.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
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

    /**
     * 퀴즈 페이지 렌더링
     * @param type
     * @param day
     * @param model
     * @return
     */
    @GetMapping("/{type}/{day}")
    public String solveQuizPage(@PathVariable("type") String type, @PathVariable("day") String day, Model model) {
        model.addAttribute("quizType",type);
        model.addAttribute("quizDay", day);
//        if(type.equals("korean")) {
//            Map<String, String> quizAnswerToKr = quizService.koreanQuiz(type, dayType);
//            model.addAttribute("quizAnswerToKr", quizAnswerToKr)
//        } else if(type.equals("english")) {
//            //TODO
//        }
        return "/solveTheQuiz";
    }

    /**
     * 퀴즈 출력하기
     * @param type
     * @param day
     * @return
     */
    @GetMapping("/quiz-data")
    @ResponseBody
    public Map<String, String> getQuizDate(@RequestParam("type") String type,
                                           @RequestParam("day") String day) {
        DayType dayType = DayType.valueOf(day.toUpperCase());

        if(type.equals("korean")) {
            Map<String, String> quiz = quizService.koreanQuiz(type,dayType);
            return quiz;
        } else if(type.equals("english")) {
            return null;
        }
        return null;
    }



}

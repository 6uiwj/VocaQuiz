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

        return "/solveTheQuiz";
    }

    /**
     * 퀴즈 출력하기 - 데이터 전달용
     * @param type
     * @param day
     * @return
     */
    @GetMapping("/quiz-data")
    @ResponseBody
    public Map<String, String> getQuizDate(@RequestParam("type") String type,
                                           @RequestParam("day") String day,
                                           @RequestParam(value = "n", required = false) Integer n) {
        DayType dayType = DayType.valueOf(day.toUpperCase());
        Map<String, String> quiz;

        if(type.equals("korean")) {
            quiz = quizService.koreanQuiz(type,dayType);
        } else if(type.equals("english")) {
            quiz = quizService.englishQuiz(type,dayType);
        } else {
            return null;
        }

        // 문제 수 제한 처리 (n이 null 이거나 0 이하이면 전체 문제 반환)
        if(n == null || n <= 0 || n >= quiz.size()) {
            return quiz;
        }

        // 문제 수 제한에 맞게 quiz에서 일부 문제만 잘라서 반환
        // LinkedHashMap이 문제 순서 보장하므로 LinkedHashMap을 쓰는 걸 가정
        Map<String, String> limitedQuiz = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<String, String> entry : quiz.entrySet()) {
            if (count >= n) break;
            limitedQuiz.put(entry.getKey(), entry.getValue());
            count++;
        }
        return limitedQuiz;

    }

}

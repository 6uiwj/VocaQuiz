package com.example.EnglishQuiz.quiz;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
@Tag(name= "Quiz", description = "퀴즈 페이지 렌더링, 퀴즈 출력")
@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @Operation(summary = "퀴즈 풀 날짜 선택 페이지 이동")
    @GetMapping
    public String quiz() {
        return "/selectQuizDay";
    }

    @Operation(summary = "퀴즈 타입 선택", description = "한글 문제 영단어 맞히기 or 영단어 문제 한글 뜻 맞히기")
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
    @Operation(summary = "퀴즈 페이지 렌더링", description = "선택한 퀴즈 날짜와 타입을 model에 넣고 페이지를 렌더링합니다.")
    @GetMapping("/{type}/{day}")
    public String solveQuizPage(
            @Parameter(description = "퀴즈 타입: korean(한글로 영단어 맞히기) 또는 english(영단어로 한글 뜻 맞히기)")
            @PathVariable("type") String type,
            @Parameter(description = "퀴즈 날짜 선택")
            @PathVariable("day") String day, Model model) {
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
    @Operation(summary = "퀴즈 데이터 반환", description = "사용자가 요청한 날짜와 퀴즈 타입, 퀴즈 수에 맞는 퀴즈 데이터를 반환합니다.")
    @Parameter()
    @GetMapping("/quiz-data")
    @ResponseBody
    public Map<String, String> getQuizDate(
            @Parameter(description = "퀴즈 타입")
            @RequestParam("type") String type,
                                           @Parameter(description = "퀴즈 날짜 선택")
                                           @RequestParam("day") String day,
                                           @Parameter(description = "문제 수")
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

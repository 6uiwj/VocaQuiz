package com.example.EnglishQuiz;

import java.util.HashMap;
import java.util.Map;

public class QuizData {
    private static Map<String, String> quizData;
    static {
        quizData = new HashMap<>();

        quizData.put("vend","팔다, 행상하다");
    }
}

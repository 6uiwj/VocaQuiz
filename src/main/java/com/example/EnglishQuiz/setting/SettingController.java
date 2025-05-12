package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.quiz.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting")
public class SettingController {
    private final SettingService settingService;

    @GetMapping
    public String setting() {
        return "/setting";
    }


    @PostMapping
    public String saveNewVocabulary (@RequestParam("dayType") DayType dayType,
                                     @RequestParam(value="voca") String word,
                                     @RequestParam(value="meaning") String meaning) {
        settingService.saveNewVoca(dayType,word,meaning);
        return "redirect:/setting";
    }
}

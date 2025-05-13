package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.quiz.DayType;
import com.example.EnglishQuiz.quiz.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordApiController {

    private final SettingService settingService;

    public WordApiController(SettingService settingService) {
        this.settingService =settingService;
    }

    @GetMapping("/words")
    public List<WordDto> getWordsByDay(@RequestParam("day")DayType dayType) {
        return settingService.getWordListOfDay(dayType);
    }


}

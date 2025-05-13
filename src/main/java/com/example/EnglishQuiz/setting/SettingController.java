package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.quiz.DayType;
import com.example.EnglishQuiz.quiz.Vocabulary;
import com.example.EnglishQuiz.quiz.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        settingService.saveNewWord(dayType,word,meaning);
        return "redirect:/setting";
    }

    @GetMapping("/wordList")
    public String wordList() {
        return "wordList";
    }


    public void getWordList(@RequestParam("dataType") DayType dayType, Model model) {
        //TODO : 서비스 구현
        List<WordDto> wordList =  settingService.getWordListOfDay(dayType);
        model.addAttribute("wordListOfDay",wordList);

    }
}

package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.quiz.DayType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/setting")
public class SettingController {
    private final SettingService settingService;

    @GetMapping
    public String setting() {
        return "/setting";
    }


    /**
     * 단어 추가하기
     * @param dayType
     * @param word
     * @param meaning
     * @return
     */
    @PostMapping("/wordList")
    public String saveNewVocabulary (@RequestParam("dayType") DayType dayType,
                                     @RequestParam(value="voca") String word,
                                     @RequestParam(value="meaning") String meaning) {
        settingService.saveNewWord(dayType,word,meaning);
        return "redirect:/setting/wordList?day=" +dayType.name();
    }

    @GetMapping("/wordList")
    public String wordList() {
        return "wordList";
    }

    @GetMapping("/delete/{id}")
    public String deleteWord(@PathVariable("id") Integer vId, @RequestParam("day") String day) {
        settingService.deleteWord(vId);
        return "redirect:/setting/wordList?day=" + day;
    }

//    /**
//     *
//     * @param dayType
//     * @param model
//     */
//    public void getWordList(@RequestParam("dataType") DayType dayType, Model model) {
//        //TODO : 서비스 구현
//        List<WordDto> wordList =  settingService.getWordListOfDay(dayType);
//        model.addAttribute("wordListOfDay",wordList);
//
//    }
}

package com.example.EnglishQuiz.setting;

import com.example.EnglishQuiz.DataNotFoundException;
import com.example.EnglishQuiz.quiz.DayType;
import com.example.EnglishQuiz.quiz.QuizRepository;
import com.example.EnglishQuiz.quiz.Vocabulary;
import com.example.EnglishQuiz.quiz.WordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final QuizRepository quizRepository;

    /**
     * setting 새로운 단어 저장
     * @param dayType
     * @param word
     * @param meaning
     */
    public void saveNewWord(DayType dayType, String word, String meaning) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(word);
        vocabulary.setMeaning(meaning);
        vocabulary.setDayType(dayType);
        this.quizRepository.save(vocabulary);
    }


    public List<WordDto> getWordListOfDay(DayType dayType) {
        Optional<List<Vocabulary>> wordListTemp = quizRepository.findByDayType(dayType);
        if(wordListTemp.isEmpty()) {
            throw new DataNotFoundException("Data Not Found");
        }
        List<Vocabulary> wordList = wordListTemp.get();
        return wordList.stream()
                .map(word -> new WordDto(word.getVId(), word.getWord(), word.getMeaning()))
                .collect(Collectors.toList());
    }

}

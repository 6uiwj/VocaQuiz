package com.example.EnglishQuiz.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Vocabulary, Integer> {

    void deleteById(Integer vId);

    Optional<List<Vocabulary>> findByDayType(DayType dayType);
}

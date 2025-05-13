package com.example.EnglishQuiz.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface QuizRepository extends JpaRepository<Vocabulary, Integer> {

    Optional<List<Vocabulary>> findByDayType(DayType dayType);
}

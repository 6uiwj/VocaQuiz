package com.example.EnglishQuiz.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Vocabulary, Integer> {
}

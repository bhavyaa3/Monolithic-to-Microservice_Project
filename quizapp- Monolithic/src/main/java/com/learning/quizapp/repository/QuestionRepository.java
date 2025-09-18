package com.learning.quizapp.repository;

import com.learning.quizapp.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q WHERE q.category = :category AND q.difficultylevel = :difficultyLevel ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> getRandomQuestionByCategory(@Param("category") String category,
                                                   @Param("difficultyLevel") String difficultyLevel,
                                                   @Param("numQ") int numQ);

}

package com.learning.question_service.repository;

import com.learning.question_service.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category AND q.difficultylevel = :difficultyLevel ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> getRandomQuestionByCategory(@Param("category") String category,
                                               @Param("difficultyLevel") String difficultyLevel,
                                               @Param("numQ") int numQ);

}

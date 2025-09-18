package com.learning.quiz_service.service;

import com.learning.quiz_service.model.entity.Quiz;
import com.learning.quiz_service.model.wrapper.QuestionWrapper;
import com.learning.quiz_service.model.wrapper.QuizResponse;
import com.learning.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String name,String category, String difficultyLevel, int numQ) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,difficultyLevel,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<QuestionWrapper> questionWrappers = quizInterface.getQuestionsFromId(quiz.get().getQuestions()).getBody();
        return ResponseEntity.ok(questionWrappers);
    }

    public ResponseEntity<Integer> getResult(int id, List<QuizResponse> quizResponses) {

        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return  quizInterface.getResult(quizResponses);


    }
}

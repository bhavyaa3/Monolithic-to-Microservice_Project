package com.learning.quiz_service.service;

import com.learning.quiz_service.model.wrapper.QuestionWrapper;
import com.learning.quiz_service.model.wrapper.QuizResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    @PostMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category, @RequestParam String difficultyLevel, @RequestParam int  numQ);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questions);

    @PostMapping("/question/getResult")
    public ResponseEntity<Integer> getResult(@RequestBody List<QuizResponse> quizResponses);



}

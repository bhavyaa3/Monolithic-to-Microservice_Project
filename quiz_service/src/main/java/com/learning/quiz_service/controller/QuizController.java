package com.learning.quiz_service.controller;

import com.learning.quiz_service.model.QuizDTO;
import com.learning.quiz_service.model.entity.Quiz;
import com.learning.quiz_service.model.wrapper.QuestionWrapper;
import com.learning.quiz_service.model.wrapper.QuizResponse;
import com.learning.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String>  create(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getTitle(),quizDTO.getCategory(), quizDTO.getDifficultyLevel(), quizDTO.getNumQ());
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
       return  quizService.getQuiz(id);
    }

    @PostMapping("/getResult/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable int id , @RequestBody List<QuizResponse> quizResponses){

        return  quizService.getResult(id, quizResponses);

    }
}

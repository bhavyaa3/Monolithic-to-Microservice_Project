package com.learning.quizapp.controller;

import com.learning.quizapp.model.entity.Quiz;
import com.learning.quizapp.model.wrapper.QuestionWrapper;
import com.learning.quizapp.model.wrapper.QuizResponse;
import com.learning.quizapp.service.QuizService;
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
    public ResponseEntity<Quiz>  create(@RequestParam String name, @RequestParam String category, @RequestParam String difficultyLevel, @RequestParam int  numQ){
        Quiz saved = quizService.createQuiz(name,category, difficultyLevel, numQ);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
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

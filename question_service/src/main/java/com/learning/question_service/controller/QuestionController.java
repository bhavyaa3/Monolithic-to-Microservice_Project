package com.learning.question_service.controller;


import com.learning.question_service.model.entity.Question;
import com.learning.question_service.model.wrapper.QuestionWrapper;
import com.learning.question_service.model.wrapper.QuizResponse;
import com.learning.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;


    @GetMapping("/getAll")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getQuestions();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(questions); // 200 OK
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        if (questions.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question saved = questionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // 201 Created
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        try {
            Question updated = questionService.updateQuestion(id, question);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<List<Integer>>  getQuestionsForQuiz(@RequestParam String category, @RequestParam String difficultyLevel, @RequestParam int  numQ){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsForQuiz(category, difficultyLevel, numQ);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questions)
    {
        System.out.println(environment.getProperty("local.server.port"));

        return questionService.getQuestionFromId(questions);
    }

    @PostMapping("/getResult")
    public ResponseEntity<Integer> getResult(@RequestBody List<QuizResponse> quizResponses){
        return questionService.getResult(quizResponses);
    }








}

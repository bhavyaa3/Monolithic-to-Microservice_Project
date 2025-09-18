package com.learning.question_service.service;


import com.learning.question_service.model.entity.Question;
import com.learning.question_service.model.wrapper.QuestionWrapper;
import com.learning.question_service.model.wrapper.QuizResponse;
import com.learning.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getQuestions(){

        return  questionRepository.findAll();

    }

    public void deleteQuestion(Integer id) {
        questionRepository.deleteById(id);
    }

    public Question updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> existing = questionRepository.findById(id);
        if (existing.isPresent()) {
            Question q = existing.get();
            q.setCategory(updatedQuestion.getCategory());
            q.setDifficultylevel(updatedQuestion.getDifficultylevel());
            q.setOption1(updatedQuestion.getOption1());
            q.setOption2(updatedQuestion.getOption2());
            q.setOption3(updatedQuestion.getOption3());
            q.setOption4(updatedQuestion.getOption4());
            q.setQuestionTitle(updatedQuestion.getQuestionTitle());
            q.setRightAnswer(updatedQuestion.getRightAnswer());
            return questionRepository.save(q);
        }
        throw new RuntimeException("Question not found with id " + id);
    }
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, String difficultyLevel, int numQ) {
        List<Integer> quizQuestionList = questionRepository.getRandomQuestionByCategory(category, difficultyLevel, numQ);
        return new ResponseEntity<>(quizQuestionList, HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questions) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        for(int q : questions){
            Optional<Question> question = questionRepository.findById(q);
            QuestionWrapper questionWrapper = new QuestionWrapper();
            questionWrapper.setQuestionTitle(question.get().getQuestionTitle());
            questionWrapper.setOption1(question.get().getOption1());
            questionWrapper.setOption2(question.get().getOption2());
            questionWrapper.setOption3(question.get().getOption3());
            questionWrapper.setOption4(question.get().getOption4());
            questionWrapper.setId(question.get().getId());
            wrappers.add(questionWrapper);


        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getResult(List<QuizResponse> quizResponses) {


        int count = 0;

        for(QuizResponse response : quizResponses){
            Optional<Question> question = questionRepository.findById(response.getId());
            if(response.getResponse().equals(question.get().getRightAnswer())){
                count++;
            }
        }

        return ResponseEntity.ok(count);


    }
}

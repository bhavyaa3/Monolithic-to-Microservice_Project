package com.learning.quizapp.service;

import com.learning.quizapp.model.entity.Question;
import com.learning.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

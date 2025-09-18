package com.learning.quizapp.service;

import com.learning.quizapp.model.entity.Question;
import com.learning.quizapp.model.entity.Quiz;
import com.learning.quizapp.model.wrapper.QuestionWrapper;
import com.learning.quizapp.model.wrapper.QuizResponse;
import com.learning.quizapp.repository.QuestionRepository;
import com.learning.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    QuestionRepository questionRepository;

    public Quiz createQuiz(String name, String category, String difficultyLevel, int numQ) {
        List<Question> quizQuestionList = questionRepository.getRandomQuestionByCategory(category, difficultyLevel, numQ);
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setQuestions(quizQuestionList);
        return quizRepository.save(quiz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Question> questions = quiz.get().getQuestions();

        List<QuestionWrapper> wrappers = new ArrayList<>();
        for (Question ques : questions) {
            QuestionWrapper qw = new QuestionWrapper(
                    ques.getId(),
                    ques.getQuestionTitle(),
                    ques.getOption1(),
                    ques.getOption2(),
                    ques.getOption3(),
                    ques.getOption4()
            );
            wrappers.add(qw);
        }

        return ResponseEntity.ok(wrappers);
    }

    public ResponseEntity<Integer> getResult(int id, List<QuizResponse> quizResponses) {

        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Question> questions = quiz.get().getQuestions();

        int count = 0;
        int i = 0;
        for(Question question : questions){
            if(quizResponses.get(i).getResponse().equals(questions.get(i).getRightAnswer())){
                count++;
            }
            i++;
        }

        return ResponseEntity.ok(count);


    }
}

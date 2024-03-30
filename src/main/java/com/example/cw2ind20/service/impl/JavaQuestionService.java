package com.example.cw2ind20.service.impl;

import com.example.cw2ind20.entity.Question;
import com.example.cw2ind20.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question questionForDelete = new Question(question, answer);
        questions.remove(questionForDelete);
        return questionForDelete;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int randomInt = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(randomInt);
    }
}

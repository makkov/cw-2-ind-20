package com.example.cw2ind20.service.impl;

import com.example.cw2ind20.entity.Question;
import com.example.cw2ind20.exception.QuestionLimitException;
import com.example.cw2ind20.service.ExaminerService;
import com.example.cw2ind20.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> javaQuestions = javaQuestionService.getAll();
        Collection<Question> mathQuestions = mathQuestionService.getAll();
        Set<Question> allQuestions = new HashSet<>();
        allQuestions.addAll(javaQuestions);
        allQuestions.addAll(mathQuestions);
        int allQuestionsSize = allQuestions.size();

        if (allQuestionsSize < amount) {
            throw new QuestionLimitException(String.format("Запрошенно %s вопросов, в базе %s вопросов", amount, allQuestionsSize));
        }

        Random random = new Random();

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            if (random.nextBoolean()) {
                result.add(javaQuestionService.getRandomQuestion());
            } else {
                result.add((mathQuestionService.getRandomQuestion()));
            }
        }

        return result;
    }
}

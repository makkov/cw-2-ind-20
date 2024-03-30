package com.example.cw2ind20.service.impl;

import com.example.cw2ind20.entity.Question;
import com.example.cw2ind20.exception.QuestionLimitException;
import com.example.cw2ind20.service.ExaminerService;
import com.example.cw2ind20.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.getAll();
        int allQuestionsSize = allQuestions.size();

        if (allQuestionsSize < amount) {
            throw new QuestionLimitException(String.format("Запрошенно %s вопросов, в базе %s вопросов", amount, allQuestionsSize));
        }

        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }

        return result;
    }
}

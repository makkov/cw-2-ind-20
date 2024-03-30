package com.example.cw2ind20.service.impl;

import com.example.cw2ind20.entity.Question;
import com.example.cw2ind20.exception.QuestionLimitException;
import com.example.cw2ind20.repository.QuestionRepository;
import com.example.cw2ind20.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void getQuestions_withException() {
        //Подготовка входных данных
        int amount = 5;

        //Подготовка ожидаемого результата
        //Подготовка ожидаемого результата
        String q1 = "q1?";
        String a1 = "a1";
        String q2 = "q2";
        String a2 = "a2";

        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question(q1, a1));
        allQuestions.add(new Question(q2, a2));

        when(questionService.getAll()).thenReturn(allQuestions);
        String expectedMessage = String.format("Запрошенно %s вопросов, в базе %s вопросов", amount, allQuestions.size());

        //Начало теста
        Exception exception = assertThrows(QuestionLimitException.class,
                () -> examinerService.getQuestions(amount));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getQuestions_success() {
        int amount = 1;
        //Подготовка ожидаемого результата
        String q1 = "q1?";
        String a1 = "a1";
        String q2 = "q2";
        String a2 = "a2";

        Question question1 = new Question(q1, a1);
        Question question2 = new Question(q2, a2);

        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(question1);
        allQuestions.add(question2);

        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getRandomQuestion()).thenReturn(question1);
        when(questionService.getRandomQuestion()).thenReturn(question2);

        //Начало теста, простой вариант
        Collection<Question> questions = examinerService.getQuestions(amount);
        verify(questionService, times(2)).getAll();
        verify(questionService).getRandomQuestion();
    }
}

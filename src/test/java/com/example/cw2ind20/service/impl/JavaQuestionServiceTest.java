package com.example.cw2ind20.service.impl;

import com.example.cw2ind20.entity.Question;
import com.example.cw2ind20.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Test
    void add() {
        //Подготовка входных данных
        String question = "Сколько методов у класса Object?";
        String answer = "8";

        //Подготовка ожидаемого результата
        Question expectedQuestion = new Question(question, answer);
        when(questionRepository.add(expectedQuestion)).thenReturn(expectedQuestion);

        //Начало теста
        Question actualQuestion = javaQuestionService.add(question, answer);
        assertEquals(expectedQuestion, actualQuestion);
        verify(questionRepository).add(expectedQuestion);
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void getAll() {
        //Подготовка ожидаемого результата
        String q1 = "q1?";
        String a1 = "a1";
        String q2 = "q2";
        String a2 = "a2";

        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question(q1, a1));
        allQuestions.add(new Question(q2, a2));

        when(questionRepository.getAll()).thenReturn(allQuestions);

        //Начало теста
        Collection<Question> actualResult = javaQuestionService.getAll();
        assertEquals(allQuestions, actualResult);
        verify(questionRepository).getAll();
        verifyNoMoreInteractions(questionRepository);
    }

    @Test
    void getRandomQuestion() {
        //Подготовка ожидаемого результата
        String q1 = "q1?";
        String a1 = "a1";
        String q2 = "q2";
        String a2 = "a2";

        Set<Question> allQuestions = new HashSet<>();
        allQuestions.add(new Question(q1, a1));
        allQuestions.add(new Question(q2, a2));
        when(questionRepository.getAll()).thenReturn(allQuestions);

        //Начало теста
        Question question = javaQuestionService.getRandomQuestion();
        //Простой тест, без подробностей:
        assertTrue(allQuestions.contains(question));
        verify(questionRepository).getAll();
        verifyNoMoreInteractions(questionRepository);
    }
}
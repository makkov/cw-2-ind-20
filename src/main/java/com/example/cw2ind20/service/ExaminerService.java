package com.example.cw2ind20.service;

import com.example.cw2ind20.entity.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}

package com.example.cw2ind20.repository;

import com.example.cw2ind20.entity.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}

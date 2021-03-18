package com.demo.assignment.repository;

import androidx.lifecycle.MutableLiveData;

import com.demo.assignment.api.ApiResult;
import com.demo.assignment.models.Questions;

public interface StackOverflowRepository {
    void getQuestions(MutableLiveData<ApiResult<Questions>> questionsResult);
}

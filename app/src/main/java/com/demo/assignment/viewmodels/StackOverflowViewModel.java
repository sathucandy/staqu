package com.demo.assignment.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.assignment.api.ApiResult;
import com.demo.assignment.models.Questions;
import com.demo.assignment.repository.StackOverflowRepository;

public class StackOverflowViewModel extends ViewModel {

    private StackOverflowRepository stackOverflowRepository;
    private MutableLiveData<ApiResult<Questions>> questionsResult = new MutableLiveData<>();

    public StackOverflowViewModel(StackOverflowRepository stackOverflowRepository) {
        this.stackOverflowRepository = stackOverflowRepository;
    }

    public MutableLiveData<ApiResult<Questions>> getQuestionsResult() {
        return questionsResult;
    }

    public void getQuestions() {
        stackOverflowRepository.getQuestions(questionsResult);
    }

}

package com.demo.assignment.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.demo.assignment.api.Api;
import com.demo.assignment.api.ApiResult;
import com.demo.assignment.api.ApiService;
import com.demo.assignment.models.Questions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StackOverflowImpl implements StackOverflowRepository {

    private ApiService apiService;

    public StackOverflowImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getQuestions(MutableLiveData<ApiResult<Questions>> questionsResult) {
        apiService.getApi(Api.class).getQuestions().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(v -> questionsResult.setValue(new ApiResult<>(v)),
                        throwable -> questionsResult.setValue(new ApiResult<>(throwable)));
    }
}

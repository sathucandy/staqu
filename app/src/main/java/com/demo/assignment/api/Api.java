package com.demo.assignment.api;

import com.demo.assignment.models.Questions;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("no-answers?order=desc&sort=activity&site=stackoverflow")
    Observable<Questions> getQuestions();

}

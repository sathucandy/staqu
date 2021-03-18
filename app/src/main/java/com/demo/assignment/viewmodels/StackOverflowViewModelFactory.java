package com.demo.assignment.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.assignment.api.ApiService;
import com.demo.assignment.repository.StackOverflowImpl;

public class StackOverflowViewModelFactory implements ViewModelProvider.Factory {

    private ApiService apiService;

    public StackOverflowViewModelFactory() {
        this.apiService = new ApiService();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StackOverflowViewModel.class)) {
            return (T) new StackOverflowViewModel(new StackOverflowImpl(apiService));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

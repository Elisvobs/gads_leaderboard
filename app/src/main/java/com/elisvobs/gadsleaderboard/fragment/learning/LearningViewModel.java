package com.elisvobs.gadsleaderboard.fragment.learning;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elisvobs.gadsleaderboard.util.DataResponseCallback;
import com.elisvobs.gadsleaderboard.util.DataService;
import com.elisvobs.gadsleaderboard.model.Learning;

import java.util.List;

public class LearningViewModel extends ViewModel {
    private Handler handler = new Handler();
    private MutableLiveData<List<Learning>> learningLeaders;
    private MutableLiveData<Boolean> error = new MutableLiveData<>(false);

    public LiveData<List<Learning>> getLearningLeaders() {
        if (learningLeaders == null) {
            learningLeaders = new MutableLiveData<>();
            refreshList();
        }
        return learningLeaders;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public void refreshList() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataService.getLearningLeaders(new DataResponseCallback<List<Learning>>() {
                    @Override
                    public void onResponse(List<Learning> response) {
                        learningLeaders.setValue(response);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error.setValue(true);
                    }
                });
            }
        }, 1000);
    }
}

package com.elisvobs.gadsleaderboard.fragment.submit;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elisvobs.gadsleaderboard.api.ApiResponseCallback;
import com.elisvobs.gadsleaderboard.api.GoogleFormsApiService;
import com.elisvobs.gadsleaderboard.model.Submission;

public class SubmitViewModel extends ViewModel {
    public static final int STATUS_NEUTRAL = 0;
    public static final int STATUS_OK = 1;
    public static final int STATUS_ERROR = -1;
    private MutableLiveData<Integer> status = new MutableLiveData<>(STATUS_NEUTRAL);

    public void submit(Submission submission) {
        status.setValue(STATUS_NEUTRAL);
        new Handler().postDelayed(() -> GoogleFormsApiService
                .submitProject(submission, new ApiResponseCallback<Void>() {
                    @Override
                    public void onResponse(Void response) {
                        status.postValue(STATUS_OK);
                    }

                    @Override
                    public void onError(Throwable error) {
                        status.postValue(STATUS_ERROR);
                    }
                }), 1500);
    }

    public LiveData<Integer> getStatus() {
        return status;
    }
}

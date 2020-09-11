package com.elisvobs.gadsleaderboard.fragment.skill;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.elisvobs.gadsleaderboard.util.DataResponseCallback;
import com.elisvobs.gadsleaderboard.util.DataService;
import com.elisvobs.gadsleaderboard.model.Skill;

import java.util.List;

public class SkillViewModel extends ViewModel {
    private MutableLiveData<List<Skill>> skillLeaders;
    private MutableLiveData<Boolean> error = new MutableLiveData<>(false);
    private Handler handler = new Handler();

    public LiveData<List<Skill>> getSkillLeaders() {
        if (skillLeaders == null) {
            skillLeaders = new MutableLiveData<>();
            refreshList();
        }
        return skillLeaders;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public void refreshList() {
        handler.postDelayed(() -> DataService.getSkillLeaders(
                new DataResponseCallback<List<Skill>>() {
                    @Override
                    public void onResponse(List<Skill> response) {
                        skillLeaders.setValue(response);
                        error.setValue(false);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error.setValue(true);
                    }
                }), 1000);
    }
}

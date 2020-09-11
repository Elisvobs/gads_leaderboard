package com.elisvobs.gadsleaderboard.util;

import android.os.Handler;

import androidx.annotation.NonNull;

import com.elisvobs.gadsleaderboard.api.ApiResponseCallback;
import com.elisvobs.gadsleaderboard.api.GadsApiService;
import com.elisvobs.gadsleaderboard.model.Learning;
import com.elisvobs.gadsleaderboard.model.Skill;

import java.util.Collections;
import java.util.List;

public class DataService {
    public static Handler handler = new Handler();

    public static void getLearningLeaders(@NonNull final DataResponseCallback<List<Learning>> callback) {
        GadsApiService.getLearningLeaders(new ApiResponseCallback<List<Learning>>() {
            @Override
            public void onResponse(final List<Learning> response) {
                handler.post(() -> {
                    Collections.sort(response, (a, b) -> (int) Math.signum(b.getHours() - a.getHours()));
                    callback.onResponse(response);
                });
            }

            @Override
            public void onError(Throwable error) {
                callback.onError(error);
            }
        });
    }

    public static void getSkillLeaders(@NonNull final DataResponseCallback<List<Skill>> callback) {
        GadsApiService.getSkillLeaders(new ApiResponseCallback<List<Skill>>() {
            @Override
            public void onResponse(final List<Skill> response) {
                handler.post(() -> {
                    Collections.sort(response, (a, b) -> (int) Math.signum(b.getScore() - a.getScore()));
                    callback.onResponse(response);
                });
            }

            @Override
            public void onError(final Throwable error) {
                handler.post(() -> callback.onError(error));
            }
        });
    }
}

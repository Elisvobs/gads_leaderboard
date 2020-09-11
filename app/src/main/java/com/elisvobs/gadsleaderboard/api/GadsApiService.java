package com.elisvobs.gadsleaderboard.api;

import androidx.annotation.NonNull;

import com.elisvobs.gadsleaderboard.model.Learning;
import com.elisvobs.gadsleaderboard.model.Skill;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GadsApiService {

    public interface RetrofitInterface {
        @GET("/api/hours")
        Call<List<Learning>> getLearningLeaders();

        @GET("/api/skilliq")
        Call<List<Skill>> getSkillLeaders();
    }

    private static RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface.class);

    public static void getLearningLeaders(@NonNull final ApiResponseCallback<List<Learning>> callback) {
        retrofitInterface.getLearningLeaders()
                .enqueue(new Callback<List<Learning>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<Learning>> call, @NotNull Response<List<Learning>> response) {
                        if (response.isSuccessful())
                            callback.onResponse(response.body());
                        else
                            callback.onError(new ApiResponseError(response));
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<Learning>> call, @NotNull Throwable t) {
                        callback.onError(t);
                    }
                });
    }

    public static void getSkillLeaders(@NonNull final ApiResponseCallback<List<Skill>> callback) {
        retrofitInterface.getSkillLeaders()
                .enqueue(new Callback<List<Skill>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<Skill>> call, @NotNull Response<List<Skill>> response) {
                        if (response.isSuccessful())
                            callback.onResponse(response.body());
                        else
                            callback.onError(new ApiResponseError(response));
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<Skill>> call, @NotNull Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}

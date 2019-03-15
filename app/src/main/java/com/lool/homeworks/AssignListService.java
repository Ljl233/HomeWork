package com.lool.homeworks;

import com.example.homeworks.TasksBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AssignListService {
    @GET("api/course/{siteId: string}/assignment/list")
    Call<TasksBean> getTasksBean(@Header("cookie") String cookie,
                                 @Header("token") String token);
}

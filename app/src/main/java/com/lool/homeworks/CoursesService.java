package com.lool.homeworks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoursesService {
    @GET("/api/course/list/")
    Call<CoursesBean> getCoursesBean(@Header("cookie") String cookies , @Header("token") String tokens);
}
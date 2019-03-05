package com.lool.homeworks;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CourseService {
    @FormUrlEncoded
    @POST("api/login")
    Call<CourseBean> getCourseBean(@Field("userName") String userName,
                                   @Field("password") String password);
}

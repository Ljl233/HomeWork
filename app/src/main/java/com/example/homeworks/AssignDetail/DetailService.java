package com.example.homeworks.AssignDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DetailService {
    @GET("/api/assignment/{siteId}/{assignId}/info/")
    Call<DetailBean> getDetailBean(@Path("siteId") String siteId,
                                   @Path("assignId") String assignId,
                                   @Header("cookie") String cookie,
                                   @Header("token") String token);
}

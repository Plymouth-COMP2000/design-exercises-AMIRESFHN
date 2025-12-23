package com.example.resapp;

import com.example.resapp.model.MessageResponse;
import com.example.resapp.model.ReadAllUsersResponse;
import com.example.resapp.model.ReadUserResponse;
import com.example.resapp.model.UserRequest;

import retrofit2.Call;
import retrofit2.http.*;

public interface CourseworkApi {


    @POST("create_student/{student_id}")
    Call<MessageResponse> createStudentDb(@Path("student_id") String studentId);

    @POST("create_user/{student_id}")
    Call<MessageResponse> createUser(@Path("student_id") String studentId,
                                     @Body UserRequest body);

    @GET("read_all_users/{student_id}")
    Call<ReadAllUsersResponse> readAllUsers(@Path("student_id") String studentId);

    @GET("read_user/{student_id}/{username}")
    Call<ReadUserResponse> readUser(@Path("student_id") String studentId,
                                    @Path("username") String username);

    @PUT("update_user/{student_id}/{username}")
    Call<MessageResponse> updateUser(@Path("student_id") String studentId,
                                     @Path("username") String username,
                                     @Body UserRequest body);

    @DELETE("delete_user/{student_id}/{username}")
    Call<MessageResponse> deleteUser(@Path("student_id") String studentId,
                                     @Path("username") String username);
}

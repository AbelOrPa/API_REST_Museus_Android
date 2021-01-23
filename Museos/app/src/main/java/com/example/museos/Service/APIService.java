package com.example.museos.Service;

import com.example.museos.Model.Element;
import com.example.museos.Model.Museums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("15")
    Call<Museums> getMuseumsDe();
}

package com.example.museos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.museos.Model.Element;
import com.example.museos.Model.Museums;
import com.example.museos.Service.APIService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showMuseosActivity extends AppCompatActivity {

    private List<Element> listaMuseos;
    private RecyclerView recycler;
    private LoadingDialog loadingDialog;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_museos);
        //loadPreferences();

        recycler = (RecyclerView)findViewById(R.id.recycler);
        loadingDialog = new LoadingDialog(showMuseosActivity.this);
        loadingDialog.startLoadingDialog();
        getMuseums();

    }

    private void loadPreferences(){

        preferences = getSharedPreferences("Login credentials", Context.MODE_PRIVATE);

    }

    private void getMuseums(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/dataset/museus/format/json/pag-ini/1/pag-fi/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        APIService apiService =retrofit.create(APIService.class);
        Call<Museums> call = apiService.getMuseumsDe();
        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {

                if (response.isSuccessful()) {
                    Museums museums = response.body();
                    recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    AdapterData adapterData = new AdapterData(museums.getElements(),getApplicationContext());
                    recycler.setAdapter(adapterData);
                    loadingDialog.dismissDialog();
                }
            }
            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

                Log.e("failure",t.getLocalizedMessage());


            }
        });

    }
}
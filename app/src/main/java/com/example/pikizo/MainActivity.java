package com.example.pikizo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiClient apiClient;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiClient=new ApiClient();
        recyclerView=findViewById(R.id.listCategores);


        Call<Category> call = apiClient.getApiinterface().getCategory();

        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                AdapterCategory countryAdapter=new AdapterCategory(getApplicationContext(),response.body());
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(countryAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }
}
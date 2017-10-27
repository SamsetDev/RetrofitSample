package com.samset.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.samset.retrofitexample.adapter.RepositoryAdapter;
import com.samset.retrofitexample.model.Repository;
import com.samset.retrofitexample.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btnSearch;
    private AppCompatEditText etSearch;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        callApi();

        // by default call
        getDatafromServer("samsetdev");
    }

    private void callApi() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSearch.getText().toString() != null && !etSearch.getText().toString().isEmpty()) {
                    getDatafromServer(etSearch.getText().toString());
                }
            }
        });
    }

    private void initView() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        btnSearch = (AppCompatButton) findViewById(R.id.btnsearch);
        etSearch = (AppCompatEditText) findViewById(R.id.etrepository);
    }

    private void getDatafromServer(String username) {

        ApiService usersService = AppApplication.getInstance().getApiService();

        usersService.getRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                progressBar.setVisibility(View.GONE);
                Log.e("TAG", " Response " + response.body().size());
                setupRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView(List<Repository> repositories) {
        RepositoryAdapter adapter = new RepositoryAdapter(repositories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

package com.widya_angraini.widyauas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.widya_angraini.widyauas.adapter.UserAdapter;
import com.widya_angraini.widyauas.model.GetUser;
import com.widya_angraini.widyauas.model.User;
import com.widya_angraini.widyauas.rest.ApiClient;
import com.widya_angraini.widyauas.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert=(Button)findViewById((R.id.btnInsert));
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,InsertActivity.class));
            }
        });
        mRecyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //mLayoutManager = new LinearLayoutManager(this);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma = this;
        refresh();
    }

    public void refresh(){
        Call<GetUser> userCall = mApiInterface.getUser();
        userCall.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                List<User> userList=response.body().getData();
                Log.d("Retrofit Get", "Jumlah data User "+String.valueOf(userList.size()));
                mAdapter = new UserAdapter(MainActivity.this, userList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}
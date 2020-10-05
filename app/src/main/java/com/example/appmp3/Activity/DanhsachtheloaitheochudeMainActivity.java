package com.example.appmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appmp3.Adapter.DanhsachtheloaitheochudeAdapter;
import com.example.appmp3.Model.ChuDe;
import com.example.appmp3.Model.TheLoai;
import com.example.appmp3.R;
import com.example.appmp3.Service.APIService;
import com.example.appmp3.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeMainActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloaitheochude_main);
        GetIntent();
        init();
        GetData();

        
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback =dataservice.GetTheloaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai>mangtheloai= (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter= new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeMainActivity.this,mangtheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeMainActivity.this,2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude =findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent =getIntent();
        if (intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}
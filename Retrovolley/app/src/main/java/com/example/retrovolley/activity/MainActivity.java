package com.example.retrovolley.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrovolley.API.APIRequestData;
import com.example.retrovolley.API.Retroserver;
import com.example.retrovolley.R;
import com.example.retrovolley.adapter.AdapterData;
import com.example.retrovolley.model.DataModel;
import com.example.retrovolley.model.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;
    private FloatingActionButton fabTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData = findViewById(R.id.pb_data);
        fabTambah = findViewById(R.id.fab_tambah);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        retrieveData();

        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                retrieveData();
                srlData.setRefreshing(false);
            }
        });

        //pindah ke activity tambah
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    //method tampil data
    public void retrieveData(){
        pbData.setVisibility(View.VISIBLE);
        //memanggil API
        APIRequestData ardData = Retroserver.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardRetrieveData();

        //antrikan perintah
        tampilData.enqueue(new Callback<ResponseModel>() {
            //untuk menangkap jika berhasil
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                //membuat variable penampung
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                //Toast.makeText(MainActivity.this, "Kode : " + kode + "Pesan" + pesan, Toast.LENGTH_SHORT).show();

                listData = response.body().getData();

                //pasangkan dengan adapter
                adData = new AdapterData(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            //untuk menangkap jika gagal
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
package com.example.retrovolley.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrovolley.API.APIRequestData;
import com.example.retrovolley.API.Retroserver;
import com.example.retrovolley.R;
import com.example.retrovolley.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    //Deklarasi
    private EditText etFullname, etEmail, etPassword;
    private Button btnSubmit;
    private String fullname, email, password; //variable penampung


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etFullname = findViewById(R.id.et_fullname);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_submit);

        //memberi aksi ketika button diklik
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //menangkap data yang diisikan
                fullname = etFullname.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                //validasi
                if(fullname.trim().equals("")) {
                    etFullname.setError("Nama Tidak Boleh Kosong");
                } else if(email.trim().equals("")) {
                    etEmail.setError("Email Tidak Boleh Kosong");
                } else if (password.trim().equals("")) {
                    etPassword.setError("Password Tidak Boleh Kosong");
                } else {
                    createData();
                }
            }
        });
    }

    //method untuk menambah data
    private void createData(){
        //memanggil API
        APIRequestData ardData = Retroserver.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardCreateData(fullname, email, password);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                //membuat variable penampung
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode : " + kode + "Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();//langsung kembali ke MainActivity
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
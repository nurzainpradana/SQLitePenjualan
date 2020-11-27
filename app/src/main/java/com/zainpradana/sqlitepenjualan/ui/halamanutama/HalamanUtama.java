package com.zainpradana.sqlitepenjualan.ui.halamanutama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.ui.pelanggan.DataPelanggan;

public class HalamanUtama extends AppCompatActivity {
    Button buttonDataPelanggan, buttonDataBarang, buttonPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_utama);

        buttonDataPelanggan = findViewById(R.id.button_data_pelanggan);
        buttonDataBarang = findViewById(R.id.button_data_barang);
        buttonPenjualan = findViewById(R.id.button_data_penjualan);

        buttonDataPelanggan.setOnClickListener(view -> {
            Intent goToDataPelanggan = new Intent(HalamanUtama.this, DataPelanggan.class);
            startActivity(goToDataPelanggan);
        });

        buttonDataBarang.setOnClickListener(view -> {
//            Intent goToDataBarang = new Intent(HalamanUtama.this, DataBarang.class);
//            startActivity(goToDataBarang);
        });

        buttonPenjualan.setOnClickListener(view -> {
//            Intent goToDataPenjualan = new Intent(HalamanUtama.this, DataPenjualan.class);
//            startActivity(goToDataPenjualan);
        });


    }
}
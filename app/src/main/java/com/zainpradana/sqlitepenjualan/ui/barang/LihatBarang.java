package com.zainpradana.sqlitepenjualan.ui.barang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

public class LihatBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonKembali;
    TextView tvKdBarang, tvNamaBarang, tvSatuan, tvHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        dbHelper = new DataHelper(this);

        buttonKembali = findViewById(R.id.button_kembali);

        tvKdBarang = findViewById(R.id.tv_kode_barang);
        tvNamaBarang = findViewById(R.id.tv_nama_barang);
        tvHarga = findViewById(R.id.tv_harga);
        tvSatuan = findViewById(R.id.tv_satuan);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE kd_barang ='" + getIntent().getStringExtra("kd_barang") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvKdBarang.setText(cursor.getString(0));
            tvNamaBarang.setText(cursor.getString(1));
            tvSatuan.setText(cursor.getString(2));
            tvHarga.setText(cursor.getString(3));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
package com.zainpradana.sqlitepenjualan.ui.pelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

public class LihatPelanggan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonKembali;
    TextView tvKdPelanggan, tvNamaPelanggan, tvTelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pelanggan);

        dbHelper = new DataHelper(this);

        buttonKembali = findViewById(R.id.button_kembali);

        tvKdPelanggan = findViewById(R.id.tv_kode_pelanggan);
        tvNamaPelanggan = findViewById(R.id.tv_nama_pelanggan);
        tvTelp = findViewById(R.id.tv_telepon);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pelanggan WHERE kd_pelanggan ='" + getIntent().getStringExtra("kd_pelanggan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            tvKdPelanggan.setText(cursor.getString(0));
            tvNamaPelanggan.setText(cursor.getString(1));
            tvTelp.setText(cursor.getString(2));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
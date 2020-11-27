package com.zainpradana.sqlitepenjualan.ui.barang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;
import com.zainpradana.sqlitepenjualan.ui.barang.DataBarang;

public class UpdateBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText etKodeBarang, etNamaBarang, etSatuan, etHarga;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang);
        dbHelper = new DataHelper(this);
        etKodeBarang = findViewById(R.id.et_kode_barang);
        etNamaBarang = findViewById(R.id.et_nama_barang);
        etSatuan = findViewById(R.id.et_satuan);
        etHarga = findViewById(R.id.et_harga);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpan = findViewById(R.id.button_simpan_barang);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE kd_barang = '" + getIntent().getStringExtra("kd_barang") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            etKodeBarang.setText(cursor.getString(0));
            etNamaBarang.setText(cursor.getString(1));
            etSatuan.setText(cursor.getString(2));
            etHarga.setText(cursor.getString(3));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        buttonSimpan.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            db.execSQL("UPDATE barang SET nama_barang = '" +
                    etNamaBarang.getText().toString() + "', satuan ='" +
                    etSatuan.getText().toString() + "', harga ='" +
                    etHarga.getText().toString() + "' WHERE kd_barang ='" +
                    etKodeBarang.getText().toString() +"'");
            Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            DataBarang.dp.RefreshList();
            finish();
        });

    }
}
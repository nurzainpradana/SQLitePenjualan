package com.zainpradana.sqlitepenjualan.ui.penjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;
import com.zainpradana.sqlitepenjualan.ui.penjualan.DataPenjualan;

public class TambahPenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpanPenjualan, buttonKembali;
    EditText etIdPenjualan, etTglPenjualan, etKdPelanggan, etKdBarang, etQty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penjualan);

        dbHelper = new DataHelper(this);

        etIdPenjualan = findViewById(R.id.et_id_penjualan);
        etTglPenjualan = findViewById(R.id.et_tgl_pelanggan);
        etKdPelanggan = findViewById(R.id.et_kode_pelanggan);
        etKdBarang = findViewById(R.id.et_kode_barang);
        etQty = findViewById(R.id.et_qty);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanPenjualan = findViewById(R.id.button_simpan_penjualan);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanPenjualan.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into penjualan(id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) values('" +
                    etIdPenjualan.getText().toString() + "','" +
                    etTglPenjualan.getText().toString() + "','" +
                    etKdPelanggan.getText().toString() + "','" +
                    etKdBarang.getText().toString() + "','" +
                    etQty.getText().toString() + "');");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataPenjualan.dp.RefreshList();
            finish();
        });
    }
}
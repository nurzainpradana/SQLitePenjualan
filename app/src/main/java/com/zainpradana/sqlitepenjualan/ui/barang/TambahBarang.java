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

public class TambahBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpanBarang, buttonKembali;
    EditText etKodeBarang, etNamaBarang, etSatuan, etHarga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);

        dbHelper = new DataHelper(this);

        etKodeBarang = findViewById(R.id.et_kode_barang);
        etNamaBarang = findViewById(R.id.et_nama_barang);
        etSatuan = findViewById(R.id.et_satuan);
        etHarga = findViewById(R.id.et_harga);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanBarang = findViewById(R.id.button_simpan_barang);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanBarang.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into barang(kd_barang, nama_barang, satuan, harga) values('" +
                    etKodeBarang.getText().toString() + "','" +
                    etNamaBarang.getText().toString() + "','" +
                    etSatuan.getText().toString() + "','" +
                    etHarga.getText().toString() + "');");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataBarang.dp.RefreshList();
            finish();
        });
    }
}
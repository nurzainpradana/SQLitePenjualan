package com.zainpradana.sqlitepenjualan.ui.pelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

public class TambahPelanggan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpanPelanggan, buttonKembali;
    EditText etKodePelanggan, etNamaPelanggan, etTelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pelanggan);

        dbHelper = new DataHelper(this);

        etKodePelanggan = findViewById(R.id.et_kode_pelanggan);
        etNamaPelanggan = findViewById(R.id.et_nama_pelanggan);
        etTelp = findViewById(R.id.et_telp);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpanPelanggan = findViewById(R.id.button_simpan_pelanggan);

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanPelanggan.setOnClickListener(view -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into pelanggan(kd_pelanggan, nama_pelanggan, telp) values('" +
                    etKodePelanggan.getText().toString() + "','" +
                    etNamaPelanggan.getText().toString() + "','" +
                    etTelp.getText().toString() + "');");
            Toast.makeText(getApplicationContext(), "Berhasil" ,
                    Toast.LENGTH_LONG).show();
            DataPelanggan.dp.RefreshList();
            finish();
        });
    }
}
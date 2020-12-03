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
import com.zainpradana.sqlitepenjualan.ui.pelanggan.DataPelanggan;

public class UpdatePenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpanPenjualan, buttonKembali;
    EditText etIdPenjualan, etTglPenjualan, etKdPelanggan, etKdBarang, etQty;

    SQLiteDatabase db;


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

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty FROM penjualan WHERE id_penjualan ='" + getIntent().getStringExtra("id_penjualan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            etIdPenjualan.setText(cursor.getString(0));
            etTglPenjualan.setText(cursor.getString(1));
            etKdPelanggan.setText(cursor.getString(2));
            etKdBarang.setText(cursor.getString(3));
            etQty.setText(cursor.getString(4));
        }

        buttonKembali.setOnClickListener(view -> {
            finish();
        });

        buttonSimpanPenjualan.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            db.execSQL("UPDATE penjualan SET tgl_penjualan = '" +
                    etTglPenjualan.getText().toString() + "', kd_pelanggan ='" +
                    etKdPelanggan.getText().toString() + "', kd_barang ='" +
                    etKdBarang.getText().toString() + "' WHERE id_penjualan ='" +
                    etIdPenjualan.getText().toString() +"'");
            Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            DataPenjualan.dp.RefreshList();
            finish();
        });
    }
}
package com.zainpradana.sqlitepenjualan.ui.penjualan;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

import java.text.NumberFormat;
import java.util.Locale;

public class LihatPenjualan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonKembali;
    TextView tvIDPenjualan, tvTglPenjualan, tvKdPelanggan, tvNamaPelanggan, tvKdBarang, tvNamaBarang, tvQty, tvHargaBarang, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_penjualan);

        dbHelper = new DataHelper(this);

        buttonKembali = findViewById(R.id.button_kembali);

        tvIDPenjualan = findViewById(R.id.tv_id_penjualan);
        tvTglPenjualan = findViewById(R.id.tv_tanggal_penjualan);
        tvKdPelanggan = findViewById(R.id.tv_kode_pelanggan);
        tvNamaPelanggan = findViewById(R.id.tv_nama_pelanggan);
        tvKdBarang = findViewById(R.id.tv_kode_barang);
        tvNamaBarang = findViewById(R.id.tv_nama_barang);
        tvQty = findViewById(R.id.tv_qty);
        tvHargaBarang = findViewById(R.id.tv_harga_barang);
        tvTotal = findViewById(R.id.tv_total);

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT P.id_penjualan, P.tgl_penjualan, PL.kd_pelanggan, PL.nama_pelanggan, B.kd_barang, B.nama_barang, P.qty, B.harga FROM penjualan AS P JOIN pelanggan AS PL ON P.kd_pelanggan = PL.kd_pelanggan JOIN barang AS B ON P.kd_barang = B.kd_barang WHERE P.id_penjualan ='" + getIntent().getStringExtra("id_penjualan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);

            Log.i("CEK", cursor.getString(0));
            Log.i("CEK1", getIntent().getStringExtra("id_penjualan"));

            tvIDPenjualan.setText(cursor.getString(0));
            tvTglPenjualan.setText(cursor.getString(1));
            tvKdPelanggan.setText(cursor.getString(2));
            tvNamaPelanggan.setText(cursor.getString(3));
            tvKdBarang.setText(cursor.getString(4));
            tvNamaBarang.setText(cursor.getString(5));
            tvQty.setText(cursor.getString(6));

            double qty = Integer.parseInt(cursor.getString(6));
            double hargaBarang = Integer.parseInt(cursor.getString(7));
            double total = qty * hargaBarang;

            tvHargaBarang.setText(formatRupiah.format(hargaBarang));
            tvTotal.setText(formatRupiah.format(total));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
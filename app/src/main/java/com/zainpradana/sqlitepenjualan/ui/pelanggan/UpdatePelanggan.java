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

public class UpdatePelanggan extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button buttonSimpan, buttonKembali;
    EditText etKodePelanggan, etNamaPelanggan, etTelp;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pelanggan);
        dbHelper = new DataHelper(this);
        etKodePelanggan = findViewById(R.id.et_kd_pelanggan);
        etNamaPelanggan = findViewById(R.id.et_nama_pelanggan);
        etTelp = findViewById(R.id.et_telp);

        buttonKembali = findViewById(R.id.button_kembali);
        buttonSimpan = findViewById(R.id.button_simpan_pelanggan);

        db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pelanggan WHERE kd_pelanggan = '" + getIntent().getStringExtra("kd_pelanggan") + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            etKodePelanggan.setText(cursor.getString(0));
            etNamaPelanggan.setText(cursor.getString(1));
            etTelp.setText(cursor.getString(2));
        }

        buttonKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        buttonSimpan.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            db.execSQL("UPDATE pelanggan SET nama_pelanggan = '" +
                    etNamaPelanggan.getText().toString() + "', telp ='" +
                    etTelp.getText().toString() + "' WHERE kd_pelanggan ='" +
                    etKodePelanggan.getText().toString() +"'");
            Toast.makeText(this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
            DataPelanggan.dp.RefreshList();
            finish();
        });

    }
}
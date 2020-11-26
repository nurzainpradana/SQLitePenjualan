package com.zainpradana.sqlitepenjualan.ui.datapelanggan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

public class DataPelanggan extends AppCompatActivity {
    public static DataPelanggan dp;
    protected Cursor cursor;
    String[] daftarPelanggan, daftarNomorPelanggan;
    ListView listViewPelanggan;
    Button btnTambahPelanggan;
    Menu menu;
    DataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pelanggan);

        btnTambahPelanggan = findViewById(R.id.bt_tambah_dokter);
        btnTambahPelanggan.setOnClickListener(view -> {
            Intent goToTambahPelanggan = new Intent(DataPelanggan.this, TambahPelanggan.class);
            startActivity(goToTambahPelanggan);
        });

        dp = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pelanggan", null);
        daftarPelanggan = new String[cursor.getCount()];
        daftarNomorPelanggan = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarPelanggan[cc] = cursor.getString(1).toString();
            daftarNomorPelanggan[cc] = cursor.getString(0).toString();
        }

        listViewPelanggan = findViewById(R.id.listview_data_pelanggan);
        listViewPelanggan.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarPelanggan));
        listViewPelanggan.setSelected(true);
        listViewPelanggan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorPelanggan[arg2];
                final CharSequence[] dialogItem = { "Lihat Pelanggan", "Update Pelanggan", "Hapus Pelanggan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataPelanggan.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent goToLihatPelanggan = new Intent(DataPelanggan.this, LihatPelanggan.class);
                                goToLihatPelanggan.putExtra("nopelanggan", selection);
                                startActivity(goToLihatPelanggan);
                                break;

                            case 1:
                                Intent goToUpdatePelanggan = new Intent(DataPelanggan.this, UpdatePelanggan.class);
                                goToUpdatePelanggan.putExtra("nopelanggan", selection);
                                Toast.makeText(getApplicationContext(), selection.toString(), Toast.LENGTH_SHORT).show();
                                startActivity(goToUpdatePelanggan);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM pelanggan WHERE nopelanggan = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewPelanggan.getAdapter()).notifyDataSetInvalidated();
    }
}
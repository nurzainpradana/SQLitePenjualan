package com.zainpradana.sqlitepenjualan.ui.barang;

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

import com.zainpradana.sqlitepenjualan.R;
import com.zainpradana.sqlitepenjualan.database.DataHelper;

public class DataBarang extends AppCompatActivity {
    public static DataBarang dp;
    protected Cursor cursor;
    String[] daftarBarang, daftarNomorBarang;
    ListView listViewBarang;
    Button btnTambahBarang;
    Menu menu;
    DataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_barang);

        btnTambahBarang = findViewById(R.id.bt_tambah_barang);
        btnTambahBarang.setOnClickListener(view -> {
            Intent goToTambahBarang = new Intent(DataBarang.this, TambahBarang.class);
            startActivity(goToTambahBarang);
        });

        dp = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang", null);
        daftarBarang = new String[cursor.getCount()];
        daftarNomorBarang = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarBarang[cc] = cursor.getString(1).toString();
            daftarNomorBarang[cc] = cursor.getString(0).toString();
        }

        listViewBarang = findViewById(R.id.listview_data_barang);
        listViewBarang.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarBarang));
        listViewBarang.setSelected(true);
        listViewBarang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorBarang[arg2];
                final CharSequence[] dialogItem = { "Lihat Barang", "Update Barang", "Hapus Barang"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataBarang.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent goToLihatBarang = new Intent(DataBarang.this, LihatBarang.class);
                                goToLihatBarang.putExtra("kd_barang", selection);
                                startActivity(goToLihatBarang);
                                break;

                            case 1:
                                Intent goToUpdateBarang = new Intent(DataBarang.this, UpdateBarang.class);
                                goToUpdateBarang.putExtra("kd_barang", selection);
                                startActivity(goToUpdateBarang);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM barang WHERE kd_barang = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewBarang.getAdapter()).notifyDataSetInvalidated();
    }
}
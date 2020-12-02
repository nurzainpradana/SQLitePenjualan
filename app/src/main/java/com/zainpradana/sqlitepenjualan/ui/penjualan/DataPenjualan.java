package com.zainpradana.sqlitepenjualan.ui.penjualan;

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
import com.zainpradana.sqlitepenjualan.ui.penjualan.DataPenjualan;
import com.zainpradana.sqlitepenjualan.ui.penjualan.LihatPenjualan;
import com.zainpradana.sqlitepenjualan.ui.penjualan.TambahPenjualan;

public class DataPenjualan extends AppCompatActivity {
    public static DataPenjualan dp;
    protected Cursor cursor;
    String[] daftarPenjualan, daftarNomorPenjualan;
    ListView listViewPenjualan;
    Button btnTambahPenjualan;
    Menu menu;
    DataHelper dbCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penjualan);

        btnTambahPenjualan = findViewById(R.id.bt_tambah_penjualan);
        btnTambahPenjualan.setOnClickListener(view -> {
            Intent goToTambahPenjualan = new Intent(DataPenjualan.this, TambahPenjualan.class);
            startActivity(goToTambahPenjualan);
        });

        dp = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM penjualan", null);
        daftarPenjualan = new String[cursor.getCount()];
        daftarNomorPenjualan = new String[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftarPenjualan[cc] = cursor.getString(1).toString();
            daftarNomorPenjualan[cc] = cursor.getString(0).toString();
        }

        listViewPenjualan = findViewById(R.id.listview_data_penjualan);
        listViewPenjualan.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftarNomorPenjualan));
        listViewPenjualan.setSelected(true);
        listViewPenjualan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftarNomorPenjualan[arg2];
                final CharSequence[] dialogItem = { "Lihat Penjualan", "Update Penjualan", "Hapus Penjualan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DataPenjualan.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent goToLihatPenjualan = new Intent(DataPenjualan.this, LihatPenjualan.class);
                                goToLihatPenjualan.putExtra("id_penjualan", selection);
                                startActivity(goToLihatPenjualan);
                                break;

                            case 1:
//                                Intent goToUpdatePenjualan = new Intent(DataPenjualan.this, UpdatePenjualan.class);
//                                goToUpdatePenjualan.putExtra("id_penjualan", selection);
//                                startActivity(goToUpdatePenjualan);
                                break;

                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM penjualan WHERE kd_penjualan = '"+ selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listViewPenjualan.getAdapter()).notifyDataSetInvalidated();
    }
}
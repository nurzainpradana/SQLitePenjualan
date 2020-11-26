package com.zainpradana.sqlitepenjualan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbpenjualan.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("CREATE TABLE pasien(norekam integer primary key, tanggal_rekam text, nopasien integer, nodokter text, agama text, telp text, alamat text)");

        db.execSQL("CREATE TABLE pelanggan (kd_pelanggan int(5) NOT NULL, nama_pelanggan text NOT NULL, telp text NOT NULL)");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1001, 'Dinny Susilowati', '08577110779')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1002, 'Afif Mahar Widodo', '08988981908')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1003, 'Didik Setya Budi', '083830082299')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1004, 'Fahri Saputra', '085156298710')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1005, 'Galumbang Christo Marbun', '081263816274')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1006, 'Hendika Saputro', '081385745468')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1007, 'Kunto Wibisono', '081387690506')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1008, 'Nur Ain Pradana', '082125652279')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1009, 'Rizqi Fawaz Al-Fahri', '081295120669')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1010, 'Rizki Wahyudi', '085211345481')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1011, 'Ravi Budi Rizkiaji', '083869147788')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1012, 'Lailatul Zahro', '081388438315')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1013, 'Rico Irmansyah', '081804847455')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1014, 'Ariq Andrean', '081285459750')");
        db.execSQL("INSERT INTO pelanggan (kd_pelanggan, nama_pelanggan, telp) VALUES (1015, 'Dede Khairuddin', '085714266056')");

        db.execSQL("CREATE TABLE barang (kd_barang int(11) NOT NULL, nama_barang text DEFAULT NULL, satuan text DEFAULT NULL, harga int(11) DEFAULT NULL)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (200001, 'Lemari Es Toshiba 2 Pintu', 'Unit', 2700000)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (200002, 'TV Xiomi Android 32 inc', 'Unit', 2000000)");
        db.execSQL("INSERT INTO barang (kd_barang, nama_barang, satuan, harga) VALUES (200003, 'Kabel Antena ', 'Roll', 750000)");

        db.execSQL("CREATE TABLE penjualan (id_penjualan int(11) NOT NULL, tgl_penjualan date NOT NULL, kd_pelanggan int(11) NOT NULL, kd_barang int(11) NOT NULL, qty int(11) NOT NULL)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (3001, '2020-11-26', 1001, 200001, 1)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (3002, '2020-11-26', 1002, 200002, 2)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (3003, '2020-11-26', 1003, 200003, 5)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (3004, '2020-11-26', 1004, 200004, 2)");
        db.execSQL("INSERT INTO penjualan (id_penjualan, tgl_penjualan, kd_pelanggan, kd_barang, qty) VALUES (3005, '2020-11-26', 1005, 200005, 2)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE pelanggan");
        db.execSQL("DROP TABLE barang");
        db.execSQL("DROP TABLE penjualan");
    }
}
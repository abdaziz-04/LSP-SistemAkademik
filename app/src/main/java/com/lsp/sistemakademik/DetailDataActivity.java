package com.lsp.sistemakademik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailDataActivity extends AppCompatActivity {
    TextView nama, jenisKelamin, tglLahir, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        // Inisialisasi TextView
        nama = findViewById(R.id.tv_nama);
        jenisKelamin = findViewById(R.id.tv_jenisKelamin);
        tglLahir = findViewById(R.id.tv_tglLahir);
        alamat = findViewById(R.id.tv_alamat);

        // Mendapatkan data dari intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String namaValue = extras.getString("nama");
            String jenisKelaminValue = extras.getString("jenisKelamin");
            String tglLahirValue = extras.getString("tglLahir");
            String alamatValue = extras.getString("alamat");

            // Set text ke TextView
            nama.setText(namaValue);
            jenisKelamin.setText( jenisKelaminValue);
            tglLahir.setText( tglLahirValue);
            alamat.setText( alamatValue);
        }
    }
}

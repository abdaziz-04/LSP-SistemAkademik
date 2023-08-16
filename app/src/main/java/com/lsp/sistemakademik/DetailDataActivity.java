package com.lsp.sistemakademik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailDataActivity extends AppCompatActivity {
    TextView nama, jenisKelamin, tglLahir, alamat;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        btnOK = findViewById(R.id.bt_ok);
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

btnOK.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(DetailDataActivity.this, ListMahasiswaActivity.class);
        startActivity(intent);
    }
});
    }
}

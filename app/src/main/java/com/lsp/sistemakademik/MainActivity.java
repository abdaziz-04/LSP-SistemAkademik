package com.lsp.sistemakademik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button lihatData, InputData, infoAPP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lihatData = findViewById(R.id.btn_lihatData);
        InputData = findViewById(R.id.btn_inputData);
        infoAPP = findViewById(R.id.btn_infoApps);

        lihatData.setOnClickListener(view -> {
            Intent lihat = new Intent(MainActivity.this, ListMahasiswaActivity.class);
            startActivity(lihat);
        });

        InputData.setOnClickListener(view -> {
            Intent tambah = new Intent(MainActivity.this, EditorActivity.class);
            startActivity(tambah);
        });

        infoAPP.setOnClickListener(view -> {
            Intent info = new Intent(MainActivity.this, InformasiAppActivity.class);
            startActivity(info);
        });
    }
}
package com.lsp.sistemakademik;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lsp.sistemakademik.helper.Helper;

public class EditorActivity extends AppCompatActivity {
    private EditText edname, edjenisKelamin, edalamat, edtglLahir;
    private Button btnSimpan;
    private Helper db = new Helper(this);
    private String id, name, jenisKelamin, alamat, tglLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        edname = findViewById(R.id.ed_nama);
        edjenisKelamin = findViewById(R.id.ed_jeniKelamin);
        edtglLahir = findViewById(R.id.ed_tglLahir);
        edalamat = findViewById(R.id.ed_alamat);
        btnSimpan = findViewById(R.id.btn_simpan);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        jenisKelamin = getIntent().getStringExtra("jenisKelamin");
        tglLahir = getIntent().getStringExtra("tglLahir");
        alamat = getIntent().getStringExtra("alamat"); // Add this line

        if (id == null || id.equals("")) {
            setTitle("Tambah Users");
        } else {
            setTitle("Edit User");
            edname.setText(name);
            edjenisKelamin.setText(jenisKelamin);
            edtglLahir.setText(tglLahir);
            edalamat.setText(alamat);
        }
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save() {
        if (String.valueOf(edname.getText()).equals("") || String.valueOf(edjenisKelamin.getText()).equals("") || String.valueOf(edalamat.getText()).equals("") || String.valueOf(edtglLahir.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(edname.getText().toString(), edjenisKelamin.getText().toString(), edtglLahir.getText().toString(), edalamat.getText().toString());
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(edname.getText()).equals("") || String.valueOf(edjenisKelamin.getText()).equals("") || String.valueOf(edalamat.getText()).equals("") || String.valueOf(edtglLahir.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(id), edname.getText().toString(), edjenisKelamin.getText().toString(), edtglLahir.getText().toString(), edalamat.getText().toString());
            finish();
        }
    }
}
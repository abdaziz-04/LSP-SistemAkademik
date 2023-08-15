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
    private Helper db;
    private String id, name, jenisKelamin, alamat, tglLahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        db = new Helper(this);

        edname = findViewById(R.id.ed_nama);
        edjenisKelamin = findViewById(R.id.ed_jeniKelamin);
        edtglLahir = findViewById(R.id.ed_tglLahir);
        edalamat = findViewById(R.id.ed_alamat);
        btnSimpan = findViewById(R.id.btn_simpan);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        jenisKelamin = getIntent().getStringExtra("jenisKelamin");
        tglLahir = getIntent().getStringExtra("tglLahir");
        alamat = getIntent().getStringExtra("alamat");

        setTitle(id == null || id.equals("") ? "Tambah Users" : "Edit User");

        edname.setText(name);
        edjenisKelamin.setText(jenisKelamin);
        edtglLahir.setText(tglLahir);
        edalamat.setText(alamat);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isEmpty(edname) || isEmpty(edjenisKelamin) || isEmpty(edalamat) || isEmpty(edtglLahir)) {
                        Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
                    } else {
                        if (id == null || id.equals("")) {
                            save();
                        } else {
                            edit();
                        }
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void save() {
        db.insert(edname.getText().toString(), edjenisKelamin.getText().toString(), edtglLahir.getText().toString(), edalamat.getText().toString());
        finish();
    }

    private void edit() {
        db.update(Integer.parseInt(id), edname.getText().toString(), edjenisKelamin.getText().toString(), edtglLahir.getText().toString(), edalamat.getText().toString());
        finish();
    }
}
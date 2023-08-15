package com.lsp.sistemakademik;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lsp.sistemakademik.adapter.Adapter;
import com.lsp.sistemakademik.helper.Helper;
import com.lsp.sistemakademik.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListMahasiswaActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        db = new Helper(getApplicationContext());

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListMahasiswaActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        listView = findViewById(R.id.list_item);
        adapter = new Adapter(ListMahasiswaActivity.this, lists);
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showOptionsDialog(i);
            }
        });

        getData();
    }

    private void getData() {
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String tglLahir = rows.get(i).get("tglLahir");
            String jenisKelamin = rows.get(i).get("jenisKelamin");
            String alamat = rows.get(i).get("alamat");

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setAlamat(alamat);
            data.setJenisKelamin(jenisKelamin);
            data.setTglLahir(tglLahir);

            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    private void showOptionsDialog(final int position) {
        final Data selectedData = lists.get(position);
        final String id = selectedData.getId();
        final String name = selectedData.getName();
        final String jenisKelamin = selectedData.getJenisKelamin();
        final String tglLahir = selectedData.getTglLahir();
        final String alamat = selectedData.getAlamat();

        final CharSequence[] dialogItems = {"Lihat Data", "Edit", "Hapus"};
        dialog = new AlertDialog.Builder(ListMahasiswaActivity.this);
        dialog.setItems(dialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Data selectedData = lists.get(i);
                        Intent lihat = new Intent(ListMahasiswaActivity.this, DetailDataActivity.class);
                        lihat.putExtra("nama", selectedData.getName());
                        lihat.putExtra("jenisKelamin", selectedData.getJenisKelamin());
                        lihat.putExtra("tglLahir", selectedData.getTglLahir());
                        lihat.putExtra("alamat", selectedData.getAlamat());
                        startActivity(lihat);
                        break;
                    case 1:
                        Intent edit = new Intent(ListMahasiswaActivity.this, EditorActivity.class);
                        edit.putExtra("id", id);
                        edit.putExtra("name", name);
                        edit.putExtra("tglLahir", tglLahir);
                        edit.putExtra("jenisKelamin", jenisKelamin);
                        edit.putExtra("alamat", alamat);
                        startActivity(edit);
                        break;
                    case 2:
                        db.DELETE(Integer.parseInt(id));
                        lists.clear();
                        getData();
                        break;
                }
            }
        }).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}
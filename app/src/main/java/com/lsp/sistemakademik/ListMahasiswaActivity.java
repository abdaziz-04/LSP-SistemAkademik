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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String name = lists.get(i).getName();
                final String jenisKelamin = lists.get(i).getJenisKelamin();
                final String tglLahir = lists.get(i).getTglLahir();
                final String alamat = lists.get(i).getAlamat();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListMahasiswaActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(ListMahasiswaActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("tglLahir", tglLahir);
                                intent.putExtra("jenisKelamin", jenisKelamin);
                                intent.putExtra("alamat", alamat);
                                startActivity(intent); // Start the activity
                                break;
                            case 1:
                                db.DELETE(Integer.parseInt(id));
                                lists.clear();
                                getData(); // Refresh data after deletion
                                break;
                        }
                    }
                }).show();
                return false;
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

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}

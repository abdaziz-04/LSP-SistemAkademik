package com.lsp.sistemakademik.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lsp.sistemakademik.R;
import com.lsp.sistemakademik.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    TextView Name, tglLahir, Alamat, jenisKelamin;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null) {
            view = inflater.inflate(R.layout.detail_mahasiswa, null);
        }
        if (view != null) {
            Name = view.findViewById(R.id.tv_nama);
            jenisKelamin = view.findViewById(R.id.tv_jenisKelamin);
            tglLahir = view.findViewById(R.id.tv_tglLahir);
            Alamat = view.findViewById(R.id.tv_alamat);

            Data data = lists.get(i);

            Name.setText(data.getName());
            jenisKelamin.setText(data.getJenisKelamin());
            tglLahir.setText(data.getTglLahir());
            Alamat.setText(data.getAlamat());
        }

        return view;
    }
}

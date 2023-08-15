package com.lsp.sistemakademik.model;

public class Data {
    private String id, name, jenisKelamin, tglLahir, alamat;

    public Data() {

    }
    public Data(String id, String name, String jenisKelamin, String tglLahir, String alamat) {
        this.id = id;
        this.name = name;
        this.jenisKelamin = jenisKelamin;
        this.tglLahir = tglLahir;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}

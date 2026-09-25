/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

public abstract class LimbahElektronik {
    private String idItem;
    private String namaItem;
    private double hargaDasar;
    private int stokUnit;

    public LimbahElektronik(String idItem, String namaItem, double hargaDasar, int stokUnit) {
        this.idItem = idItem;
        this.namaItem = namaItem;
        this.hargaDasar = hargaDasar;
        this.stokUnit = stokUnit;
    }

    public String getIdItem() { return idItem; }
    public String getNamaItem() { return namaItem; }
    public double getHargaDasar() { return hargaDasar; }
    public int getStokUnit() { return stokUnit; }
    public void setStokUnit(int stokUnit) { this.stokUnit = stokUnit; }

    public void tambahStok(int jumlah) {
        this.stokUnit += jumlah;
        System.out.println("Stok " + namaItem + " berhasil ditambahkan sebanyak " + jumlah + " unit.");
    }

    public void tambahStok(int jumlah, String sumberLimbah) {
        this.stokUnit += jumlah;
        System.out.println("Stok " + namaItem + " ditambahkan " + jumlah + " unit dari " + sumberLimbah + ".");
    }

    public abstract double hitungHargaTotal();
    public abstract void tampilkanDetail();
}
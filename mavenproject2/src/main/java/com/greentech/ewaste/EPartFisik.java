/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

public class EPartFisik extends LimbahElektronik {
    private double beratKg;

    public EPartFisik(String idItem, String namaItem, double hargaDasar, int stokUnit, double beratKg) {
        super(idItem, namaItem, hargaDasar, stokUnit);
        this.beratKg = beratKg;
    }

    public double getBeratKg() { return beratKg; }

    @Override
    public double hitungHargaTotal() {
        double biayaPemrosesanLimbah = beratKg * 7500;
        return getHargaDasar() + biayaPemrosesanLimbah;
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[%s] %s | Harga Dasar: Rp%.0f | Berat: %.1f kg | Stok: %d unit | Total (+Handling Limbah): Rp%.0f%n",
                getIdItem(), getNamaItem(), getHargaDasar(), beratKg, getStokUnit(), hitungHargaTotal());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

public class PerangkatRefurbished extends EPartFisik {
    private int garansiHijauBulan;

    public PerangkatRefurbished(String idItem, String namaItem, double hargaDasar, int stokUnit, double beratKg, int garansiHijauBulan) {
        super(idItem, namaItem, hargaDasar, stokUnit, beratKg);
        this.garansiHijauBulan = garansiHijauBulan;
    }

    @Override
    public double hitungHargaTotal() {
        double hargaFisik = super.hitungHargaTotal();
        double biayaInspeksiQC = garansiHijauBulan * 5000;
        return hargaFisik + biayaInspeksiQC;
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[%s] %s (Refurbished) | Harga Dasar: Rp%.0f | Garansi: %d Bln | Stok: %d unit | Total (+QC & Garansi): Rp%.0f%n",
                getIdItem(), getNamaItem(), getHargaDasar(), garansiHijauBulan, getStokUnit(), hitungHargaTotal());
    }
}

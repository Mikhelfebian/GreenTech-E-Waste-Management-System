/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

public class SertifikatKreditKarbon extends LimbahElektronik {
    private double estimasiKarbonTerselamatkanKg;

    public SertifikatKreditKarbon(String idItem, String namaItem, double hargaDasar, int stokUnit, double estimasiKarbonTerselamatkanKg) {
        super(idItem, namaItem, hargaDasar, stokUnit);
        this.estimasiKarbonTerselamatkanKg = estimasiKarbonTerselamatkanKg;
    }

    @Override
    public double hitungHargaTotal() {
        double biayaPencatatanBlockchain = 3000;
        return getHargaDasar() + biayaPencatatanBlockchain;
    }

    @Override
    public void tampilkanDetail() {
        System.out.printf("[%s] %s (Digital) | Nilai Offset: Rp%.0f | Emisi CO2 Offset: %.1f kg | Stok: %d unit | Total (+Platform): Rp%.0f%n",
                getIdItem(), getNamaItem(), getHargaDasar(), estimasiKarbonTerselamatkanKg, getStokUnit(), hitungHargaTotal());
    }
}
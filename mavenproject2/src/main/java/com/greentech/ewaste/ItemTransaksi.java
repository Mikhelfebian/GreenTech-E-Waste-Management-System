/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

public class ItemTransaksi {
    private LimbahElektronik item;
    private int jumlah;

    public ItemTransaksi(LimbahElektronik item, int jumlah) {
        this.item = item;
        this.jumlah = jumlah;
    }

    public LimbahElektronik getItem() { return item; }
    public int getJumlah() { return jumlah; }
    public double getSubtotal() { return item.hitungHargaTotal() * jumlah; }
}

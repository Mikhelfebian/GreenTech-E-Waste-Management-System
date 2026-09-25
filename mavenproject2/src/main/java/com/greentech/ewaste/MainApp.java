/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.greentech.ewaste;

/**
 *
 * @author ASUS
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static ArrayList<LimbahElektronik> katalogLimbah = new ArrayList<>();
    private static ArrayList<ItemTransaksi> keranjangSistem = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiKatalogLimbah();
        boolean running = true;
        while (running) {
            tampilkanHeaderSystem();
            System.out.println("1. Katalog Komoditas & Produk Daur Ulang");
            System.out.println("2. Masu1"
                    + "kkan Item ke Keranjang Daur Ulang");
            System.out.println("3. Cek Keranjang & Estimasi Eco-Impact");
            System.out.println("4. Proses Transaksi / Checkout");
            System.out.println("5. Kelola Pasokan E-Waste (Restock)");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu (0-5): ");

            int pilihanMenu = readInt();
            switch (pilihanMenu) {
                case 1:
                    tampilkanKatalog();
                    break;
                case 2:
                    tambahKeKeranjang();
                    break;
                case 3:
                    lihatKeranjang();
                    break;
                case 4:
                    prosesCheckout();
                    break;
                case 5:
                    kelolaStokEwaste();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n[Info] Terima kasih telah berkontribusi dalam kelestarian bumi!");
                    break;
                default:
                    System.out.println("\n[!] Pilihan menu tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void inisialisasiKatalogLimbah() {
        katalogLimbah.add(new EPartFisik("EP01", "Papan PCB Mainboard Bekas", 85000, 15, 0.4));
        katalogLimbah.add(new EPartFisik("EP02", "Baterai Lithium Laptop Bekas", 45000, 30, 0.6));
        
        katalogLimbah.add(new PerangkatRefurbished("RF01", "Monitor LCD 22 Inch (Refurbished)", 650000, 6, 3.5, 6));
        katalogLimbah.add(new PerangkatRefurbished("RF02", "Power Supply Unit 500W (Refurbished)", 220000, 10, 1.8, 3));
        
        katalogLimbah.add(new SertifikatKreditKarbon("KC01", "Sertifikat Offset Karbon E-Waste Tier 1", 100000, 50, 25.0));
    }

    private static void tampilkanHeaderSystem() {
        System.out.println("\n==================================================");
        System.out.println("   GREENTECH & E-WASTE MANAGEMENT SYSTEM (PBO)    ");
        System.out.println("==================================================");
    }

    private static void tampilkanKatalog() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("     KATALOG KOMODITAS DAUR ULANG & REFURBISHED   ");
        System.out.println("--------------------------------------------------");
        
        for (int i = 0; i < katalogLimbah.size(); i++) {
            System.out.print((i + 1) + ". ");
            katalogLimbah.get(i).tampilkanDetail();
        }
    }

    private static void tambahKeKeranjang() {
        tampilkanKatalog();
        System.out.print("\nMasukkan nomor item yang dipilih: ");
        int nomorItem = readInt() - 1;

        if (nomorItem >= 0 && nomorItem < katalogLimbah.size()) {
            LimbahElektronik itemDipilih = katalogLimbah.get(nomorItem);

            System.out.print("Masukkan jumlah unit: ");
            int jumlah = readInt();

            if (jumlah <= 0) {
                System.out.println("[!] Jumlah unit harus lebih dari 0.");
            } else if (jumlah > itemDipilih.getStokUnit()) {
                System.out.println("[!] Stok unit tidak mencukupi! Stok tersedia: " + itemDipilih.getStokUnit());
            } else {
                keranjangSistem.add(new ItemTransaksi(itemDipilih, jumlah));
                itemDipilih.setStokUnit(itemDipilih.getStokUnit() - jumlah);
                System.out.println("[✓] " + jumlah + " unit " + itemDipilih.getNamaItem() + " berhasil masuk keranjang.");
            }
        } else {
            System.out.println("[!] Nomor item tidak valid.");
        }
    }

    private static void lihatKeranjang() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("             Rincian Keranjang Transaksi          ");
        System.out.println("--------------------------------------------------");

        if (keranjangSistem.isEmpty()) {
            System.out.println("Keranjang transaksi masih kosong.");
            return;
        }

        double totalNilai = 0;
        for (int i = 0; i < keranjangSistem.size(); i++) {
            ItemTransaksi item = keranjangSistem.get(i);
            System.out.printf("%d. %s x%d unit @ Rp%.0f = Rp%.0f%n",
                    (i + 1),
                    item.getItem().getNamaItem(),
                    item.getJumlah(),
                    item.getItem().hitungHargaTotal(),
                    item.getSubtotal());
            totalNilai += item.getSubtotal();
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("TOTAL ESTIMASI TRANSAKSI: Rp%.0f%n", totalNilai);
    }

    private static void prosesCheckout() {
        if (keranjangSistem.isEmpty()) {
            System.out.println("\n[!] Keranjang transaksi kosong. Checkout dibatalkan.");
            return;
        }

        lihatKeranjang();

        double totalSblmIncentive = 0;
        for (ItemTransaksi item : keranjangSistem) {
            totalSblmIncentive += item.getSubtotal();
        }

        double insentifHijau = 0;
        if (totalSblmIncentive >= 1000000) {
            insentifHijau = totalSblmIncentive * 0.15;
            System.out.println("\n[Eco-Bonus] Transaksi skala besar! Anda mendapatkan Subsidi Hijau 15%!");
        } else if (totalSblmIncentive >= 500000) {
            insentifHijau = totalSblmIncentive * 0.10;
            System.out.println("\n[Eco-Bonus] Anda mendapatkan Subsidi Hijau 10%!");
        }

        double totalTagihan = totalSblmIncentive - insentifHijau;
        System.out.printf("Subsidi/Insentif Hijau: Rp%.0f%n", insentifHijau);
        System.out.printf("TOTAL AKHIR TAGIHAN    : Rp%.0f%n", totalTagihan);

        System.out.println("\nPilih Metode Pembayaran:");
        System.out.println("1. E-Wallet / Green-Pay");
        System.out.println("2. Transfer Bank / Virtual Account");
        System.out.println("3. Kredit Karbon / Eco-Points");
        System.out.print("Pilihan (1-3): ");
        int metode = readInt();

        String namaMetode = "";
        switch (metode) {
            case 1: namaMetode = "E-Wallet / Green-Pay"; break;
            case 2: namaMetode = "Transfer Bank / VA"; break;
            case 3: namaMetode = "Kredit Karbon / Eco-Points"; break;
            default: namaMetode = "Metode Pembayaran Umum"; break;
        }

        double bayar = 0;
        // Looping: Do-While
        do {
            System.out.printf("Masukkan Jumlah Pembayaran (%s): Rp", namaMetode);
            bayar = readDouble();

            if (bayar < totalTagihan) {
                System.out.printf("[!] Nominal pembayaran kurang Rp%.0f. Masukkan ulang!%n", (totalTagihan - bayar));
            }
        } while (bayar < totalTagihan);

        double kembalian = bayar - totalTagihan;

        System.out.println("\n==================================================");
        System.out.println("         STRUK TRANSAKSI & ECO-CERTIFICATE        ");
        System.out.println("==================================================");
        System.out.println("Metode Bayar : " + namaMetode);
        System.out.println("--------------------------------------------------");
        for (ItemTransaksi item : keranjangSistem) {
            System.out.printf("%-25s x%-2d Rp%.0f%n", 
                    item.getItem().getNamaItem(), item.getJumlah(), item.getSubtotal());
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("Subtotal     : Rp%.0f%n", totalSblmIncentive);
        System.out.printf("Insentif     : Rp%.0f%n", insentifHijau);
        System.out.printf("Total Tagihan: Rp%.0f%n", totalTagihan);
        System.out.printf("Dibayar      : Rp%.0f%n", bayar);
        System.out.printf("Kembalian    : Rp%.0f%n", kembalian);
        System.out.println("==================================================");
        System.out.println(" Terimakasih telah menyelamatkan e-waste bumi! ");
        System.out.println("==================================================\n");

        keranjangSistem.clear();
    }

    private static void kelolaStokEwaste() {
        tampilkanKatalog();
        System.out.print("\nPilih nomor item limbah untuk penambahan stok (restock): ");
        int nomor = readInt() - 1;

        if (nomor >= 0 && nomor < katalogLimbah.size()) {
            LimbahElektronik item = katalogLimbah.get(nomor);
            System.out.print("Masukkan jumlah unit limbah baru: ");
            int jml = readInt();

            System.out.print("Masukkan sumber pasokan (Misal: Drop Point IT Center / Kemitraan PT): ");
            scanner.nextLine();
            String sumber = scanner.nextLine();

            if (sumber.trim().isEmpty()) {
                item.tambahStok(jml);
            } else {
                item.tambahStok(jml, sumber);
            }
        } else {
            System.out.println("[!] Nomor item tidak valid.");
        }
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("[!] Input harus angka. Masukkan lagi: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double readDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("[!] Input harus angka. Masukkan lagi: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
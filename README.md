# 📝 DOKUMENTASI PROJEK
## GreenTech & E-Waste Management System
**Nama**: Mikhel Febian

**NIM**: 2509116056

**Mata Kuliah**: Pemrograman Berorientasi Objek (PBO) Java  

**Tugas**: Projek Ujian Tengah Semester (UTS)  

---

## 1. 📄 Deskripsi Proyek

### 1.1 Ringkasan Fungsi dan Kegunaan Program
**GreenTech & E-Waste Management System** adalah aplikasi konsol berbasis Pemrograman Berorientasi Objek (PBO) Java yang dirancang untuk mengelola rantai daur ulang limbah elektronik (*e-waste*), perdagangan komponen bekas teruji (*e-part*), perangkat pemulihan (*refurbished*), serta penerbitan sertifikat kredit karbon (*carbon offset*).

Aplikasi ini bertujuan untuk:
- Mengotomatisasi perhitungan harga produk daur ulang berdasarkan berat fisik, biaya garansi *quality control*, dan biaya pencatatan sertifikasi digital.
- Memfasilitasi sistem insentif/subsidi hijau bertingkat bagi pengguna yang melakukan transaksi ramah lingkungan.
- Menyediakan mekanisme pengisian ulang stok (*restock*) barang bekas dari masyarakat/penyetor.
- Menerbitkan bukti transaksi lengkap beserta sertifikat dampak lingkungan (*Eco-Certificate*).

### 1.2 Implementasi 4 Elemen Wajib PBO
Sistem ini dibangun secara komprehensif dengan menerapkan 4 pilar utama PBO:

* **Inheritance (Pewarisan Kelas)**:
  - **Abstract Superclass**: `LimbahElektronik` sebagai induk abstrak yang menyimpan properti dasar (`idItem`, `namaItem`, `hargaDasar`, `stokUnit`).
  - **Single Inheritance**: `EPartFisik` menurunkan atribut dan method dari `LimbahElektronik`.
  - **Multilevel Inheritance**: `PerangkatRefurbished` menurunkan atribut dan method dari `EPartFisik` $\rightarrow$ `LimbahElektronik`.
  - **Hierarchical Inheritance**: `SertifikatKreditKarbon` dan `EPartFisik` sama-sama menurunkan dari `LimbahElektronik`.

* **Polymorphism (Polimorfisme)**:
  - **Method Overriding**: Method `hitungHargaTotal()` dan `tampilkanDetail()` di-override pada tiap subclass untuk menyesuaikan komponen biaya fisik, QC, garansi, maupun sertifikasi digital.
  - **Method Overloading**: Method `tambahStok(int jumlah)` dan `tambahStok(int jumlah, String sumberLimbah)` pada kelas `LimbahElektronik` memiliki nama yang sama dengan parameter berbeda.

* **Condition (Percabangan)**:
  - `if-else` / `else-if`: Digunakan pada validasi batas stok, perhitungan subsidi/diskon hijau bertingkat (diskon 10% untuk transaksi $\ge$ Rp 500.000 dan 15% untuk $\ge$ Rp 1.000.000), serta verifikasi ketercukupan pembayaran.
  - `switch-case`: Mengontrol navigasi menu utama (0-5) dan pilihan metode pembayaran lingkungan.

* **Looping (Perulangan)**:
  - `while`: Menjaga aplikasi konsol tetap berjalan interaktif hingga pengguna memilih menu keluar.
  - `for`: Mengiterasi koleksi `ArrayList` pada penayangan katalog barang dan rincian keranjang belanja.
  - `do-while`: Mengulang permintaan input nominal uang pembayaran sampai jumlah pembayaran mencukupi tagihan.

---

## 2. 🔀 Alur Program

### 2.1 Petunjuk Eksekusi Program

#### Menjalankan via Apache NetBeans IDE
1. Buka **Apache NetBeans IDE**.
2. Klik **File** $\rightarrow$ **Open Project...** $\rightarrow$ Pilih folder `GreenTechEWasteSystem`.
3. Buka file `MainApp.java` yang terletak pada package `com.greentech.ewaste`.
4. Tekan **Shift + F6** atau klik kanan `MainApp.java` lalu pilih **Run File**.

#### Menjalankan via Terminal / Command Prompt
1. Masuk ke direktori sumber proyek:
   ```bash
   cd GreenTechEWasteSystem/src
   ```
2. Kompilasi seluruh file Java:
   ```bash
   javac com/greentech/ewaste/*.java
   ```
3. Eksekusi program utama:
   ```bash
   java com.greentech.ewaste.MainApp
   ```

### 2.2 Cara Kerja dan Flow Sistem
1. **Inisialisasi Sistem**: Program memuat objek katalog awal (`EPartFisik`, `PerangkatRefurbished`, `SertifikatKreditKarbon`) ke dalam `ArrayList<LimbahElektronik>`.
2. **Navigasi Menu Utama**: Pengguna disajikan 6 pilihan menu interaktif:
   - `[1]` Lihat Katalog Produk & E-Waste
   - `[2]` Tambah Barang ke Keranjang Belanja
   - `[3]` Lihat Keranjang & Checkout Transaksi
   - `[4]` Restock Pasokan Limbah (Penyetoran)
   - `[5]` Informasi Sistem & Dampak Lingkungan
   - `[0]` Keluar Aplikasi
3. **Proses Transaksi & Checkout**:
   - Pengguna memilih barang dan kuantitas.
   - Sistem secara otomatis menghitung subtotal menggunakan method polymorphic `hitungHargaTotal()`.
   - Sistem menerapkan potongan subsidi hijau berdasarkan total transaksi.
   - Pengguna memilih metode pembayaran dan memasukkan nominal uang.
4. **Penerbitan Struk & Eco-Certificate**: Sistem mencetak ringkasan transaksi, estimasi pengurangan emisi CO2, dan memperbarui stok barang secara real-time.

---

## 3. 🖼️ Penjelasan Gambar

### 3.1 Diagram Kelas (Class Diagram)
Berikut adalah struktur hirarki kelas dan relasi antar komponen dalam sistem:

```text
                     +---------------------------------+
                     |  abstract LimbahElektronik     |
                     +---------------------------------+
                     | - idItem: String                |
                     | - namaItem: String              |
                     | - hargaDasar: double            |
                     | - stokUnit: int                 |
                     +---------------------------------+
                     | + hitungHargaTotal(): double    |
                     | + tampilkanDetail(): void       |
                     | + tambahStok(int): void         |
                     | + tambahStok(int, String): void |
                     +---------------------------------+
                                     |
            +------------------------+------------------------+
            |                                                 |
+------------------------+                       +--------------------------+
|      EPartFisik        |                       | SertifikatKreditKarbon   |
+------------------------+                       +--------------------------+
| - beratKg: double      |                       | - estimasiKarbonKg: double|
+------------------------+                       +--------------------------+
| + hitungHargaTotal()   |                       | + hitungHargaTotal()     |
| + tampilkanDetail()    |                       | + tampilkanDetail()      |
+------------------------+                       +--------------------------+
            |
+------------------------+
| PerangkatRefurbished   |
+------------------------+
| - garansiHijauBulan: int|
+------------------------+
| + hitungHargaTotal()   |
| + tampilkanDetail()    |
+------------------------+
```

**Penjelasan Komponen Kelas**:
- **`LimbahElektronik` (Abstract Class)**: Induk dasar yang menjamin seluruh anak kelas memiliki method standar `hitungHargaTotal()` dan `tampilkanDetail()`.
- **`EPartFisik`**: Menangani komponen fisik bekas dengan komponen perhitungan harga berbasis berat fisik (`beratKg`).
- **`PerangkatRefurbished`**: Menangani perangkat elektronik teruji dengan penambahan biaya garansi hijau (*Quality Control*).
- **`SertifikatKreditKarbon`**: Produk digital *offsetting* emisi berbasis estimasi konversi serapan emisi CO2 (`estimasiKarbonKg`).
- **`ItemTransaksi`**: Kelas pembantu (*helper class*) untuk menampung item, kuantitas, dan subtotal di dalam keranjang belanja.
- **`MainApp`**: Kelas utama (*driver class*) yang memuat fungsi `main()`, navigasi menu konsol, dan logika alur transaksi.

---

### 3.2 Tangkapan Layar Output Program (Screenshots)

Berikut adalah dokumentasi alur kerja dan penjelasan untuk setiap tampilan output antarmuka *Greentech & E-Waste Management System*:

---

### 1. Menu 1: Menampilkan Katalog Komoditas & Produk Daur Ulang

<img width="1450" height="427" alt="image" src="https://github.com/user-attachments/assets/ca8b0046-81bb-4d80-8b66-ea2dd4d05272" />

**Penjelasan:**
Tampilan ini muncul saat pengguna memilih **Menu 1**. Sistem menampilkan seluruh daftar item dalam katalog secara detail, meliputi:
* Kode dan nama produk (Komoditas, Refurbished, atau Sertifikat Offset Karbon).
* Harga dasar, kriteria spesifik (berat, garansi, atau estimasi emisi $\text{CO}_2$), serta sisa stok.
* Total harga akhir yang sudah mencakup biaya penanganan (*handling/QC*) atau biaya platform.

---

### 2. Menu 2: Masukkan Item ke Keranjang (Validasi Stok)

<img width="1463" height="506" alt="image" src="https://github.com/user-attachments/assets/f3634b7a-8b3f-4797-bfab-a639bf1f82b6" />

**Penjelasan:**
Tampilan ini menunjukkan interaksi **Menu 2** untuk memasukkan produk ke keranjang belanja, sekaligus memperlihatkan fitur penanganan error (*validation handling*):
* Pengguna mencoba membeli **Item 1** sebanyak **20 unit**.
* Karena stok yang tersedia hanya **15 unit**, sistem menolak input tersebut dan menampilkan notifikasi peringatan: `[!] Stok unit tidak mencukupi! Stok tersedia: 15`.

---

### 3. Menu 3: Cek Keranjang & Estimasi Eco-Impact (Keranjang Kosong)

<img width="1041" height="335" alt="image" src="https://github.com/user-attachments/assets/d1364479-a0bd-4b35-8797-4d7534eec8bb" />

**Penjelasan:**
Tampilan saat pengguna memilih **Menu 3** untuk melihat isi keranjang belanja dan kalkulasi *Eco-Impact*. Karena belum ada produk yang berhasil ditambahkan sebelumnya, sistem menginformasikan status dengan pesan: `Keranjang transaksi masih kosong.`.

---

### 4. Menu 4: Proses Transaksi / Checkout (Keranjang Kosong)

<img width="500" height="273" alt="image" src="https://github.com/user-attachments/assets/2c2f19af-cfa2-4794-8ee3-f888dadc7ca1" />

**Penjelasan:**
Tampilan saat pengguna memilih **Menu 4** untuk menyelesaikan pembelian (*checkout*). Sistem secara otomatis memverifikasi ketersediaan isi keranjang. Jika keranjang kosong, proses transaksi dibatalkan dengan pemberitahuan: `[!] Keranjang transaksi kosong. Checkout dibatalkan.`.

---

### 5. Menu 5: Kelola Pasokan E-Waste (Restock)

<img width="1453" height="521" alt="image" src="https://github.com/user-attachments/assets/211008ca-7acd-4e87-aec0-b9e33fde934f" />

**Penjelasan:**
Tampilan fungsi manajemen pasokan pada **Menu 5** untuk memperbarui stok barang dari penyedia/mitra:
* Pengguna memilih item **1** (*Papan PCB Mainboard Bekas*).
* Memasukkan jumlah penambahan stok sebesar **100 unit**.
* Mencantumkan sumber pasokan, misalnya: **Telkom Indonesia**.
* Sistem berhasil memperbarui jumlah pasokan dan menampilkan pesan konfirmasi penambahan stok.

---

### 6. Menu 0: Keluar Aplikasi

<img width="611" height="266" alt="image" src="https://github.com/user-attachments/assets/73ccad87-0526-48e8-a750-159597534050" />

**Penjelasan:**
Tampilan saat pengguna memilih **Menu 0** untuk mengakhiri sesi penggunaan aplikasi. Sistem menghentikan program dan memberikan pesan penutup: `[Info] Terima kasih telah berkontribusi dalam kelestarian bumi!`.

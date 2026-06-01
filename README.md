# 🍽️ HapHapHap - Aplikasi Penelusuran & Manajemen Resep Kuliner

[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)
[![JavaFX Version](https://img.shields.io/badge/JavaFX-17%2B-blue.svg)](https://openjfx.io/)
[![Build Tool](https://img.shields.io/badge/Maven-3.8%2B-red.svg)](https://maven.apache.org/)
[![Database](https://img.shields.io/badge/Database-MySQL-blue.svg)](https://www.mysql.com/)

**HapHapHap** adalah aplikasi desktop modern berbasis **JavaFX** yang dirancang untuk membantu pengguna menjelajah, menyimpan, mengelola, serta mengekspor resep masakan kuliner nusantara dan mancanegara secara interaktif dan dinamis.

Aplikasi ini mengadopsi standar arsitektur perangkat lunak yang bersih menggunakan **MVC (Model-View-Controller)**, pemisahan logika bisnis menggunakan **Service Layer**, serta abstraksi akses database dengan **DAO (Data Access Object) Pattern**.

---

## ✨ Fitur Utama

Aplikasi HapHapHap terbagi ke dalam empat pilar fitur utama:

### 🔍 1. Sistem Pencarian, Filter & Kategori Cerdas
*   **Pencarian Masakan:** Mencari resep favorit dengan mengetikkan nama masakan secara instan (real-time filtering).
*   **Kategorisasi Makanan:** Menyaring resep secara langsung dari sidebar navigasi (Kategori: Makanan Utama, Dessert, & Minuman).
*   **Filter Bahan Interaktif:** Menyaring makanan berdasarkan ketersediaan bahan dapur di rumah menggunakan sistem penambahan tag bahan dinamis (sistem tag interaktif dengan tombol hapus "✕").

### 🍳 2. Manajemen Resep Mandiri (CRUD Resep)
*   **Pembuatan Resep Dinamis:** Menambahkan resep pribadi lengkap dengan formulir dinamis (tambah/hapus kolom bahan & langkah memasak secara langsung pada UI).
*   **Penyuntingan & Penghapusan:** Mengedit kembali resep buatan Anda atau menghapusnya secara permanen dengan proteksi database (manual cascade delete).
*   **Manajemen Gambar & Galeri:** Mengunggah foto masakan kustom dengan mekanisme rendering resolusi aman dan *image caching* otomatis untuk performa yang ringan.

### 🔑 3. Otentikasi User & Hak Akses Guest (Tamu)
*   **Sistem Sesi Aman:** Keamanan login & pendaftaran akun baru terenkripsi yang dikelola oleh session manager global.
*   **Hak Akses Guest (Tamu):** Akses eksplorasi instan tanpa perlu mendaftar akun. Fitur sensitif seperti tambah resep, daftar favorit, dan moderasi akan disembunyikan secara otomatis.
*   **Ekspor Resep Mandiri:** Mengekspor satu resep pilihan atau seluruh daftar resep favorit sekaligus ke dalam satu berkas teks (`.txt`) yang terformat rapi untuk dibaca secara offline.

### 👑 4. Dasbor Moderasi & Favorit
*   **Daftar Masakan Favorit:** Menambahkan masakan ke daftar favorit pribadi menggunakan ikon tombol hati (`♥` / `♡`) interaktif.
*   **Sistem Moderasi Admin:** Akses dasbor khusus **Admin** untuk meninjau, menyetujui (*Approve*), atau menolak (*Reject*) kiriman resep baru dari para pengguna sebelum dirilis secara publik.
*   **Kontrol Penuh Admin:** Admin memiliki akses langsung di halaman explore untuk mengedit atau menghapus paksa resep masakan yang tidak sesuai aturan.

---

## 🛠️ Spesifikasi Teknologi (Tech Stack)

*   **Bahasa Pemrograman:** Java (SE 17 ke atas)
*   **Framework GUI:** JavaFX 17+ & OpenJFX (FXML & CSS Styling)
*   **Manajemen Dependensi:** Maven
*   **Database:** MySQL (dengan Driver JDBC Connector)
*   **Desain Pattern:** MVC, DAO Pattern, Service Layer, & Singleton Pattern

---

## 📂 Struktur Direktori Proyek

```bash
HapHapHap/
│
├── src/main/java/
│   ├── com/felix_71241153/app/copy_teletubies_haphaphap/
│   │   └── mainApp.java            # Titik awal masuk (Entrypoint) aplikasi JavaFX
│   │
│   ├── controller/                 # Logika interaksi Antarmuka (UI Controllers)
│   │   ├── addResepController.java
│   │   ├── detailController.java
│   │   ├── exploreController.java
│   │   ├── favoritController.java
│   │   ├── homeController.java
│   │   ├── itemResepController.java
│   │   ├── itemRowController.java
│   │   ├── loginController.java
│   │   ├── moderasiAdminController.java
│   │   ├── myRecipesController.java
│   │   └── registerController.java
│   │
│   ├── dao/                        # Interface Data Access Object
│   │   ├── ResepDao.java
│   │   └── UserDao.java
│   │
│   ├── database/                   # Implementasi Query JDBC MySQL
│   │   ├── resepDB.java
│   │   └── userDB.java
│   │
│   ├── model/                      # Class Representasi Objek (POJO)
│   │   ├── Bahan.java
│   │   ├── Resep.java
│   │   └── User.java
│   │
│   ├── service/                    # Business Logic & Validasi Form
│   │   ├── RecipeService.java
│   │   └── UserService.java
│   │
│   └── util/                       # Helper & Utility Classes
│       ├── databaseUtil.java       # Pengendali koneksi database
│       ├── imageUtil.java          # Pengendali pemuatan & caching gambar
│       └── sessionManager.java     # Pengendali sesi login global
│
├── src/main/resources/
│   ├── com/felix_71241153/app/copy_teletubies_haphaphap/  # Berkas XML Desain GUI (.fxml)
│   └── images/                     # Direktori penyimpanan aset gambar masakan
│
├── pom.xml                         # Berkas konfigurasi dependensi Maven
└── README.md                       # Berkas dokumentasi proyek
```

---

## 🚀 Cara Menjalankan Proyek di Lokal

### 1. Prasyarat Sistem
*   Java Development Kit (JDK) 17 atau versi terbaru.
*   Apache Maven terpasang di sistem.
*   XAMPP / MySQL Server aktif.

### 2. Konfigurasi Database MySQL
1.  Aktifkan MySQL di control panel XAMPP Anda.
2.  Buka browser lalu akses `http://localhost/phpmyadmin/`.
3.  Buat database baru bernama **`haphaphap`**.
4.  Import skema tabel database (tabel `user`, `resep`, `bahan`, `resep_bahan`, dan `favorit_user`).
5.  *(Opsional)* Anda dapat menyesuaikan port, username, dan password database di berkas:
    `src/main/java/util/databaseUtil.java`

### 3. Kompilasi & Jalankan Aplikasi
Buka terminal/command prompt di direktori root proyek ini, kemudian jalankan perintah Maven berikut:

```bash
# Bersihkan proyek dan kompilasi ulang kelas
mvn clean compile

# Jalankan aplikasi JavaFX
mvn javafx:run
```

---

## 👥 Tim Pengembang (Teletubies)
Aplikasi HapHapHap ini dikembangkan dengan dedikasi tinggi oleh tim **Teletubies** melalui metode kolaborasi pembagian tugas pengerjaan yang terstruktur.

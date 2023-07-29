# Tugas Pemrograman 4 - SimProyek
CSGE601021 Dasar-Dasar Pemrograman 2 @ Fakultas Ilmu Komputer Universitas Indonesia,
Semester Pendek – 2022/2023

## Dokumen Soal
Dokumen soal dapat diakses melalui [link ini](https://docs.google.com/document/d/1NHqn8PeH67QWjKNaAAPbUYIWis6pG-GG77Y3MaU5KU8/).


## Panduan Memulai
1. Tambahkan ***remote*** baru bernama **`upstream`** untuk mendapatkan code dari repository DDP2 dengan perintah:
    ```bash
    git remote add upstream https://gitlab.com/DDP2-CSUI/sp-ddp2-2023/tp4-sp-ddp2.git
    ```
    Jika sebelumnya anda telah menambahkan **`upstream`**, Anda dapat mengganti url **`upstream`** ke DDP2 dengan perintah:
    ```bash
    git remote set-url upstream https://gitlab.com/DDP2-CSUI/sp-ddp2-2023/tp4-sp-ddp2.git
    ```
    > Note: Sekarang, pada git anda terdapat 2 source remote, yakni: **`origin`**, repository milik anda dan **`upstream`**, repository DDP2 tempat soal dan template Tugas Pemrograman 3 berada.
2. Buat direktori baru pada folder TP4 anda kemudian pindahkan terminal ke ***path*** tersebut.
3. Lakukan ***pull*** dari remote **`upstream`** dengan perintah 
    ```bash
    git pull upstream main
    ```

## Menyelesaikan konflik

Jika terjadi *merge conflict*, silakan selesaikan konflik yang ada dan

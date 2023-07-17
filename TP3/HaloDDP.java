import java.util.Scanner;

/**
 * Program untuk mengelola lemari obat dalam aplikasi "Haloddp".
 */
public class HaloDDP {

    /**
     * Metode utama yang akan dijalankan saat program dijalankan.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        runHaloddp();
    }

    /**
     * Metode untuk menjalankan aplikasi Haloddp.
     */
    public static void runHaloddp() {
        int BASE_PRICE = 5000;
        Scanner input = new Scanner(System.in);

        System.out.print("Selamat datang Haloddp. Berapa ukuran lemari obat hari ini? (max row 5) ");

        // TODO : Implementasi validasi input ukuran lemari
        String ukuran = "";
        do {
            System.out.print("Ukuran: ");
            ukuran = input.nextLine();
        } while (!ukuran.matches("\\d+x\\d+"));
        String[] ukuranSplit = ukuran.split("x");
        int rowLemari = Integer.valueOf(ukuranSplit[0]);
        int columnLemari = Integer.valueOf(ukuranSplit[1]);


        // TODO : Buat objek lemari dengan ukuran yang sudah ditentukan
        Lemari lemari = new Lemari(rowLemari);

        System.out.println("Silahkan tentukan kategori obat untuk setiap rak");
        // TODO : Implementasi input kategori rak
        for (int i = 0; i < rowLemari; i++) {
            System.out.print("Rak ke-" + (i + 1) + ": ");
            String kategori = input.nextLine();
            lemari.addRak(new Rak(columnLemari, kategori), i);
            Rak rak = lemari.getRak(i);
            Obat med = new Obat("Kosong", 0, kategori);
            System.out.println("Rak ke-" + (i + 1) + " adalah rak obat " + kategori);
            for (int k = 0; k < columnLemari; k++) {
                rak.tambahObat(med, k);
            }
        }

        System.out.println("Rak obat hari ini: ");
        lemari.print();

        while (true) {
            System.out.println();
            System.out.println("Menu:");
            System.out.println("1. Tambah obat");
            System.out.println("2. Lihat obat");
            System.out.println("3. Beli obat");
            System.out.println("99. Keluar");
            System.out.print("Pilih menu: ");
            String menu = input.nextLine();

            if (menu.equals("1")) {
                tambahObat(lemari, rowLemari, columnLemari, input);
            } else if (menu.equals("2")) {
                lihatObat(lemari);
            } else if (menu.equals("3")) {
                beliObat(lemari, input);
            } else if (menu.equals("99")) {
                keluar();
                break;
            } else {
                System.out.println("Menu tidak tersedia");
            }
        }

        input.close();
        System.out.println("Terima kasih telah menggunakan Haloddp!");
    }

    /**
     * Metode untuk menambahkan obat ke dalam lemari.
     *
     * @param lemari        objek lemari obat
     * @param rowLemari     jumlah baris lemari
     * @param columnLemari  jumlah kolom lemari
     * @param input         objek Scanner untuk menerima input dari pengguna
     */
    public static void tambahObat(Lemari lemari, int rowLemari, int columnLemari, Scanner input) {
        System.out.print("Masukkan nama obat: ");
        String namaObat = input.nextLine();

        System.out.print("Masukkan kategori obat: ");
        String kategoriObat = input.nextLine();
        // Cek kategori valid
        boolean kategoriValid = false;
        for (int i = 0; i < rowLemari; i++) {
            Rak rak = lemari.getRak(i);
            if (rak.getKategoriRak().equals(kategoriObat)) {
                kategoriValid = true;
                System.out.println("Kategori obat valid");
            }
        }
        if (!kategoriValid) {
            System.out.println("Kategori obat tidak valid");
            return;
        }
        System.out.print("Masukkan posisi obat: ");
        String posisiObat = input.nextLine();
        // Cek kategori obat valid
        while (!posisiObat.matches("\\d+,\\d+")) {
            System.out.println("Format posisi obat salah");
            System.out.print("Masukkan posisi obat: ");
            posisiObat = input.nextLine();
        }

        String[] posisiObatSplit = posisiObat.split(",");
        int row = Integer.valueOf(posisiObatSplit[0]);
        int column = Integer.valueOf(posisiObatSplit[1]);
        Rak rak = null;
        if (rowLemari >= row) {
            rak = lemari.getRak(row - 1);
            if (rak.getKategoriRak().equals(kategoriObat)) {
                // System.out.println("Kategori rak valid");
            } else {
                System.out.println("Kategori rak tidak valid");
                return;
            }
        } else {
            System.out.println("Posisi tidak ada di lemari");
            return;
        }

        System.out.print("Masukkan stok obat: ");
        int stokObat = Integer.parseInt(input.nextLine());

        //TODO: Hitung harga

        Obat obat = new Obat(namaObat, stokObat, kategoriObat);
        obat.setHarga(5000 + (row - 1) * 10000 + (column - 1) * 5000);
        rak.tambahObat(obat, column - 1);
    }

    /**
     * Metode untuk melihat obat-obatan dalam lemari.
     *
     * @param lemari objek lemari obat
     */
    public static void lihatObat(Lemari lemari) {
        lemari.print();
    }

    /**
     * Metode untuk membeli obat dari lemari.
     *
     * @param lemari objek lemari obat
     * @param input  objek Scanner untuk menerima input dari pengguna
     */
    public static void beliObat(Lemari lemari, Scanner input) {
        System.out.print("Masukkan nama obat yang ingin dibeli: ");
        String namaObat = input.nextLine();
        System.out.print("Masukkan jumlah obat yang ingin dibeli: ");
        int jumlahObat = Integer.parseInt(input.nextLine());

        if (lemari.beliObat(namaObat, jumlahObat)) {
            System.out.println("Berhasil membeli obat");
        } else {
            System.out.println("Gagal membeli obat");
        }
    }

    /**
     * Metode untuk keluar dari aplikasi.
     */
    public static void keluar() {
        // TODO: Riwayat Transaksi
    }
}

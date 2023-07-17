import java.util.Scanner;

public class HaloDDP {
    public static void main(String[] args) {
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
            System.out.print("Rak ke-" + (i+1) + ": ");
            String kategori = input.nextLine();
            lemari.addRak(new Rak(columnLemari, kategori), i);
            System.out.println("Rak ke-" + (i+1) + " adalah rak obat " + kategori);
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
                // TODO : Implementasi input obat
                System.out.print("Masukkan nama obat: ");
                String namaObat = input.nextLine();

                System.out.print("Masukkan kategori obat: ");
                String kategoriObat = input.nextLine();
                // Cek kategori valid
                Boolean kategoriValid = false;
                for (int i = 0; i < rowLemari ; i++ ) {
                    Rak rak = lemari.getRak(i);
                    if (rak.getKategoriRak().equals(kategoriObat)) {
                        kategoriValid = true;
                        System.out.println("Kategori obat valid");
                    }
                }
                if (!kategoriValid) {
                    System.out.println("Kategori obat tidak valid");
                    continue;
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
                    rak = lemari.getRak(row-1);
                    if (rak.getKategoriRak().equals(kategoriObat)) {
                        // System.out.println("Kategori rak valid");
                    }
                    else {
                        System.out.println("Kategori rak tidak valid");
                        continue;
                    }
                }
                else {
                    System.out.println("Posisi tidak ada di lemari");
                }

                System.out.print("Masukkan stok obat: ");
                int stokObat = Integer.parseInt(input.nextLine());
                
                //TODO: Hitung harga

                Obat obat = new Obat(namaObat, stokObat, kategoriObat);
                obat.setHarga(5000 + (row-1)*10000 + (column-1)*5000);
                rak.tambahObat(obat, column-1);
            } else if (menu.equals("2")) {
                // TODO : Implementasi print obat
                    lemari.print();
            } else if (menu.equals("3")) {
                // TODO : Implementasi beli obat
                System.out.print("Masukkan nama obat yang ingin dibeli: ");
                String namaObat = input.nextLine();
                System.out.print("Masukkan jumlah obat yang ingin dibeli: ");
                int jumlahObat = Integer.parseInt(input.nextLine());

                if (lemari.beliObat(namaObat, jumlahObat)) {
                    System.out.println("Berhasil membeli obat");
                } else {
                    System.out.println("Gagal membeli obat");
                } else if (menu.equals("99")){
                    // TODO : Implementasi keluar
                    //TODO: Riwayat Transaksi
                    break;
                } else {
                    System.out.println("Menu tidak tersedia");
                }
            }

        input.close();
        System.out.println("Terima kasih telah menggunakan Haloddp!");
    }
}
}


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
            } else if (menu.equals("2")) {
                // TODO : Implementasi print obat
            } else if (menu.equals("3")) {
                // TODO : Implementasi beli obat
            } else if (menu.equals("99")){
                // TODO : Implementasi keluar
                break;
            } else {
                System.out.println("Menu tidak tersedia");
            }
        }

        input.close();
        System.out.println("Terima kasih telah menggunakan Haloddp!");
    }
}

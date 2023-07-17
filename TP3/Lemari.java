import java.util.Scanner;

/**
 * Representasi lemari obat dalam sistem HaloDDP
 */

public class Lemari {
    private int ukuran;
    private Rak[] rak;

    /**
     * Konstruktor untuk membuat objek lemari obat dengan ukuran yang ditentukan
     * @param ukuran untuk ukuran lemari obat
     */

    public Lemari(int ukuran){
        this.ukuran = ukuran;
        rak = new Rak[ukuran];
    }

    /**
     * Menambahkan rak obat ke dalam lemari pada indeks yang ditentukan
     * @param rak untuk rak obat yang akan ditambahkan
     * @param index untuk indeks tempat penambahan rak obat
     */

    public void addRak(Rak rak, int index){
        this.rak[index] = rak;
    }

    /**
     * Print isi lemari obat
     */

    // TODO : Implementasi method print
    public void print(){
        for (int i = (this.ukuran-1) ; i > -1 ; i--){
            System.out.println(this.rak[i].getKategoriRak());
            System.out.println("==================");
            this.rak[i].printRak();
            System.out.println("==================");
            System.out.println();
        }
    }

    /**
     * Membeli obat dengan jumlah yang ditentukan
     * @param obat untuk obat yang akan dibeli
     * @param jumlah untuk jumlah obat yang akan dibeluii
     * @return akan return true jika pembeli berhasil dan false jika pembeli tidak berhasil
     */

    // TODO : Implementasi method beliObat
    public boolean beliObat(Obat obat, int jumlah) {
        Scanner input = new Scanner(System.in);
        System.out.print("Obat apa yang ingin dibeli? ");
        String namaObat = input.nextLine();
        System.out.print("Ingin beli berapa banyak? ");
        int jumlahObat = Integer.parseInt(input.nextLine());

        Obat medicationObat = searchObat(namaObat);

        if(medicationObat == null){
            System.out.println("Obat tidak ditemukan");
            return false;
        }
        if (medicationObat.getStok() < jumlah){
            System.out.println("Stok obat tidak mencukupi");
            return false;
        }

        medicationObat.setStok(medicationObat.getStok()- jumlah);
        System.out.println("Berhasil membeli obat");
        return true;
    }

    /**
     * Mencari obat berdasarkan nama obat
     * @param namaObat untuk nama obat yang akan di cari
     * @return akan mengembalikan objek Obat jika ditemukan, null jika tidak
     */

    // TODO : Implementasi method searchObat
    public Obat searchObat(String namaObat) {
        for (Rak rak : rak){
            if (rak != null){
                Obat [] daftarObat = rak.getListObat();
                for (Obat obat : daftarObat){
                    if (obat != null && obat.getNama().equals(namaObat)){
                        return obat;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Mengembalikan rak obat pada indeks yang ditentukan
     * @param i untuk indeks rak obat yang akan dikembalikan
     * @return objek Rak pada indeks yang ditentukan
     */

    // TODO : Implementasi method getRak
    public Rak getRak(int i) {
        return this.rak [i];
    }
}

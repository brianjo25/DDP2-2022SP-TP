/**
 * Representasi rak obat dalam sistem HaloDDP
 */

public class Rak {
    private int size;
    private String kategori;
    private Obat[] daftarObat;

    /**
     * Konstruktor untuk membuat objek rak obat dengan ukuran dan kategori yang ditentukan
     * @param size untuk ukuran rak obat
     * @param kategori untuk kategori rak obat
     */

    public Rak(int size, String kategori){
        this.kategori = kategori;
        this.daftarObat = new Obat[size];
        this.size = size;
    }

    /**
     * Mengembalikan kategori dari rak
     * @return kategori rak obat
     */

    // TODO : Implementasi method getKategoriRak
    public String getKategoriRak() {
        return kategori;
    }

    /**
     * Menambahkan obat ke dalam rak obat pada indeks yang ditentukan
     * @param obat untuk obat yang akan ditambahkan
     * @param index untuk indeks tempat penambahan obat
     */

    // TODO : Implementasi method tambahObat
    public void tambahObat(Obat obat, int index) {
        this.daftarObat[index] = obat;
    }
    
    /**
     * Print isi rak obat ke layar
     */

    // TODO : Implementasi method printRak
    public void printRak(){
            for (int j = 0 ; j < this.size ; j++){
                if (daftarObat[j].getStok() == 0){
                    System.out.print("| Kosong ");
                }
                else{
                    System.out.print("| " + daftarObat[j].getNama() + " (stok: " + daftarObat[j].getStok() + ")");
                }
            }
            System.out.println();
        }
    
    /**
     * Mengembalikan daftar obat yang ada dalam rak obat
     * @return untuk array obat yang ada dalam rak obat
     */

    // TODO : Implementasi method getListObat
    public Obat[] getListObat() {
        return daftarObat;
    }
    
}

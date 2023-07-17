public class Rak {
    private int size;
    private String kategori;
    private Obat[] daftarObat;

    public Rak(int size, String kategori){
        this.kategori = kategori;
        this.daftarObat = new Obat[size];
        this.size = size;
    }

    // TODO : Implementasi method getKategoriRak
    public String getKategoriRak() {
        return kategori;
    }

    // TODO : Implementasi method tambahObat
    public void tambahObat(Obat obat, int index) {
        this.daftarObat[index] = obat;
    }
    
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
    

    // TODO : Implementasi method getListObat
    public Obat[] getListObat() {
        return daftarObat;
    }
    
}

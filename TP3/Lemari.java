public class Lemari {
    private int ukuran;
    private Rak[] rak;

    public Lemari(int ukuran){
        this.ukuran = ukuran;
        rak = new Rak[ukuran];
    }

    public void addRak(Rak rak, int index){
        this.rak[index] = rak;
    }

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

    // TODO : Implementasi method beliObat
    public boolean beliObat(Obat obat, int jumlah) {
        return false; 
    }

    // TODO : Implementasi method searchObat
    public Obat searchObat(String namaObat) {
        return null;
    }

    // TODO : Implementasi method getRak
    public Rak getRak(int i) {
        return this.rak [i];
    }
}

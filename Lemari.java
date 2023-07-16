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
        return;
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
        return null;
    }
}

public class Obat {
    private String kategori;
    private String nama;
    private int harga;
    private int stok;

/**
 * Konstruktor untuk membuat objek obat dengan nama, stock dan kategori yang ditentukan
 * @param nama untuk nama obat
 * @param stok untuk jumlah stock
 * @param kategori untuk kategori obat
 */

    public Obat(String nama, int stok, String kategori){
        this.nama = nama;
        this.stok = stok;
        this.kategori = kategori;
    }
    
    /**
     * Mengembalikan kategori obat
     * @return Kategori obat
     */

    // TODO : Implementasi method getter getKategori
    public String getKategori(){
        return kategori;
    }

    /**
     * Mengembalikan nama obat
     * @return nama obat
     */

    // TODO : Implementasi method getter getNama
    public String getNama(){
        return nama;
    }

    /**
     * Mengembalikan harga obat
     * @return harga obat
     */

    // TODO : Implementasi method getter getHarga
    public int getHarga(){
        return harga;
    }

    /**
     * Mengembalikan Stok obat
     * @return stok obat
     */

    // TODO : Implementasi method getter getStok
    public int getStok(){
        return stok;
    }  

    /**
     * Mengatur kategori obat
     * @return Kategori obat
     */

    // TODO : Implementasi method setter setKategori
    public void setKategori(String kategori){
        this.kategori = kategori;
    }

    /**
     * Mengatur stok obat
     * @param stok untuk stok obat
     */

    // TODO : Implementasi method setter setNama
    public void setStok(int stok){
        this.stok = stok;
    }

    /**
     * mengatur harga obat
     * @param harga untuk harga obat
     */

    // TODO : Implementasi method setter setHarga
    public void setHarga(int harga){
        this.harga = harga;
    }

}

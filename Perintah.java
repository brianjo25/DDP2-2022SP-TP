
import java.awt.Dimension;

/**
 * 
 * Perintah.java
 * <br><br>
 * Class {@code Perintah} merepresentasikan perintah-perintah umum yang 
 * dapat diberikan kepada kura-kura. Termasuk dalam class ini adalah
 * proses untuk membaca input (saat ini baru melalui satu baris perintah)
 * dan memanggil method yang berkesesuaian.
 * Dalam kelas ini juga disediakan method-method yang merupakan kumpulan-kumpulan
 * perintah berurutan yang dapat diterima oleh kurakura dan menghasilkan gambar 
 * tertentu. 
 * <br><br>
 * Tugas anda pada file ini: <br>
 * - Lengkapi javadoc comment pada tiap deklarasi method.<br>
 * - Lengkapi inline comment untuk tiap baris perintah yang penting.<br>
 * - Perbaiki method {@code lakukan} agar tidak menimbulkan error bila input salah<br>
 * - Buat (1) perintah {@code mundur <x>}" agar kura-kura bisa mundur sebanyak x pixel satuan.
 * - Buat (2) perintah {@code hadap kanan} dan {@code hadap kiri} yang akan membuat kura-kura 
 *   menghadap ke kanan (rotasi 90) dan ke kiri (rotasi -90) 
 * - Dapatkah anda membuat (3) kura-kura mengenali perintah 
 *   {@code loop 10 perintah-perintah} <br>
 *   yang artinya melakukan perintah-perintah sebanyak 10 kali? <br>
 *   contoh: "loop 10 rotasi 30 maju 30" <br>
 *           yang artinya akan melakukan perintah "rotasi 30", dilanjutkan <br>
 *           perintah "maju 30", secara berulang-ulang sebanyak 10 kali<br>
 *   contoh: "loop 5 maju 20 hadap kanan maju 30" <br>
 *           yang artinya akan melakukan perintah "maju 20", dilanjutkan <br>
 *           "hadap kanan", kemudian perintah "maju 10", <br> 
 *           secara berulang-ulang sebanyak 5 kali<br>
 * 
 * @author Ade Azurat for DPBO 2008 @ Fasilkom UI
 * @author Ade Azurat for DDP2 2023 @ Fasilkom UI
 */
public class Perintah {
    Canvas canvas;
    Kurakura kurakuraku; 
    
    /** Creates a new instance of Perintah */
    public Perintah(Kurakura k, Canvas canvas) {
        kurakuraku = k;
        this.canvas = canvas;
    }

    // Dapatkan anda membuat method ini lebih baik dan lebih mudah ditambahkan
    // atau di ubah? 
    public String lakukan(String inputPerintah){
        
        String[] in = inputPerintah.split(" ");

        /*Menerima input yang merupakan perintah akan apa yang akan dilakukan oleh turtle 
         *Beberapa juga akan menerima paramater (ada yang satu ada yang dua parameter) untuk memberikan mereka ukuran
         bentuk yang akan mereka buat.
        */

        boolean isArgumentValid = this.isArgumentValid(in);

        if (!isArgumentValid) {
            // Jika argumen yang diberikan tidak valid
            canvas.repaint(); 
            return "Argumen yang diberikan tidak sesuai dengan perintah.";
        } else {
            // Jika argumen sudah valid
            if (in[0].equalsIgnoreCase("selesai"))
                System.exit(0);
            else if (in[0].equalsIgnoreCase("reset"))
                kurakuraku.reset();
            else if (in[0].equalsIgnoreCase("maju"))
                kurakuraku.maju(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("mundur"))
                kurakuraku.mundur(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("rotasi"))
                kurakuraku.rotasi(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("segitigasikusiku"))
                buatSegitigaSikuSiku(Integer.parseInt(in[1]), Integer.parseInt(in[2]));
            else if (in[0].equalsIgnoreCase("kotak"))
                buatKotak(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("segitiga"))
                buatSegitiga(Integer.parseInt(in[1]));
            else if (in[0].equalsIgnoreCase("persegi"))
                buatPersegi(Integer.parseInt(in[1]), Integer.parseInt(in[2]));
            else if (in[0].equalsIgnoreCase("pohon"))
                buatPohon();
            else if (in[0].equalsIgnoreCase("jejak"))
                kurakuraku.setJejak(Boolean.parseBoolean(in[1]));
            else if (in[0].equalsIgnoreCase("pindah"))
                kurakuraku.setPosition(new Dimension(Integer.parseInt(in[1]),Integer.parseInt(in[2])));
            else {
                canvas.repaint(); 
                return "Perintah tidak dipahami.";
            }
        }
        
        canvas.repaint();    
        return "Perintah sudah dilaksanakan.";
    }
    /*Membuat bentuk kotak
     * menerima satu parameter berupa ukuran yang akan
     * mengatur seberapa besar kotak yang akan dibuat
     */
    public void buatKotak(int ukuran ){        
        for (int i=0;i<4;i++){
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(90);
        }
    }

    /*Membuat bentuk segitiga sama sisi
     * menerima satu parameter berupa ukurang yang akan
     * mengatur seberapa panjang ketiga sisi dari segitiga itu
     */
    public void buatSegitiga(int ukuran){
        for (int i=0;i<3;i++) {
            kurakuraku.maju(ukuran);
            kurakuraku.rotasi(-120);
        }

    }  
    
    /*Membuat segitiga siku-siku dan menerima 2 parameter
     * satu untuk tinggi, satu untuk panjang alas
     * Dilengkapi dengan perhitungan untuk menentukan sudut x dan y serta panjang sisi miring
     */
    public void buatSegitigaSikuSiku(int panjangAlas, int tinggi){
        double miring = Math.sqrt(Math.pow(panjangAlas, 2) + Math.pow(tinggi, 2)); 

        double sudutAlasDanMiring = Math.toDegrees(Math.asin((double) tinggi / miring));
        
        kurakuraku.maju(panjangAlas);
        kurakuraku.rotasi(-(180-sudutAlasDanMiring));
        kurakuraku.maju(miring);
        kurakuraku.rotasi(-(sudutAlasDanMiring + 90));   
        kurakuraku.maju(tinggi);
        kurakuraku.rotasi(-(90));     
    }

    /*Membuat sebuah persegi panjang
     * menerima dua parameter, satu untuk panjang dan satu untuk lebar
     * Kedua parameter ini akan menentukan seberapa besar persegi panjang yang akan di buat
     */
    public void buatPersegi(int panjang, int lebar){
        kurakuraku.maju(panjang);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(lebar);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(panjang);
        kurakuraku.rotasi(-90);
        kurakuraku.maju(lebar);
        kurakuraku.rotasi(-90);
    }
    
    /*Tidak menerima parameter
     * Membuat bentuk pohon dengan ranting-ranting sebagai cabangnya
     */
    public void buatPohon(){        
        kurakuraku.setJejak(false);
        kurakuraku.reset();
        kurakuraku.rotasi(90);
        kurakuraku.maju(100);
        kurakuraku.rotasi(180);
        buatPohon(6,50);        
        kurakuraku.reset();
    }
    
    private void buatPohon(int ukuran, int tinggi){
        if (ukuran>0){
            kurakuraku.setJejak(true);
            kurakuraku.maju(tinggi);                        
            kurakuraku.rotasi(-45);
            Dimension posAwal = kurakuraku.getPosition();
            double arah = kurakuraku.getArah();
            double sudut = arah;
            for(int i=0;i<3;i++){
                buatPohon(ukuran-1,(int)(tinggi/1.5));
                kurakuraku.setJejak(false);
                kurakuraku.setPosition(posAwal);
                kurakuraku.setArah(arah);   
                
                // Tambah kotak di ujung
                if (ukuran == 1) {
                    kurakuraku.setJejak(true);
                    buatKotak(10);
                    kurakuraku.setJejak(false);
                } 

                sudut+=45;
                kurakuraku.rotasi(sudut);
            }     
        }
        kurakuraku.reset();
    }


    private boolean isArgumentValid(String[] in) {
        // Inisiasi variabel pengecekan validitas argumen
        boolean argumen_ada_satu = false;
        boolean argumen_ada_dua = false;
        boolean argumen_type_valid = true;

        // Jika ada 1 argumen
        if (in.length >= 2) {
            argumen_ada_satu = true;

            if (in[0].equalsIgnoreCase("jejak")) {
                // Khusus jejak, cek apakah boolean
                if (!in[1].equalsIgnoreCase("true") && !in[1].equalsIgnoreCase("false")) {
                    argumen_type_valid = false; // kalau ada argumen bukan 'true' atau 'false', buat argumen_type_valid jadi false (GA VALID)
                }
            } else {
                // selain jejak, cek argumen apakah digit / angka
                for (char c : in[1].toCharArray()) { // "50" -> ["5", "0"]
                    if (!Character.isDigit(c)) {
                        argumen_type_valid = false; // kalau ada character yang bukan digit, buat argumen_type_valid jadi false (GA VALID)
                    }
                }
            }
            
        }

        // Jika ada 2 argumen
        if (in.length >= 3) {
            argumen_ada_dua = true;

            // argumen kedua cek valid atau tidak (digit atau bukan)
            for (char c : in[2].toCharArray()) { // "50" -> ["5", "0"]
                if (!Character.isDigit(c)) {
                    argumen_type_valid = false; // kalau ada character yang bukan digit, buat argumen_type_valid jadi false (GA VALID)
                }
            } 
        }

        // Perintah-perintah yang membutuhkan 1 argumen
        if (in[0].equalsIgnoreCase("maju") || in[0].equalsIgnoreCase("mundur") || in[0].equalsIgnoreCase("rotasi") || in[0].equalsIgnoreCase("kotak") || in[0].equalsIgnoreCase("segitiga") || in[0].equalsIgnoreCase("jejak")) {
            if (!argumen_ada_satu || !argumen_type_valid) {
                return false;
            }
        // Perintah-perintah yang membutuhkan 2 argumen
        } else if (in[0].equalsIgnoreCase("persegi") || in[0].equalsIgnoreCase("pindah") || in[0].equalsIgnoreCase("segitigasikusiku")) {
            if (!argumen_ada_dua || !argumen_type_valid) {
                return false;
            }
        }

        // Perintah yang tidak membutuhkan argumen ATAU argumen sudah valid
        // Return True
        return true;
    }
    
}

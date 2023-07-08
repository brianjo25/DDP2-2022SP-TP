
import java.awt.Dimension;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

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

        int index = 0;
        int loop_how_many = 1;

        if (in[0].equalsIgnoreCase("loop")) {           //Sebuah kondisi dimana ada loop diawal input yang akan mengantarkan ke fungsi looping

            if (in.length < 2 || !this.isArgumentValid(Arrays.copyOfRange(in, 0, 2))) {
                // Jika argumen yang diberikan tidak valid
                canvas.repaint(); 
                return "Argumen yang diberikan tidak sesuai dengan perintah.";
            }
            
            loop_how_many = Integer.parseInt(in[1]);                //Mengambil argumen dan menyimpannya di loop_how_many dalam bentuk integer

            index = 2;                                              //Menginisiasi index dengan nilai 2
        }

        /* Dapatkan perintah-perintah yang ada (terutama untuk loop),
        Jika bukan loop, maka isi dari list hanya 1 perintah saja
        */

        List<String> list_of_perintah = new ArrayList<String>();

        while (in.length > index) {
            int original_index = index;

            // Perintah-perintah yang membutuhkan 1 argumen
            if (in[original_index].equalsIgnoreCase("maju") || in[original_index].equalsIgnoreCase("mundur") || in[original_index].equalsIgnoreCase("rotasi") || in[original_index].equalsIgnoreCase("kotak") || in[original_index].equalsIgnoreCase("segitiga") || in[original_index].equalsIgnoreCase("jejak")) {
                index = index + 2;
            // Perintah-perintah yang membutuhkan 2 argumen
            } else if (in[original_index].equalsIgnoreCase("persegi") || in[original_index].equalsIgnoreCase("pindah") || in[original_index].equalsIgnoreCase("segitigasikusiku")) {
                index = index + 3;
            } else {
                index = index + 1;
            }

            String[] temp_list_split;
            if (in.length > index) {
                temp_list_split = Arrays.copyOfRange(in, original_index, index);
            } else {
                temp_list_split = Arrays.copyOfRange(in, original_index, in.length);
            }
            boolean isArgumentValid = this.isArgumentValid(temp_list_split);

            if (!isArgumentValid) {
                // Jika argumen yang diberikan tidak valid
                canvas.repaint(); 
                return "Argumen yang diberikan tidak sesuai dengan perintah.";      //mengisi pesan saat argumen tidak valid
            } else {
                list_of_perintah.add(String.join(" ", temp_list_split));    //Menambahkan perintah ke list
            }

            if (!in[0].equalsIgnoreCase("loop")) {                      //Jika tidak diawali dengan loop, maka akan melakukan break      
                break;
            }
        }

        // Lakukan sesuai banyak looping jika ada perintah loop
        // Atau 1 kali saja jika tanpa perintah loop
        for (int i=0; i < loop_how_many; i++) {

            /*Menerima input yang merupakan perintah akan apa yang akan dilakukan oleh turtle 
            *Beberapa juga akan menerima paramater (ada yang satu ada yang dua parameter) untuk memberikan mereka ukuran
            bentuk yang akan mereka buat.
            */

            // Untuk tiap loop, lakukan perintah-perintah yang ada pada list perintah
            for (int j=0; j < list_of_perintah.size(); j++) {
                String[] temp_split = list_of_perintah.get(j).split(" ");

                if (temp_split[0].equalsIgnoreCase("selesai"))
                    System.exit(0);
                else if (temp_split[0].equalsIgnoreCase("reset"))
                    kurakuraku.reset();
                else if (temp_split[0].equalsIgnoreCase("maju"))
                    kurakuraku.maju(Integer.parseInt(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("mundur"))
                    kurakuraku.mundur(Integer.parseInt(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("rotasi"))
                    kurakuraku.rotasi(Integer.parseInt(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("segitigasikusiku"))
                    buatSegitigaSikuSiku(Integer.parseInt(temp_split[1]), Integer.parseInt(temp_split[2]));
                else if (temp_split[0].equalsIgnoreCase("kotak"))
                    buatKotak(Integer.parseInt(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("segitiga"))
                    buatSegitiga(Integer.parseInt(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("persegi"))
                    buatPersegi(Integer.parseInt(temp_split[1]), Integer.parseInt(temp_split[2]));
                else if (temp_split[0].equalsIgnoreCase("pohon"))
                    buatPohon();
                else if (temp_split[0].equalsIgnoreCase("jejak"))
                    kurakuraku.setJejak(Boolean.parseBoolean(temp_split[1]));
                else if (temp_split[0].equalsIgnoreCase("pindah"))
                    kurakuraku.setPosition(new Dimension(Integer.parseInt(temp_split[1]), Integer.parseInt(temp_split[2])));
                else {
                    canvas.repaint(); 
                    return "Perintah tidak dipahami.";
                }
            }
        }

        canvas.repaint();    
        return "Perintah sudah dilaksanakan.";

    }
   
    /*Membuat bentuk kotak
     * menerima satu parameter berupa ukuran yang akan
     * mengatur seberapa panjang keempat sisi kotak ini
     */
    public void buatKotak(int ukuran ){        
        for (int i=0;i<4;i++){          // Looping untuk membuat gerakan berulang sehingga membentuk kotak
            kurakuraku.maju(ukuran);    // Maju sesuai ukuran yang diminta
            kurakuraku.rotasi(90);    // Melakukan rotasi ke kanan sebesar 90 derajat
        }
    }




    /*Membuat bentuk segitiga sama sisi
     * menerima satu parameter berupa ukuran yang akan
     * mengatur seberapa panjang ketiga sisi dari segitiga itu
     */
    public void buatSegitiga(int ukuran){
        for (int i=0;i<3;i++) {         // Looping untuk membuat gerakan berulang sehingga membentuk segitiga
            kurakuraku.maju(ukuran);    // Maju sesuai ukurang yang diminta
            kurakuraku.rotasi(-120);    // Melakukan rotasi ke kiri sebesar 120 derajat
        }

    }  
    
    /*Membuat segitiga siku-siku dan menerima 2 parameter
     * satu untuk tinggi, satu untuk panjang alas
     * Dilengkapi dengan perhitungan untuk menentukan sudut x dan y serta panjang sisi miring
     */
    public void buatSegitigaSikuSiku(int panjangAlas, int tinggi){
        double miring = Math.sqrt(Math.pow(panjangAlas, 2) + Math.pow(tinggi, 2));      //perhitungan untuk menghitung sisi miring

        double sudutAlasDanMiring = Math.toDegrees(Math.asin((double) tinggi / miring));    //perhitungan untuk menghitung sudut antara alas dan sisi miring
        
        kurakuraku.maju(panjangAlas);                   //turtle maju sesuai panjang alas
        kurakuraku.rotasi(-(180-sudutAlasDanMiring));   //melakukan rotasi untuk membuat sisi miringnya
        kurakuraku.maju(miring);                        //turtle maju sepanjang panjang sisi miring
        kurakuraku.rotasi(-(sudutAlasDanMiring + 90));  //melakukan rotasi untuk membuat sisi tingginya 
        kurakuraku.maju(tinggi);                        // turtle maju sesuai panjang tinggi
        kurakuraku.rotasi(-(90));                       //melakukan rotasi kekanan untuk kembali ke 
    }

    /*Membuat sebuah persegi panjang
     * menerima dua parameter, satu untuk panjang dan satu untuk lebar
     * Kedua parameter ini akan menentukan seberapa besar persegi panjang yang akan di buat
     */
    public void buatPersegi(int panjang, int lebar){
        kurakuraku.maju(panjang);   //Maju sesuai panjang yang diminta
        kurakuraku.rotasi(-90);     //Berotasi ke kiri sejauh 90 derajat
        kurakuraku.maju(lebar);     //Maju sesuai lebar yang diminta
        kurakuraku.rotasi(-90);     //Berotasi ke kiri sejauh 90 derajat
        kurakuraku.maju(panjang);   //Maju sesuai panjang yang diminta
        kurakuraku.rotasi(-90);     //Berotasi ke kiri sejauh 90 derajat
        kurakuraku.maju(lebar);     //Maju sesuai lebar yang diminta
        kurakuraku.rotasi(-90);     //Berotasi ke kiri sejauh 90 derajat
    }

    /*Tidak menerima parameter
     * Membuat bentuk pohon dengan ranting-ranting sebagai cabangnya
     */
    public void buatPohon(){        
        kurakuraku.setJejak(false);     //Set jejak di false agar tidak memberi coretan
        kurakuraku.reset();               //Mereset kembali si turtle
        kurakuraku.rotasi(90);          //Melakukan rotasi ke kanan sebesar 90 derajat
        kurakuraku.maju(100);       //Maju dengan jarak 100
        kurakuraku.rotasi(180);         //Melakukan rotasi sebesar 180 derajat
        buatPohon(6,50);    //Pohon melakukan pemisahan sebanyak 6 kali dan panjangnya awalnya adalah 50
        kurakuraku.reset();               //Mereset kembali si turtle
    }
    
    private void buatPohon(int ukuran, int tinggi){         //Menerima parameter ukuran dan tinggi
        if (ukuran>0){                                      //Base case condition untuk loop
            kurakuraku.setJejak(true);                    //Set jejak true untuk membuat coretan dari turtle
            kurakuraku.maju(tinggi);                        //Kura-kura maju sesuai tinggi yang diinput
            kurakuraku.rotasi(-45);                         //Kura-kura melakukan rotasi sebesar 45 derajat ke kiri
            Dimension posAwal = kurakuraku.getPosition();   //Menentukan posisi dari kura-kura
            double arah = kurakuraku.getArah();             //Menunjukan arah gerak dari kura-kura
            double sudut = arah;                            //Menyimpan arah di dalam varibel sudut
            for(int i=0;i<3;i++){                           //Melakukan for loop dan terdapat condition untuk loop itu
                buatPohon(ukuran-1,(int)(tinggi/1.5));      //Ukuran di -1 untuk loop sampai 0, tinggi dibagi 1.5 agar semakin kecil
                kurakuraku.setJejak(false);               //SetJejak false sehingga kura-kura tidak mencoret
                kurakuraku.setPosition(posAwal);            //Kura-kura kembali ke posisi awal
                kurakuraku.setArah(arah);                   //Untuk menentukan arah kura-kura
                
                // Tambah kotak di ujung
                if (ukuran == 1) {                          //Menjadi syarat untuk program berikutnya
                    kurakuraku.setJejak(true);            //SetJejak true sehingga kura-kura mencoret saat bergerak
                    buatKotak(10);                   //Memanggil function membuat kotak dengan ukuran 10
                    kurakuraku.setJejak(false);           //SetJejak false untuk menghindari coretan saat kura-kura bergerak
                } 

                sudut+=45;                                  //Menambahkan sudut 45 derajat agar garis terlihat
                kurakuraku.rotasi(sudut);                   //Kura-kura melakukan rotasi sesuai sudut
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
        if (in.length >= 3) {               // jika panjangnya adalah 3 atau lebih, nilai argumen_ada_dua adalah true
            argumen_ada_dua = true;

            // argumen kedua cek valid atau tidak (digit atau bukan)
            for (char c : in[2].toCharArray()) { // "50" -> ["5", "0"]
                if (!Character.isDigit(c)) {
                    argumen_type_valid = false; // kalau ada character yang bukan digit, buat argumen_type_valid jadi false (GA VALID)
                }
            } 
        }

        // Perintah-perintah yang membutuhkan 1 argumen
        if (in[0].equalsIgnoreCase("loop") || in[0].equalsIgnoreCase("maju") || in[0].equalsIgnoreCase("mundur") || in[0].equalsIgnoreCase("rotasi") || in[0].equalsIgnoreCase("kotak") || in[0].equalsIgnoreCase("segitiga") || in[0].equalsIgnoreCase("jejak")) {
            if (!argumen_ada_satu || !argumen_type_valid) {         //Jika kondisi ini tidak terpenuhi, akan mereturn false
                return false;
            }
        // Perintah-perintah yang membutuhkan 2 argumen
        } else if (in[0].equalsIgnoreCase("persegi") || in[0].equalsIgnoreCase("pindah") || in[0].equalsIgnoreCase("segitigasikusiku")) {
            if (!argumen_ada_dua || !argumen_type_valid) {         //Jika kondisi ini tidak terpenuhi, akan mereturn false
                return false;           
            }
        }

        // Perintah yang tidak membutuhkan argumen ATAU argumen sudah valid
        // Return True
        return true;
    }
    
}

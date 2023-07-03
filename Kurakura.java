import java.awt.Dimension;
import java.awt.Graphics2D; 
import java.awt.geom.AffineTransform; 
import java.awt.image.BufferedImage;


/**
 * 
 * Kurakura.java
 * 
 * Class {@code Kurakura} mendefinisikan object kura-kura.
 * Object kura-kura digerakkan oleh beberapa method antara lain:
 * maju, mundur dan rotasi. Object kura-kura digambarkan oleh method
 * {@code draw} yang akan dipanggil oleh object yang memiliki 
 * object kura-kura ini untuk digambarkan pada object tersebut.
 * Object kura-kura ini direpresentasikan oleh image kura-kura. 
 * Informasi mengenai nama file image terletak pada 
 * text file dengan nama {@code kurakuraku.properties}.
 * File ber extensi {@code .properties} dapat diakses langsung 
 * melalui library java tertentu.
 * <br>
 * <br>
 * Tugas anda pada file ini:<br>
 * - Lengkapi javadoc comment pada tiap deklarasi method. <br>
 * - Lengkapi method "mundur" yang belum ada implementasinya.<br> 
 * - Lengkapi dengan method lain yang dibutuhkan.<br>
 * 
 * @author Ade Azurat for DPBO 2008 @ Fasilkom UI
 * @author Ade Azurat for DDP2 2023 @ Fasilkom UI
 *
 */

public class Kurakura {
    
   private int x = 200, y = 100;            // default awal posisi kura-kura
   private int width = 400, height = 300;   // default ukuran layar/canvas
   private double arah = 0;                 // arah 0 -> sumbu x
   private boolean jejak = true;            // status membuat jejak atau tidak
   
   private String imageName = "turtle.gif"; // default file name object kurakura
   private java.awt.Image  img;             // object image kura-kura. 
   private BufferedImage imgJejak;          // object image untuk jejak kura-kura
   private AffineTransform matRotasi;       // mendefinisikan matriks rotasi
   private AffineTransform matTrans;        // mendefinisikan matriks translasi      
   private AffineTransform matGabung;       // mendefinisikan matriks gabungan 
   
    /** Creates a new instance of Kurakura */
    public Kurakura() {
      
      java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("Kurakuraku");      //Memberikan nama untuk kura-kura yang akan dipakai 
      imageName = bundle.getString("image");                                                        //Menambahkan gambar sebagai bentuk kura-kura
        
      img = java.awt.Toolkit.getDefaultToolkit().getImage(imageName);
      
      matRotasi = new AffineTransform();
      matGabung = new AffineTransform();
      matTrans = new AffineTransform();
      reset();       
    }

    /** Membuat object kurakura dengan ukuran lebar tinggi tertentu */
    public Kurakura(int w, int h){
        this();
        setSize(w,h);           //Menentukan ukuran dari kura-kura
        reset();                
    }
    
    public void setSize(int w, int h){        
        width = w;              //Menentukan w sebagai width
        height = h;             //menentukan h sebagai height
        imgJejak = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
    }
    
    /** Mengatur posisi kura-kura ke posisi awal */
    public void reset(){        
        x = width/2-15;         //Menentukan posisi x kura-kura saat dilakukan reset
        y = height/2-20;        //Menentukan posisi y kura-kura saat dilakuakn reset
        matTrans.setToTranslation(x, y);
    }
    
    public void rotasi(double d){        //Meneirma d sebagai parameter berapa derajat rotasi akan terjadi
        arah += Math.toRadians(d);      //Menambahkan nilai arah untuk gerakan rotasi kura-kura
        matRotasi.setToRotation(arah,img.getWidth(null)/2,img.getHeight(null)/2); // rotasi dihitung dari pusat image.              
    }
    
    public void maju(double jarak){         //Menerima jarak sebagai parameter berapa jauh kura-kura akan maju
        double dx,dy;
        dx = jarak * Math.cos(arah);        //Melakukan perhitungan untuk nilai dx dengan perhitungan cos
        dy = jarak * Math.sin(arah);        // Melakukan perhitungan untuk nilai dy dengan perhitungan sin
        
        if (jejak){
            Graphics2D g = imgJejak.createGraphics();
            g.draw(new java.awt.geom.Line2D.Double(x,y,x+dx,y+dy));
        }
        
        x += dx;                            //Nilai dx ditambahkan ke x
        y += dy;                            //Nilai dy ditambahkan ke y
        matTrans.setToTranslation(x,y);
    }
    
    public void mundur(double jarak){      // Menerima jarak sebagai parameter untuk menentukan berarpa jauh kura-kura akan mundur
        double dx,dy;
        dx = jarak * Math.cos(arah);       //Melakukan perhitungan untuk nilai dx dengan perhitungan cos
        dy = jarak * Math.sin(arah);       // Melakukan perhitungan untuk nilai dy dengan perhitungan sin
        
        if (jejak){
            Graphics2D g = imgJejak.createGraphics();
            g.draw(new java.awt.geom.Line2D.Double(x,y,x-dx,y+dy));
        }
        
        x -= dx;                        //Nilai x dikurangi dx
        y -= dy;                        //Nilai y dikurangi dy
        matTrans.setToTranslation(x,y);
    }
    
    /** 
     * Mengatur apakah gerakan kurakura akan memberikan 
     * jejak dilayar atau tidak bila bernilai {@code True}
     * maka akan terlihat jejak garis.
     * 
     * @param b sebuah boolean yang menentukan 
     *          akan memberikan jejak atau tidak  
     * 
     */
    public void setJejak(boolean v){
        jejak = v;
    }
    
    /** 
     * Menggambarkan kura-kura 
     * 
     * @param g object {@code Graphics2D} secara internal menggunakan library
     *          akan menggambarkan kurakura.
     */
    public void draw(Graphics2D g)
    {      
      matGabung.setToIdentity();     
      matGabung.concatenate(matTrans);            
      matGabung.concatenate(matRotasi);      
      if (imgJejak!=null)
          g.drawImage(imgJejak,img.getWidth(null)/2,img.getHeight(null)/2,null);
      if (img!=null)
          g.drawImage(img, matGabung, null);
    }
    
    /** 
     * Accessor method: 
     * Mengambil informasi posisi kurakura dilayar
     * 
     * @return informasi posisi kurakura saat ini dalam object {@code Dimension}
     *  
     */
    public Dimension getPosition(){ 
        return new Dimension(x,y);
    }

    /** 
     * Mutator method: 
     * men-set posisi kurakura 
     * 
     * @param informasi posisi kurakura dalam object {@code Dimension}
     *  
     */
    public void setPosition(Dimension pos){         //Untuk menentukan posisi dari Kura-kura
        x = (int) pos.getWidth();                   //Menentukan x dengan width 
        y = (int) pos.getHeight();                  //Menentukan y dengan height
        matTrans.setToTranslation(x, y);
    }
    
    public double getArah(){
        return arah;
    }
    
    public void setArah(double a){          //Menerima a sebagai parameter untuk setArah
        arah =a;                            //Nilai a disimpan pada variabel arah
        matRotasi.setToRotation(arah,img.getWidth(null)/2,img.getHeight(null)/2); // rotasi dihitung dari pusat image.              
    }
}

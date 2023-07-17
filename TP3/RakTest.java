import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RakTest {

    @Test
    public void testGetKategoriRak() {
        Rak rak = new Rak(5, "Kategori Rak");

        String expectedKategori = "Kategori Rak";
        String actualKategori = rak.getKategoriRak();

        assertEquals(expectedKategori, actualKategori);
    }

    @Test
    public void testTambahObat() {
        Rak rak = new Rak(5, "Kategori Rak");
        Obat obat = new Obat("Obat A", 10, "Kategori Obat");

        int index = 2;
        rak.tambahObat(obat, index);

        Obat[] daftarObat = rak.getListObat();
        Obat expectedObat = obat;
        Obat actualObat = daftarObat[index];

        assertEquals(expectedObat, actualObat);
    }

    @Test
    public void testPrintRak() {
        Rak rak = new Rak(5, "Kategori Rak");
        Obat obat1 = new Obat("Obat A", 5, "Kategori Obat");
        Obat obat2 = new Obat("Obat B", 0, "Kategori Obat");

        int index1 = 1;
        int index2 = 3;
        rak.tambahObat(obat1, index1);
        rak.tambahObat(obat2, index2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        rak.printRak();

        String expectedOutput = "| Kosong | Obat A (stok: 5) | Kosong | Obat B (stok: 0) | Kosong \n";
        String actualOutput = outputStream.toString();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testGetListObat() {
        Rak rak = new Rak(5, "Kategori Rak");
        Obat obat1 = new Obat("Obat A", 5, "Kategori Obat");
        Obat obat2 = new Obat("Obat B", 10, "Kategori Obat");

        int index1 = 1;
        int index2 = 3;
        rak.tambahObat(obat1, index1);
        rak.tambahObat(obat2, index2);

        Obat[] expectedListObat = new Obat[] { null, obat1, null, obat2, null };
        Obat[] actualListObat = rak.getListObat();

        assertArrayEquals(expectedListObat, actualListObat);
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LemariTest {

    private Lemari lemari;

    @BeforeEach
    public void setUp() {
        lemari = new Lemari(2);

        Rak rak1 = new Rak(3, "Rak 1");
        Obat obat1 = new Obat("Obat A", 5, "Kategori Obat");
        rak1.tambahObat(obat1, 0);

        Rak rak2 = new Rak(2, "Rak 2");
        Obat obat2 = new Obat("Obat B", 10, "Kategori Obat");
        Obat obat3 = new Obat("Obat C", 8, "Kategori Obat");
        rak2.tambahObat(obat2, 0);
        rak2.tambahObat(obat3, 1);

        lemari.addRak(rak1, 0);
        lemari.addRak(rak2, 1);
    }

    @Test
    public void testBeliObatSuccess() {
        Obat obat = new Obat("Obat B", 10, "Kategori Obat");

        assertTrue(lemari.beliObat(obat, 5));
        assertEquals(5, obat.getStok());
    }

    @Test
    public void testBeliObatInsufficientStock() {
        Obat obat = new Obat("Obat C", 8, "Kategori Obat");

        assertFalse(lemari.beliObat(obat, 10));
        assertEquals(8, obat.getStok());
    }

    @Test
    public void testBeliObatNotFound() {
        Obat obat = new Obat("Obat D", 10, "Kategori Obat");

        assertFalse(lemari.beliObat(obat, 5));
        assertEquals(10, obat.getStok());
    }

    @Test
    public void testSearchObat() {
        Obat obat = lemari.searchObat("Obat A");

        assertNotNull(obat);
        assertEquals("Obat A", obat.getNama());
    }

    @Test
    public void testSearchObatNotFound() {
        Obat obat = lemari.searchObat("Obat D");

        assertNull(obat);
    }

    @Test
    public void testGetRak() {
        Rak rak = lemari.getRak(0);

        assertNotNull(rak);
        assertEquals("Rak 1", rak.getKategoriRak());
    }
}

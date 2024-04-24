package id.ac.ui.cs.advprog.produktransaksiservice.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
public class ProdukTest {
    Produk produk1;
    Produk produk2;
    @BeforeEach
    void setup() {
        this.produk1 = new Produk();
        this.produk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        this.produk1.setNama("Red Dead Redemption 2");
        this.produk1.setKategori("Open World");
        this.produk1.setHarga(350000);
        this.produk1.setDeskripsi("Prequel to RDR 1");
        this.produk1.setStokTersedia(100);
        this.produk1.setStokTerjual(10);
        this.produk1.setPenjual("Rockstar Store");

        this.produk2 = new Produk();

    }

    @Test
    void testGetProdukId() {
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454", this.produk1.getProdukId());
    }

    @Test
    void testGetNama() {
        assertEquals("Red Dead Redemption 2", this.produk1.getNama());
    }

    @Test
    void testGetKategori() {
        assertEquals("Open World", this.produk1.getKategori());
    }

    @Test
    void testGetHarga() {
        assertEquals(350000, this.produk1.getHarga());
    }

    @Test
    void testGetDeskripsi() {
        assertEquals("Prequel to RDR 1", this.produk1.getDeskripsi());
    }

    @Test
    void testGetStokTersedia() {
        assertEquals(100, this.produk1.getStokTersedia());
    }

    @Test
    void testGetStokTerjual() {
        assertEquals(10, this.produk1.getStokTerjual());
    }

    @Test
    void testGetPenjual() {
        assertEquals("Rockstar Store", this.produk1.getPenjual());
    }

    @Test
    void testProdukWithInvalidStok() {
        assertThrows(IllegalArgumentException.class, () -> this.produk2.setStokTersedia(-100));
    }
}
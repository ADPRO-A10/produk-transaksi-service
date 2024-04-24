package id.ac.ui.cs.advprog.produktransaksiservice.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
public class ProductTest {
    Produk produk1;
    @BeforeEach
    void setup() {
        this.produk1 = new Produk();
        this.produk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        this.produk1.setNama("Red Dead Redemption 2");
        this.produk1.setKategori("Open World");
        this.produk1.setHarga(350000);
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
}
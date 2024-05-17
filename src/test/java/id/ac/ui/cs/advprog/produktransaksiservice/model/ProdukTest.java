package id.ac.ui.cs.advprog.produktransaksiservice.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdukTest {
    Produk produk1;
    Produk produk2;
    @BeforeEach
    void setup() {
        ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();
        this.produk1 = produkBuilder
                .id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .harga(350000)
                .kategori("Open World")
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

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

    @Test
    void testSetProdukId() {
        this.produk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63a5w9281");
        assertEquals("f8c3de3d-1fea-4d7c-a8b0-29f63a5w9281", this.produk1.getProdukId());
    }

    @Test
    void testSetNama() {
        this.produk1.setNama("Red Dead Redemption 2 Update");
        assertEquals("Red Dead Redemption 2 Update", this.produk1.getNama());
    }

    @Test
    void testSetKategori() {
        this.produk1.setKategori("Shooting");
        assertEquals("Shooting", this.produk1.getKategori());
    }

    @Test
    void testSetDeskripsi() {
        this.produk1.setDeskripsi("This is an updated version of RDR 2");
        assertEquals("This is an updated version of RDR 2", this.produk1.getDeskripsi());
    }

    @Test
    void testSetHarga() {
        this.produk1.setHarga(300000);
        assertEquals(300000, this.produk1.getHarga());
    }

    @Test
    void testStokTersedia() {
        this.produk1.setStokTersedia(200);
        assertEquals(200, this.produk1.getStokTersedia());
    }

    @Test
    void testStokTerjual() {
        this.produk1.setStokTerjual(85);
        assertEquals(85, this.produk1.getStokTerjual());
    }

    @Test
    void testPenjual() {
        this.produk1.setPenjual("Rockstar RDR Store");
        assertEquals("Rockstar RDR Store", this.produk1.getPenjual());
    }
}
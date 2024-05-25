package id.ac.ui.cs.advprog.produktransaksiservice.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProdukTest {
    Produk produk1;
    Produk produk2;
    ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();

    @BeforeEach
    void setup() {
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
        ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();
        assertThrows(IllegalArgumentException.class, () -> {
            produkBuilder
                    .id("1")
                    .nama("Test Product")
                    .harga(1000)
                    .kategori("Test Category")
                    .deskripsi("Test Description")
                    .stokTersedia(-10)
                    .penjual("Test Seller")
                    .build();
        });
    }

    @Test
    void testProdukWithInvalidStokMessage() {
        ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            produkBuilder
                    .id("1")
                    .nama("Test Product")
                    .harga(1000)
                    .kategori("Test Category")
                    .deskripsi("Test Description")
                    .stokTersedia(-10)
                    .penjual("Test Seller")
                    .build();
        });
        assertEquals("Stok yang diinput tidak valid", exception.getMessage());
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

    @Test
    void testGetReviews() {
        Review review1 = new Review(produk1, "Reviewer1", "Great game!", 5);
        Review review2 = new Review(produk1, "Reviewer2", "Not bad.", 3);
        produk1.getReviews().add(review1);
        produk1.getReviews().add(review2);
        assertEquals(2, produk1.getReviews().size());
        assertTrue(produk1.getReviews().contains(review1));
        assertTrue(produk1.getReviews().contains(review2));
    }

    @Test
    void testSetReviews() {
        Review review1 = new Review(produk1, "Reviewer1", "Great game!", 5);
        Review review2 = new Review(produk1, "Reviewer2", "Not bad", 3);
        produk1.getReviews().add(review1);
        produk1.getReviews().add(review2);

        produk1.setReviews( new ArrayList<>());

        assertEquals(0, produk1.getReviews().size());
    }

    @Test
    void setStokInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.produk1.setStokTersedia(-1);
        });
    }
}
package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class ProdukRepositoryTest {

    @Autowired
    ProdukRepository produkRepository;

    ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();

    @BeforeEach
    void setup() {
    }

    @Test
    void testCreateProduk() {

        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkRepository.save(tesProduk1);

        List<Produk> produkList = produkRepository.findAll();
        assertFalse(produkList.isEmpty());
    }

    @Test
    void testFindProdukById() {
        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkRepository.save(tesProduk1);

        Produk findedProduk = produkRepository.findById(tesProduk1.getProdukId()).orElse(null);

        assertEquals(tesProduk1.getProdukId(), findedProduk.getProdukId());
        assertEquals(tesProduk1.getNama(), findedProduk.getNama());
        assertEquals(tesProduk1.getHarga(), findedProduk.getHarga());
        assertEquals(tesProduk1.getDeskripsi(), findedProduk.getDeskripsi());
        assertEquals(tesProduk1.getKategori(), findedProduk.getKategori());
        assertEquals(tesProduk1.getStokTerjual(), findedProduk.getStokTerjual());
        assertEquals(tesProduk1.getStokTersedia(), findedProduk.getStokTersedia());
        assertEquals(tesProduk1.getPenjual(), findedProduk.getPenjual());
    }

    @Test
    void testFindAllIfEmpty() {
        List<Produk> produkList = produkRepository.findAll();
        assertTrue(produkList.isEmpty());
    }

    @Test
    void testDeleteProduk() {
        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkRepository.save(tesProduk1);
        List<Produk> produkList = produkRepository.findAll();
        assertFalse(produkList.isEmpty());

        Produk deletedProduk = produkRepository.findById(tesProduk1.getProdukId()).orElse(null);

        produkRepository.delete(deletedProduk);
        produkList = produkRepository.findAll();
        assertTrue(produkList.isEmpty());
    }

    @Test
    void testEditProduk() {
        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkRepository.save(tesProduk1);

        Produk tesProduk2 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(10000)
                .deskripsi("Testing Desc")
                .stokTersedia(500)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

        tesProduk2.setProdukId(produkRepository.findById(tesProduk1.getProdukId()).orElse(null).getProdukId());
        produkRepository.save(tesProduk2);

        Produk findedProduk = produkRepository.findById(tesProduk2.getProdukId()).orElse(null);

        assertEquals(tesProduk2.getProdukId(), findedProduk.getProdukId());
        assertEquals(tesProduk2.getNama(), findedProduk.getNama());
        assertEquals(tesProduk2.getHarga(), findedProduk.getHarga());
        assertEquals(tesProduk2.getDeskripsi(), findedProduk.getDeskripsi());
        assertEquals(tesProduk2.getKategori(), findedProduk.getKategori());
        assertEquals(tesProduk2.getStokTerjual(), findedProduk.getStokTerjual());
        assertEquals(tesProduk2.getStokTersedia(), findedProduk.getStokTersedia());
        assertEquals(tesProduk2.getPenjual(), findedProduk.getPenjual());

    }
}

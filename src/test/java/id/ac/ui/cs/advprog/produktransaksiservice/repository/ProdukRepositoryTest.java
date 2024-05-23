package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
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

    @BeforeEach
    void setup() {
    }

    @Test
    void testCreateProduk() {
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
        produkRepository.save(tesProduk1);

        List<Produk> produkList = produkRepository.findAll();
        assertFalse(produkList.isEmpty());
    }

    @Test
    void testFindProdukById() {
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
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
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
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
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
        produkRepository.save(tesProduk1);

        Produk tesProduk2 = new Produk();
        tesProduk2.setNama("Red Dead Redemption 2");
        tesProduk2.setKategori("Open World");
        tesProduk2.setHarga(10000);
        tesProduk2.setStokTersedia(500);
        tesProduk2.setDeskripsi("Testing Desc");
        tesProduk2.setStokTerjual(10);
        tesProduk2.setPenjual("Rockstar Store");

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

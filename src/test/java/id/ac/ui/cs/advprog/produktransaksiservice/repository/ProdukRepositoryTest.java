package id.ac.ui.cs.advprog.produktransaksiservice.repository;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProdukRepositoryTest {
    @InjectMocks
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
        produkRepository.create(tesProduk1);

        Iterator<Produk> produkIterator = produkRepository.findAll();
        assertTrue(produkIterator.hasNext());
    }

    //Unhappy Test
    @Test
    void createProduckWithSameName() {
        Produk product1 = new Produk();
        product1.setProdukId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setNama("Overwatch");
        product1.setHarga(50000);
        produkRepository.create(product1);

        Produk product2 = new Produk();
        product2.setProdukId("ef658e4x-2s69-510z-8862-72bq6afx65453ww");
        product2.setNama("Overwatch");
        product2.setHarga(25000);
        assertThrows(IllegalArgumentException.class, () -> produkRepository.create(product2));
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
        produkRepository.create(tesProduk1);

        Produk savedProduct = produkRepository.findByProductId(tesProduk1.getProdukId());
        assertEquals(tesProduk1.getProdukId(), savedProduct.getProdukId());
        assertEquals(tesProduk1.getNama(), savedProduct.getNama());
        assertEquals(tesProduk1.getHarga(), savedProduct.getHarga());
        assertEquals(tesProduk1.getDeskripsi(), savedProduct.getDeskripsi());
        assertEquals(tesProduk1.getKategori(), savedProduct.getKategori());
        assertEquals(tesProduk1.getStokTerjual(), savedProduct.getStokTerjual());
        assertEquals(tesProduk1.getStokTersedia(), savedProduct.getStokTersedia());
        assertEquals(tesProduk1.getPenjual(), savedProduct.getPenjual());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Produk> productIterator = produkRepository.findAll();
        assertFalse(productIterator.hasNext());
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
        produkRepository.create(tesProduk1);
        Iterator<Produk> productIterator = produkRepository.findAll();

        produkRepository.deleteProduct(tesProduk1.getProdukId());
        assertFalse(productIterator.hasNext());
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
        produkRepository.create(tesProduk1);

        Produk tesProduk2 = new Produk();
        tesProduk2.setHarga(10000);
        tesProduk2.setStokTersedia(500);
        tesProduk2.setDeskripsi("Testing Desc");

        produkRepository.editProduct(tesProduk1.getProdukId(), tesProduk2);
        assertEquals(tesProduk2.getHarga(), tesProduk1.getHarga());
        assertEquals(tesProduk2.getDeskripsi(), tesProduk1.getDeskripsi());
        assertEquals(tesProduk2.getStokTersedia(), tesProduk1.getStokTersedia());
    }
}

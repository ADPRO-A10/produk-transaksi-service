package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdukServiceImplTest {

    @Mock
    private ProdukRepository produkRepository;

    @InjectMocks
    private ProdukServiceImpl produkServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProdukById() {
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
        produkServiceImpl.addProduk(tesProduk1);

        when(produkRepository.findById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(Optional.of(tesProduk1));
        Produk findProduk = produkServiceImpl.getProdukById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");


        assertNotNull(findProduk);
        assertEquals("Red Dead Redemption 2", findProduk.getNama());
        assertEquals("Prequel to RDR 1", findProduk.getDeskripsi());
        assertEquals("Open World", findProduk.getKategori());
        assertEquals(350000, findProduk.getHarga());
        assertEquals(100, findProduk.getStokTersedia());
        assertEquals(10, findProduk.getStokTerjual());
        assertEquals("Rockstar Store", findProduk.getPenjual());

    }

    @Test
    void testGetProdukByIdNotFound() {
        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        Produk result = produkServiceImpl.getProdukById("123");
        assertNull(result);
    }

    @Test
    void testEditProduk() {
        Produk produk = new Produk();
        produk.setProdukId("123");
        produk.setNama("Product A");
        produk.setHarga(100);
        produk.setKategori("Category A");
        produk.setDeskripsi("Description");
        produk.setStokTersedia(10);
        produk.setStokTerjual(0);
        produk.setPenjual("Seller A");

        Produk updatedProduk = new Produk();
        updatedProduk.setNama("Product B");
        updatedProduk.setHarga(150);
        updatedProduk.setKategori("Category B");
        updatedProduk.setDeskripsi("New Description");
        updatedProduk.setStokTersedia(20);
        updatedProduk.setStokTerjual(5);
        updatedProduk.setPenjual("Seller B");

        when(produkRepository.findById("123")).thenReturn(Optional.of(produk));
        when(produkRepository.save(any(Produk.class))).thenReturn(updatedProduk);

        Produk result = produkServiceImpl.editProduk("123", updatedProduk);

        assertNotNull(result);
        assertEquals("Product B", result.getNama());
        assertEquals(150, result.getHarga());
        assertEquals("Category B", result.getKategori());
        assertEquals("New Description", result.getDeskripsi());
        assertEquals(20, result.getStokTersedia());
        assertEquals(5, result.getStokTerjual());
        assertEquals("Seller B", result.getPenjual());

    }

    @Test
    void testEditProduk_NotFound() {
        Produk updatedProduk = new Produk();
        updatedProduk.setNama("Product B");

        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        Produk result = produkServiceImpl.editProduk("123", updatedProduk);

        assertNull(result);
    }

    @Test
    void testAddProdukWithSameName() {
        Produk tesProduk1 = new Produk();
        tesProduk1.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        tesProduk1.setNama("Red Dead Redemption 2");
        tesProduk1.setKategori("Open World");
        tesProduk1.setHarga(350000);
        tesProduk1.setDeskripsi("Prequel to RDR 1");
        tesProduk1.setStokTersedia(100);
        tesProduk1.setStokTerjual(10);
        tesProduk1.setPenjual("Rockstar Store");
        produkServiceImpl.addProduk(tesProduk1);

        Produk tesProduk2 = new Produk();
        tesProduk2.setProdukId("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455");
        tesProduk2.setNama("Red Dead Redemption 2");
        tesProduk2.setKategori("Open World");
        tesProduk2.setHarga(350000);
        tesProduk2.setDeskripsi("Prequel to RDR 1");
        tesProduk2.setStokTersedia(100);
        tesProduk2.setStokTerjual(10);
        tesProduk2.setPenjual("Rockstar Store");

        when(produkRepository.findByNama("Red Dead Redemption 2")).thenReturn(Optional.of(tesProduk1));

        assertThrows(RuntimeException.class, () -> produkServiceImpl.addProduk(tesProduk2));
    }
}

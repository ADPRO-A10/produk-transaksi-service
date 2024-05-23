package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
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

    ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProdukById() {
        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
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
        Produk produk = produkBuilder
                .id("123")
                .nama("Product A")
                .harga(100)
                .kategori("Category A")
                .deskripsi("Description")
                .stokTersedia(10)
                .stokTerjual(0)
                .penjual("Seller A")
                .build();
        produkServiceImpl.addProduk(produk);
        when(produkRepository.findById("123")).thenReturn(Optional.of(produk));
        assertNotNull(produkServiceImpl.getProdukById("123"));

        Produk updatedProduk = produkBuilder
                        .nama("Product B")
                        .harga(150)
                        .kategori("Category B")
                        .deskripsi("New Description")
                        .stokTersedia(20)
                        .stokTerjual(5)
                        .penjual("Seller A")
                        .build();


        produkServiceImpl.editProduk("123", updatedProduk);


        assertEquals("Product B", produk.getNama());
        assertEquals(150, produk.getHarga());
        assertEquals("Category B", produk.getKategori());
        assertEquals("New Description", produk.getDeskripsi());
        assertEquals(20, produk.getStokTersedia());
        assertEquals(5, produk.getStokTerjual());
        assertEquals("Seller A", produk.getPenjual());

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
        Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkServiceImpl.addProduk(tesProduk1);

        Produk tesProduk2 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

        when(produkRepository.findByNama("Red Dead Redemption 2")).thenReturn(Optional.of(tesProduk1));
        when(produkRepository.findByNama("Red Dead Redemption 2")).thenReturn(Optional.of(tesProduk1));

        assertThrows(RuntimeException.class, () -> produkServiceImpl.addProduk(tesProduk2));
    }
}

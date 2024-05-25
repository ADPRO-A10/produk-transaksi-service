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

import java.util.List;
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

    Produk tesProduk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
            .nama("Red Dead Redemption 2")
            .kategori("Open World")
            .harga(350000)
            .deskripsi("Prequel to RDR 1")
            .stokTersedia(100)
            .stokTerjual(10)
            .penjual("Rockstar Store")
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProdukById() {
        produkServiceImpl.createProduk(tesProduk1);

        when(produkRepository.findById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(Optional.of(tesProduk1));
        Produk findProduk = produkServiceImpl.findProdukById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");


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
        Produk result = produkServiceImpl.findProdukById("123");
        assertNull(result);
    }

    @Test
    void testGetProdukByNama() {
        produkServiceImpl.createProduk(tesProduk1);

        when(produkRepository.findByNama("Red Dead Redemption 2")).thenReturn(Optional.of(tesProduk1));
        Produk findProduk = produkServiceImpl.findProdukByNama("Red Dead Redemption 2");

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
        produkServiceImpl.createProduk(produk);
        when(produkRepository.findById("123")).thenReturn(Optional.of(produk));
        assertNotNull(produkServiceImpl.findProdukById("123"));

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
    void testEditProdukNotFound() {
        Produk updatedProduk = new Produk();
        updatedProduk.setNama("Product B");

        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produkServiceImpl.editProduk("123", updatedProduk);
        });
        assertEquals("Produk tidak ditemukan", exception.getMessage());
    }

    @Test
    void testAddProdukWithSameName() {
        produkServiceImpl.createProduk(tesProduk1);

        Produk tesProduk2 = produkBuilder.
                id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
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

        assertThrows(RuntimeException.class, () -> produkServiceImpl.createProduk(tesProduk2));
    }

    @Test
    void testFindAll() {
        produkServiceImpl.createProduk(tesProduk1);

        Produk tesProduk2 = produkBuilder.
                id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        produkServiceImpl.createProduk(tesProduk2);

        when(produkRepository.findAll()).thenReturn(List.of(tesProduk1, tesProduk2));
        assertEquals(2, produkServiceImpl.findAll().size());
        assertEquals(tesProduk1.getProdukId(), produkServiceImpl.findAll().getFirst().getProdukId());
        assertEquals(tesProduk2.getProdukId(), produkServiceImpl.findAll().getLast().getProdukId());
        assertEquals(tesProduk1.getNama(), produkServiceImpl.findAll().getFirst().getNama());
        assertEquals(tesProduk2.getNama(), produkServiceImpl.findAll().getLast().getNama());
        assertEquals(tesProduk1.getKategori(), produkServiceImpl.findAll().getFirst().getKategori());
        assertEquals(tesProduk2.getKategori(), produkServiceImpl.findAll().getLast().getKategori());
        assertEquals(tesProduk1.getHarga(), produkServiceImpl.findAll().getFirst().getHarga());
        assertEquals(tesProduk2.getHarga(), produkServiceImpl.findAll().getLast().getHarga());
        assertEquals(tesProduk1.getDeskripsi(), produkServiceImpl.findAll().getFirst().getDeskripsi());
        assertEquals(tesProduk2.getDeskripsi(), produkServiceImpl.findAll().getLast().getDeskripsi());
        assertEquals(tesProduk1.getStokTersedia(), produkServiceImpl.findAll().getFirst().getStokTersedia());
        assertEquals(tesProduk2.getStokTersedia(), produkServiceImpl.findAll().getLast().getStokTersedia());
        assertEquals(tesProduk1.getStokTerjual(), produkServiceImpl.findAll().getFirst().getStokTerjual());
        assertEquals(tesProduk2.getStokTerjual(), produkServiceImpl.findAll().getLast().getStokTerjual());
        assertEquals(tesProduk1.getPenjual(), produkServiceImpl.findAll().getFirst().getPenjual());
        assertEquals(tesProduk2.getPenjual(), produkServiceImpl.findAll().getLast().getPenjual());
    }

    @Test
    void testDeleteprodukSuccess() {
        produkServiceImpl.createProduk(tesProduk1);

        when(produkRepository.findById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(Optional.of(tesProduk1));
        assertDoesNotThrow(() -> {
            produkServiceImpl.deleteProduk("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        });
    }

    @Test
    void testDeleteProductFailed() {
        when(produkRepository.findById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produkServiceImpl.deleteProduk("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
        });
        assertEquals("Produk tidak ditemukan", exception.getMessage());
    }
}

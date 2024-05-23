package id.ac.ui.cs.advprog.produktransaksiservice.controller;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.ProdukDirector;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.ProdukRepository;
import id.ac.ui.cs.advprog.produktransaksiservice.service.ProdukServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdukControllerTest {

    @Mock
    private ProdukRepository produkRepository;

    @InjectMocks
    private ProdukServiceImpl produkServiceImpl;

    @InjectMocks
    private ProdukController produkController;

    ProdukDirector.ProdukBuilder produkBuilder = new ProdukDirector.ProdukBuilder();

    @BeforeEach
    void setup() {

    }

    @Test
    void testCreateProduk() {
        Produk produk = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();
        when(produkRepository.save(produk)).thenReturn(produk);

        ResponseEntity<Produk> response = produkController.createProduk(produk);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produk, response.getBody());
    }

    @Test
    void testGetAllProduk() {
        Produk produk1 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

        produkController.createProduk(produk1);

        Produk produk2 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3455")
                .nama("GTA V")
                .kategori("Open World")
                .harga(300000)
                .deskripsi("Game about crime")
                .stokTersedia(200)
                .stokTerjual(20)
                .penjual("Rockstar Store")
                .build();

        produkController.createProduk(produk2);

        List<Produk> produkList = new ArrayList<>();
        produkList.add(produk1);
        produkList.add(produk2);

        when(produkRepository.findAll()).thenReturn(produkList);

        ResponseEntity<List<Produk>> response = produkController.listProduk();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produkList, response.getBody());
    }

    @Test
    void testGetProdukById() {
        Produk produk = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

        produkController.createProduk(produk);
        when(produkRepository.findById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(Optional.of(produk));

        ResponseEntity<Produk> response = produkController.getProdukById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produk, response.getBody());
    }

    @Test
    void testGetProdukByIdNotFound() {
        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        ResponseEntity<Produk> response = produkController.getProdukById("123");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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
        produkController.createProduk(produk);
        when(produkRepository.findById("123")).thenReturn(Optional.of(produk));
        assertNotNull(produkController.getProdukById("123"));

        Produk updatedProduk = produkBuilder
                        .nama("Product B")
                        .harga(150)
                        .kategori("Category B")
                        .deskripsi("New Description")
                        .stokTersedia(20)
                        .stokTerjual(5)
                        .penjual("Seller B")
                        .build();
        when(produkRepository.save(updatedProduk)).thenReturn(updatedProduk);

        ResponseEntity<Produk> response = produkController.editProduk("123", updatedProduk);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduk, response.getBody());
    }

    @Test
    void testEditProdukNotFound() {
        Produk updatedProduk = produkBuilder
                        .nama("Product B")
                        .harga(150)
                        .kategori("Category B")
                        .deskripsi("New Description")
                        .stokTersedia(20)
                        .stokTerjual(5)
                        .penjual("Seller B")
                        .build();
        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        ResponseEntity<Produk> response = produkController.editProduk("123", updatedProduk);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteProduk() {
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
        produkController.createProduk(produk);
        when(produkRepository.findById("123")).thenReturn(Optional.of(produk));
        assertNotNull(produkController.getProdukById("123"));

        ResponseEntity<Produk> response = produkController.deleteProduk("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteProdukNotFound() {
        when(produkRepository.findById("123")).thenReturn(Optional.empty());
        ResponseEntity<Produk> response = produkController.deleteProduk("123");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

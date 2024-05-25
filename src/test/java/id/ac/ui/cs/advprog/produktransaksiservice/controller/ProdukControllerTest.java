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
import org.mockito.MockitoAnnotations;
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

        when(produkServiceImpl.createProduk(produk)).thenReturn(produk);
        ResponseEntity<Produk> response = produkController.createProduk(produk);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(produk.getProdukId(), response.getBody().getProdukId());
    }
    @Test
    void testCreateProdukWithSameName() {
        Produk produk = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();


        Produk produk2 = produkBuilder.id("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .nama("Red Dead Redemption 2")
                .kategori("Open World")
                .harga(350000)
                .deskripsi("Prequel to RDR 1")
                .stokTersedia(100)
                .stokTerjual(10)
                .penjual("Rockstar Store")
                .build();

        when(produkServiceImpl.createProduk(produk)).thenReturn(produk);
        ResponseEntity<Produk> response1 = produkController.createProduk(produk);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(produk, response1.getBody());

        when(produkServiceImpl.createProduk(produk2)).thenThrow(new RuntimeException());
        ResponseEntity<Produk> response = produkController.createProduk(produk2);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
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

        when(produkServiceImpl.findAll()).thenReturn(produkList);

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
        when(produkServiceImpl.findProdukById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")).thenReturn(produk);

        ResponseEntity<Produk> response = produkController.getProdukById("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(produk, response.getBody());
    }

    @Test
    void testGetProdukByIdNotFound() {
        when(produkServiceImpl.findProdukById("123")).thenReturn(null);
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

        when(produkServiceImpl.createProduk(produk)).thenReturn(produk);
        ResponseEntity<Produk> response = produkController.createProduk(produk);

        Produk updatedProduk = produkBuilder
                        .nama("Product B")
                        .harga(150)
                        .kategori("Category B")
                        .deskripsi("New Description")
                        .stokTersedia(20)
                        .stokTerjual(5)
                        .penjual("Seller B")
                        .build();

        when(produkServiceImpl.findProdukById("123")).thenReturn(produk);
        when(produkServiceImpl.editProduk("123", updatedProduk)).thenReturn(produk);
        ResponseEntity<Produk> response2 = produkController.editProduk("123", updatedProduk);

        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals(produk, response2.getBody());
    }

    @Test
    void testEditProdukNotFound() {
        Produk updatedProduk = new Produk();
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
        when(produkServiceImpl.findProdukById("123")).thenReturn(produk);
        assertNotNull(produkController.getProdukById("123"));

        ResponseEntity<Produk> response = produkController.deleteProduk("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteProdukNotFound() {
        when(produkServiceImpl.findProdukById("123")).thenReturn(null);
        ResponseEntity<Produk> response = produkController.deleteProduk("123");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

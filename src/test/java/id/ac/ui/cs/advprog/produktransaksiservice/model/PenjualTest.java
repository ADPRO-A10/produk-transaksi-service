package id.ac.ui.cs.advprog.produktransaksiservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class PenjualTest {

    private List<Produk> listProduk;

    @BeforeEach
    void setUp() {
        this.listProduk = new ArrayList<>();

        Produk produk1 = new Produk();
        produk1.setProdukId("123456");
        produk1.setNama("meong");
        produk1.setHarga(3000);
        produk1.setKategori("casual");
        produk1.setDeskripsi("untuk kucing dari kucing");
        produk1.setStokTersedia(1);
        produk1.setStokTerjual(1);
        produk1.setPenjual("ngeong");

        Produk produk2 = new Produk();
        produk2.setProdukId("234567");
        produk2.setNama("guk");
        produk2.setHarga(3000);
        produk2.setKategori("action");
        produk2.setDeskripsi("gukguk");
        produk2.setStokTersedia(1);
        produk2.setStokTerjual(1);
        produk2.setPenjual("anjin");

        this.listProduk.add(produk1);
        this.listProduk.add(produk2);
    }

    @Test
    void testEmptyRiwayatPenjual() {
        Penjual penjual = new Penjual();
        penjual.setUsername("ngeong");
        penjual.setKatalog(listProduk);
        penjual.setRiwayatTransaksi(null);

        assertNotNull(penjual);
    }

    @Test
    void testEmptyKatalogPenjual() {
        List<Transaksi> listTransaksi = new ArrayList<>();
        Transaksi transaksi1 = new Transaksi.Builder()
                .transaksiId(UUID.randomUUID())
                .listProduk(listProduk)
                .totalHarga(Long.valueOf(123))
                .statusPembayaran("selesai")
                .tanggalTransaksi(LocalDate.now())
                .build();
        listTransaksi.add(transaksi1);

        Penjual penjual = new Penjual();
        penjual.setUsername("ngeong");
        penjual.setKatalog(null);
        penjual.setRiwayatTransaksi(listTransaksi);
        assertNotNull(penjual);
    }

    @Test
    void testEmptyKatalogRiwayatPenjual() {
        Penjual penjual = new Penjual();
        penjual.setUsername("ngeong");
        penjual.setKatalog(null);
        penjual.setRiwayatTransaksi(null);
        assertNotNull(penjual);
    }
}
package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransaksiServiceImplTest {
    @InjectMocks
    TransaksiServiceImpl transaksiService;
    @Mock
    TransaksiRepository transaksiRepository;
    List<Transaksi> listTransaksi;
    List<Produk> listProduk;
    List<Penjual> listPenjual;

    @BeforeEach
    void setUp() {
        listProduk = new ArrayList<>(); // Initialize listProduk
        listPenjual = new ArrayList<>(); // Initialize listPenjual

        Produk produk1 = new Produk();
        produk1.setProdukId("123456");
        produk1.setNama("meong");
        produk1.setHarga(3000);
        produk1.setKategori("casual");
        produk1.setDeskripsi("untuk kucing dari kucing");
        produk1.setStokTersedia(1);
        produk1.setStokTerjual(1);
        produk1.setPenjual("ngeong");
        listProduk.add(produk1);
        listTransaksi = new ArrayList<>();
        Transaksi transaksi1 = new Transaksi.Builder()
                .transaksiId(UUID.randomUUID())
                .listProduk(listProduk)
                .totalHarga(Long.valueOf(123))
                .statusPembayaran("selesai")
                .tanggalTransaksi(LocalDate.now())
                .build();
        listTransaksi.add(transaksi1);
        Transaksi transaksi2 = new Transaksi.Builder()
                .transaksiId(UUID.randomUUID())
                .listProduk(listProduk)
                .totalHarga(Long.valueOf(345))
                .statusPembayaran("selesai")
                .tanggalTransaksi(LocalDate.now())
                .build();
        listTransaksi.add(transaksi1);
        listTransaksi.add(transaksi2);

        Penjual penjual = new Penjual();
        penjual.setUsername("ngeong");
        penjual.setKatalog(listProduk);
        penjual.setRiwayatTransaksi(new ArrayList<>());
        listPenjual.add(penjual);
    }
    
    @Test
    void testCreateTransaksi() {
        Pembeli pembeli = new Pembeli();
        pembeli.setUsername("guguk");
        pembeli.setBalance(5000);
        pembeli.setLibrary(new ArrayList<>());
        pembeli.setRiwayatTransaksi(new ArrayList<>());

        transaksiService.processTransaksi(pembeli, listPenjual, listProduk);
    }
}
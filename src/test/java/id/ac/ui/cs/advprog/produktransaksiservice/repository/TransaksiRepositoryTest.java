package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransaksiRepositoryTest {

    @Mock
    TransaksiRepository transaksiRepository;

    List<Transaksi> listTransaksi;

    @BeforeEach
    void setUp() {
        List<Produk> listProduk = new ArrayList<>();
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
        listTransaksi.add(transaksi2);
    }

    @Test
    void testFindId() {
        assertNotNull(transaksiRepository);
        transaksiRepository.findById(listTransaksi.get(0).getTransaksiId());
    }
}

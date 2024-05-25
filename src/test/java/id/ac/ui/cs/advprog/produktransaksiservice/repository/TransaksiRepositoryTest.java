package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class TransaksiRepositoryTest {

    List<Transaksi> listTransaksi;

    @Autowired
    TransaksiRepository transaksiRepository;

    @BeforeEach
    void setUp() {

        List<Produk>  listProduk = new ArrayList<>();
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

//        listTransaksi = new ArrayList<>();
//        Transaksi transaksi1 = new Transaksi(Long.valueOf(123), listProduk, Long.valueOf(123), "selesai",
//                LocalDate.of(2024, 4, 23));
//        listTransaksi.add(transaksi1);
//        Transaksi transaksi2 = new Transaksi(Long.valueOf(345), listProduk, Long.valueOf(345), "selesai",
//                LocalDate.of(2024, 4, 24));
//        listTransaksi.add(transaksi2);
    }

//    @Test
//    void testCreateFindId() {
//        Transaksi transaksi = listTransaksi.get(1);
//        Transaksi result = transaksiRepository.save(transaksi);
//
//        Transaksi findResult = transaksiRepository.findById(listTransaksi.get(1).getTransaksiId().toString()).orElse(null);
//        assertEquals(transaksi.getTransaksiId(), result.getTransaksiId());
//        assertEquals(transaksi.getListProduk(), findResult.getListProduk());
//        assertEquals(transaksi.getTotalHarga(), findResult.getTotalHarga());
//        assertEquals(transaksi.getStatusPembayaran(), findResult.getStatusPembayaran());
//        assertEquals(transaksi.getTanggalTransaksi(), findResult.getTanggalTransaksi());
//    }

}
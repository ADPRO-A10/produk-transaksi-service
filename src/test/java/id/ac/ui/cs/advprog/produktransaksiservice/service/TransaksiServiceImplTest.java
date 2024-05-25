package id.ac.ui.cs.advprog.produktransaksiservice.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransaksiServiceImplTest {
    @InjectMocks
    TransaksiServiceImpl transaksiService;
    @Mock
    TransaksiRepository transaksiRepository;
    List<Transaksi> transaksiList;

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

//        transaksiList = new ArrayList<>();
//        Transaksi transaksi1 = new Transaksi(Long.valueOf(123), listProduk, Long.valueOf(123), "selesai",
//                LocalDate.of(2024, 4, 23));
//        transaksiList.add(transaksi1);
//        Transaksi transaksi2 = new Transaksi(Long.valueOf(345), listProduk, Long.valueOf(345), "selesai",
//                LocalDate.of(2024, 4, 24));
//        transaksiList.add(transaksi2);
    }

    @Test
    void testCreateTransaksi() {
        Transaksi transaksi = transaksiList.get(1);
        doReturn(transaksi).when(transaksiRepository).save(transaksi);

        Transaksi result = transaksiService.createTransaksi(transaksi);
        verify(transaksiRepository, times(1)).save(transaksi);
        assertEquals(transaksi.getTransaksiId(), result.getTransaksiId());
    }
    @Test
    void testFindById() {
        Transaksi transaksi = transaksiList.get(1);
        doReturn(transaksi).when(transaksiRepository).findById(transaksi.getTransaksiId().toString());

        Transaksi result = transaksiService.checkout(Long.valueOf(transaksi.getTransaksiId().toString())).orElse(null);
        assertEquals(transaksi.getTransaksiId(), result.getTransaksiId());
    }
}
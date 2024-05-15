package id.ac.ui.cs.advprog.produktransaksiservice.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@Entity
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaksiId;
    private List<Produk> listProduk;
    private Long totalHarga;
    private String statusPembayaran;
    private LocalDate tanggalTransaksi;

    public Transaksi() {}
    public Transaksi(Long transaksiId, List<Produk> listProduk, Long totalHarga,
                     String statusPembayaran, LocalDate tanggalTransaksi) {
        this.transaksiId = transaksiId;
        this.listProduk = listProduk;
        this.totalHarga = totalHarga;
        this.statusPembayaran = statusPembayaran;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public Transaksi(List<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    public Long countHarga() {
        long totalHarganya = 0;
        for (Produk produk : listProduk) {
            totalHarganya += Long.valueOf(produk.getHarga());
        }
        this.totalHarga = totalHarganya;
        return Long.valueOf(totalHarganya);
    }
}
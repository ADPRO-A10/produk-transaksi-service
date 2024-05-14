package id.ac.ui.cs.advprog.produktransaksiservice.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class Transaksi {
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

    public Transaksi(List<Produk> listProduk, Long totalHarga) {
        this.listProduk = listProduk;
        this.totalHarga = totalHarga;
    }

}
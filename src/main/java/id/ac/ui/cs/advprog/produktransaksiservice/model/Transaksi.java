package id.ac.ui.cs.advprog.produktransaksiservice.model;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID transaksiId;
    private List<Produk> listProduk;
    private Long totalHarga;
    private String statusPembayaran;
    private LocalDate tanggalTransaksi;
    private Transaksi(Builder builder) {
        this.transaksiId = builder.transaksiId;
        this.listProduk = builder.listProduk;
        if (builder.totalHarga < 0L) {
            throw new IllegalArgumentException("Total harga must be non-negative");
        }
        this.totalHarga = builder.totalHarga;
        this.statusPembayaran = builder.statusPembayaran;
        this.tanggalTransaksi = builder.tanggalTransaksi;
    }

    public static class Builder {
        private UUID transaksiId;
        private List<Produk> listProduk;
        private Long totalHarga;
        private String statusPembayaran;
        private LocalDate tanggalTransaksi;

        public Builder transaksiId(UUID transaksiId) {
            this.transaksiId = transaksiId;
            return this;
        }

        public Builder listProduk(List<Produk> listProduk) {
            this.listProduk = listProduk;
            return this;
        }

        public Builder totalHarga(Long totalHarga) {
            this.totalHarga = totalHarga;
            return this;
        }

        public Builder statusPembayaran(String statusPembayaran) {
            this.statusPembayaran = statusPembayaran;
            return this;
        }

        public Builder tanggalTransaksi(LocalDate tanggalTransaksi) {
            this.tanggalTransaksi = tanggalTransaksi;
            return this;
        }

        public Transaksi build() {
            return new Transaksi(this);
        }


    }

}
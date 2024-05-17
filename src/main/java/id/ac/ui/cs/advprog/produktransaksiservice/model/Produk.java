package id.ac.ui.cs.advprog.produktransaksiservice.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "Produk")
public class Produk {
    @Setter
    @Id
    @Column(name = "Id")
    private String produkId = UUID.randomUUID().toString();

    @Setter
    @Column(name = "Nama")
    private String nama;

    @Setter
    @Column(name = "Harga")
    private int harga;

    @Setter
    @Column(name = "Kategori")
    private String kategori;

    @Setter
    @Column(name = "Deskripsi")
    private String deskripsi;

    @Column(name = "StokTersedia")
    private int stokTersedia;

    @Setter
    @Column(name = "StokTerjual")
    private int stokTerjual;

    @Setter
    @Column(name = "Penjual")
    private String penjual;

    @Setter
    @Transient
    private List<Review> reviews;

    public Produk() {
    }

    public void setStokTersedia(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok yang diinput tidak valid");
        } else {
            this.stokTersedia = stok;
        }
    }
}
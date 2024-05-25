package id.ac.ui.cs.advprog.produktransaksiservice.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity(name = "Produk")
@Table(name = "produk")
public class Produk {

    @Setter
    @Id
    @Column(name = "ProdukId")
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
    @OneToMany(
            mappedBy = "produk",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "listProduk")
    List<Transaksi> transaksiList = new ArrayList<>();

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
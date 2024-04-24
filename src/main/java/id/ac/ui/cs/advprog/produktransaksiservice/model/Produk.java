package id.ac.ui.cs.advprog.produktransaksiservice.model;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Produk {
    @Setter
    private String produkId = UUID.randomUUID().toString();

    @Setter
    private String nama;

    @Setter
    private int harga;

    @Setter
    private String kategori;

    @Setter
    private String deskripsi;

    private int stokTersedia;

    @Setter
    private int stokTerjual;

    @Setter
    private String penjual;

    public void setStokTersedia(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok yang diinput tidak valid");
        } else {
            this.stokTersedia = stok;
        }
    }
}

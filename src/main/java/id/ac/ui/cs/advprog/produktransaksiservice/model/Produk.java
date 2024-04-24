package id.ac.ui.cs.advprog.produktransaksiservice.model;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class Produk {
    private String produkId = UUID.randomUUID().toString();
    private String nama;
    private int harga;
    private String kategori;
    private String deskripsi;
    private int stokTersedia;
    private int stokTerjual;
    private String penjual;
}

package id.ac.ui.cs.advprog.produktransaksiservice.model;

public interface Builder {
    Builder id (String id);
    Builder nama (String nama);

    Builder harga(int harga);

    Builder kategori(String kategori);

    Builder deskripsi(String deskripsi);

    Builder stokTersedia(int stokTersedia);

    Builder penjual(String penjual);
}

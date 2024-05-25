package id.ac.ui.cs.advprog.produktransaksiservice.model;

import java.util.List;
import java.util.UUID;

public class ProdukDirector{
    public static class ProdukBuilder implements Builder {
        String id;
        private String nama;
        private int harga;
        private String kategori;
        private String deskripsi;
        private int stokTersedia;
        private int stokTerjual;
        private String penjual;

        public ProdukBuilder() {

        }
        public ProdukBuilder id(String id) {
            this.id = id;
            return this;
        }
        public ProdukBuilder nama(String nama) {
            this.nama = nama;
            return this;
        }
        @Override
        public ProdukBuilder harga(int harga) {
            this.harga = harga;
            return this;
        }
        @Override
        public ProdukBuilder kategori(String kategori) {
            this.kategori = kategori;
            return this;
        }
        @Override
        public ProdukBuilder deskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
            return this;
        }
        @Override
        public ProdukBuilder stokTersedia(int stokTersedia) {
            if (stokTersedia < 0) {
                throw new IllegalArgumentException("Stok yang diinput tidak valid");
            } else {
                this.stokTersedia = stokTersedia;
            }
            return this;
        }
        public ProdukBuilder stokTerjual(int stokTerjual) {
            this.stokTerjual = stokTerjual;
            return this;
        }
        public ProdukBuilder penjual(String penjual) {
            this.penjual = penjual;
            return this;
        }
        public Produk build() {
            Produk newProduk = new Produk();
            newProduk.setProdukId(this.id);
            newProduk.setNama(this.nama);
            newProduk.setHarga(this.harga);
            newProduk.setDeskripsi(this.deskripsi);
            newProduk.setKategori(this.kategori);
            newProduk.setStokTersedia(this.stokTersedia);
            newProduk.setStokTerjual(this.stokTerjual);
            newProduk.setPenjual(this.penjual);
            return newProduk;
        }
    }
}

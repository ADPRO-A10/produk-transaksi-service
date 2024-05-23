package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

public interface ProdukService {
    Produk getProdukById(String id);
    Produk addProduk(Produk produk);
    Produk editProduk(String id, Produk updatedProduk);
}

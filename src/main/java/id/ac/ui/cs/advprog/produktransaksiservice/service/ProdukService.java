package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;

public interface ProdukService {
    Produk findProdukById(String id);
    Produk createProduk(Produk produk);
    Produk editProduk(String id, Produk updatedProduk);
    void deleteProduk(String id);
    Produk findProdukByNama(String nama);
    List<Produk> findAll();
}

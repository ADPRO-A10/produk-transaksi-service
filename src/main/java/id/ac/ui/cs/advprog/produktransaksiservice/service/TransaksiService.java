package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;
import java.util.Optional;

public interface TransaksiService {
    Transaksi createTransaksi(Transaksi transaksi);
    Optional<Transaksi> checkout(Long transaksiId);
    void processTransaksi(Pembeli pembeli, List<Produk> listProduk);
}
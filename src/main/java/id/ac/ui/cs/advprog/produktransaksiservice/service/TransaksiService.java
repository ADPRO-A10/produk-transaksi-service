package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransaksiService {
    Optional<Transaksi> getTransaksi(UUID transaksiId);
    List<Transaksi> getAllTransaksi();
    Long sumHarga(List<Produk> listProduk);
    void validateTransaksi(Pembeli pembeli, Transaksi transaksi, long totalHarga, List<Produk> listProduk);
    Transaksi processTransaksi(Pembeli pembeli, List<Penjual> listPenjual, List<Produk> listProduk);
}
package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;

public interface TransaksiService {
    Transaksi createTransaksi(Transaksi transaksi);
    Transaksi checkout(Long transaksiId);
}
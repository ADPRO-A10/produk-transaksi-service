package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;

public interface TransaksiService {
    public Transaksi createTransaksi(Transaksi transaksi);
    public Transaksi checkout(Long transaksiId);
}
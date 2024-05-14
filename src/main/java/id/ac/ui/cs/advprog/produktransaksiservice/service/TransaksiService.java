package id.ac.ui.cs.advprog.produktransaksiservice.service;

import id.ac.ui.cs.advprog.produktransaksiservice.command.DeductMoneyCommand;
import id.ac.ui.cs.advprog.produktransaksiservice.command.TransactionInvoker;
import id.ac.ui.cs.advprog.produktransaksiservice.command.UpdateStockCommand;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;

public interface TransaksiService {
    Transaksi createTransaksi(Transaksi transaksi);
    Transaksi checkout(Long transaksiId);
    void processTransaksi(Pembeli pembeli, List<Produk> listProduk);
}
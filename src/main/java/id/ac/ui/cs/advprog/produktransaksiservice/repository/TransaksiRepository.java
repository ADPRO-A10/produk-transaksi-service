package id.ac.ui.cs.advprog.produktransaksiservice.repository;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransaksiRepository {
    private List<Transaksi> transaksiData = new ArrayList<>();

    public Transaksi createTransaksi(Transaksi transaksi) {
        transaksiData.add(transaksi);
        return transaksi;
    }

    public Transaksi findTransaksiById(Long id) {
        for (Transaksi savedTransaksi : transaksiData) {
            if (savedTransaksi.getTransaksiId().equals(id)) {
                return savedTransaksi;
            }
        }
        return null;
    }
}
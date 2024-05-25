package id.ac.ui.cs.advprog.produktransaksiservice.service;
import id.ac.ui.cs.advprog.produktransaksiservice.command.*;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransaksiServiceImpl implements TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    public Transaksi createTransaksi(Transaksi transaksi) {

        return transaksiRepository.save(transaksi);
    }
    @Override
    public Optional<Transaksi> checkout(Long transaksiId) {
        return transaksiRepository.findById(String.valueOf(transaksiId));
    }

    @Override
    public void processTransaksi(Pembeli pembeli, List<Penjual> listPenjual, List<Produk> listProduk) {
        long totalHarga = transaksiRepository.sumHarga();
        Transaksi transaksi = new Transaksi.Builder()
                .transaksiId(UUID.randomUUID())
                .listProduk(listProduk)
                .totalHarga(totalHarga)
                .statusPembayaran("SUCCESS")
                .tanggalTransaksi(LocalDate.now())
                .build();
        //add validate
        TransactionInvoker invoker = new TransactionInvoker();
        invoker.addCommand(new AddLibraryCommand(pembeli, listProduk));
        for (Produk produk : listProduk) {
            invoker.addCommand(new UpdateStockCommand(produk, 1));
            invoker.addCommand(new UpdatePenjualBalanceCommand(produk, listPenjual));
            invoker.addCommand(new UpdatePenjualRiwayatCommand(produk, listPenjual, transaksi));
        }
        invoker.addCommand(new AddLibraryCommand(pembeli, listProduk));
        invoker.addCommand(new UpdatePembeliBalanceCommand(pembeli, totalHarga));
        invoker.addCommand(new UpdatePembeliRiwayatCommand(pembeli, transaksi));

        invoker.executeCommands();
    }
}
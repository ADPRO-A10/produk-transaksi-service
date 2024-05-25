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
    public Optional<Transaksi> getTransaksi(UUID transaksiId) {
        return transaksiRepository.findById(transaksiId);
    }

    @Override
    public Long sumHarga(List<Produk> listProduk) {
        long totalHarga = 0;
        for (Produk produk : listProduk) {
            totalHarga += produk.getHarga();
        }
        return totalHarga;
    }

    @Override
    public void validateTransaksi(Pembeli pembeli, Transaksi transaksi, long totalHarga) {
        if (pembeli.getBalance() < totalHarga) {
            throw new IllegalArgumentException();
        } else {
            transaksi.setStatusPembayaran("SUCCESS");
        }
    }

    @Override
    public Transaksi processTransaksi(Pembeli pembeli, List<Penjual> listPenjual, List<Produk> listProduk) {
        long totalHarga = sumHarga(listProduk);
        Transaksi transaksi = new Transaksi.Builder()
                .transaksiId(UUID.randomUUID())
                .listProduk(listProduk)
                .totalHarga(totalHarga)
                .statusPembayaran("AWAITING PAYMENT")
                .tanggalTransaksi(LocalDate.now())
                .build();

        try {
            validateTransaksi(pembeli, transaksi, totalHarga);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Insufficient Balance to Perform Transaction");
        }

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

        return transaksi;
    }
}
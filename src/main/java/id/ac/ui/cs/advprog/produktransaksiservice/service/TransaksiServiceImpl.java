package id.ac.ui.cs.advprog.produktransaksiservice.service;
import id.ac.ui.cs.advprog.produktransaksiservice.command.AddLibraryCommand;
import id.ac.ui.cs.advprog.produktransaksiservice.command.DeductMoneyCommand;
import id.ac.ui.cs.advprog.produktransaksiservice.command.TransactionInvoker;
import id.ac.ui.cs.advprog.produktransaksiservice.command.UpdateStockCommand;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;
import id.ac.ui.cs.advprog.produktransaksiservice.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void processTransaksi(Pembeli pembeli, List<Produk> listProduk) {
        Transaksi transaksi = new Transaksi.Builder().build();
        long totalHarga = transaksiRepository.sumHarga();
        TransactionInvoker invoker = new TransactionInvoker();
        invoker.addCommand(new AddLibraryCommand(pembeli, listProduk));
        invoker.addCommand(new DeductMoneyCommand(pembeli, totalHarga));
        for (Produk produk : listProduk) {
            invoker.addCommand(new UpdateStockCommand(produk, 1));
        }
        invoker.addCommand(new AddLibraryCommand(pembeli, listProduk));
        invoker.addCommand(new DeductMoneyCommand(pembeli, totalHarga));

        invoker.executeCommands();
    }
}
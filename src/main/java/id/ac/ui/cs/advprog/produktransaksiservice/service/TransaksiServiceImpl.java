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

@Service
public class TransaksiServiceImpl implements TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Override
    public Transaksi createTransaksi(Transaksi transaksi) {
        return transaksiRepository.createTransaksi(transaksi);
    }
    @Override
    public Transaksi checkout(Long transaksiId) {
        return transaksiRepository.findTransaksiById(transaksiId);
    }

    @Override
    public void processTransaksi(Pembeli pembeli, List<Produk> listProduk) {
        long totalHarga = transaksiRepository.countTotal(listProduk);
        TransactionInvoker invoker = new TransactionInvoker();
        invoker.addCommand(new DeductMoneyCommand(pembeli, totalHarga));
        for (Produk produk : listProduk) {
            invoker.addCommand(new UpdateStockCommand(produk, 1)); // Assuming quantity is 1 for simplicity
        }
        invoker.addCommand(new AddLibraryCommand(pembeli, listProduk));
        invoker.addCommand(new DeductMoneyCommand(pembeli, totalHarga));
        Transaksi transaksi = new Transaksi(listProduk, totalHarga);


        invoker.executeCommands();
    }
}
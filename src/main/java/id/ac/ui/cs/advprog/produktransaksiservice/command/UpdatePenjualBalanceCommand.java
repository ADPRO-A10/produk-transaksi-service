package id.ac.ui.cs.advprog.produktransaksiservice.command;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;

public class UpdatePenjualBalanceCommand implements TransactionCommand {
    private List<Penjual> listPenjual;
    private Produk produk;

    public UpdatePenjualBalanceCommand(Produk produk, List<Penjual> listPenjual) {
        this.produk = produk;
        this.listPenjual = listPenjual;
    }

    @Override
    public void execute() {
        listPenjual.stream()
            .filter(penjual -> penjual.getUsername().equals(produk.getPenjual()))
            .findFirst()
            .ifPresent(penjual -> penjual.setBalance(penjual.getBalance() + produk.getHarga()));
    }

}
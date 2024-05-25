package id.ac.ui.cs.advprog.produktransaksiservice.command;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;

public class UpdatePembeliBalanceCommand implements TransactionCommand {
    private Pembeli pembeli;
    private long totalHarga;

    public UpdatePembeliBalanceCommand(Pembeli pembeli, long totalHarga) {
        this.pembeli = pembeli;
        this.totalHarga = totalHarga;
    }

    @Override
    public void execute() {
        pembeli.setBalance(pembeli.getBalance() - totalHarga);
    }

}
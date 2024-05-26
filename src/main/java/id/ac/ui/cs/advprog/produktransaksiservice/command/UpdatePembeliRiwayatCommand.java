package id.ac.ui.cs.advprog.produktransaksiservice.command;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;

import java.util.List;

public class UpdatePembeliRiwayatCommand implements TransactionCommand {
    private Pembeli pembeli;
    private Transaksi transaksi;

    public UpdatePembeliRiwayatCommand(Pembeli pembeli, Transaksi transaksi) {
        this.pembeli = pembeli;
        this.transaksi = transaksi;
    }

    @Override
    public void execute() {
        pembeli.getRiwayatTransaksi().add(transaksi);
    }

}
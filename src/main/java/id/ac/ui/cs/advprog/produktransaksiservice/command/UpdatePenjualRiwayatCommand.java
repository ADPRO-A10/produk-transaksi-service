package id.ac.ui.cs.advprog.produktransaksiservice.command;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Penjual;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Transaksi;

import java.util.List;

public class UpdatePenjualRiwayatCommand implements TransactionCommand {
    private Produk produk;
    private List<Penjual> listPenjual;
    private Transaksi transaksi;

    public UpdatePenjualRiwayatCommand(Produk produk, List<Penjual> listPenjual, Transaksi transaksi) {
        this.produk = produk;
        this.listPenjual = listPenjual;
        this.transaksi = transaksi;
    }

    @Override
    public void execute() {
        listPenjual.stream()
                .filter(penjual -> penjual.getUsername().equals(produk.getPenjual()))
                .findFirst()
                .ifPresent(penjual -> penjual.getRiwayatTransaksi().add(transaksi));
    }

}
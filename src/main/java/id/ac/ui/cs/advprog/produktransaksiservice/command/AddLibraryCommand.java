package id.ac.ui.cs.advprog.produktransaksiservice.command;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

import java.util.List;

public class AddLibraryCommand implements TransactionCommand {
    private Pembeli pembeli;
    private List<Produk> listProduk;

    public AddLibraryCommand(Pembeli pembeli, List<Produk> listProduk) {
        this.pembeli = pembeli;
        this.listProduk = listProduk;
    }

    @Override
    public void execute() {
        for (Produk produk: listProduk) {
            pembeli.getLibrary().add(produk);
        }
    }

    @Override
    public void undo() {
        for (Produk produk: listProduk) {
            pembeli.getLibrary().remove(produk);
        }
    }
}
package id.ac.ui.cs.advprog.produktransaksiservice.command;

import id.ac.ui.cs.advprog.produktransaksiservice.model.Produk;

public class UpdateStockCommand implements TransactionCommand {
    private Produk produk;
    private int quantity;

    public UpdateStockCommand(Produk produk, int quantity) {
        this.produk = produk;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        produk.setStokTersedia(produk.getStokTersedia() - quantity);
        produk.setStokTerjual(produk.getStokTerjual() + quantity);
    }

}
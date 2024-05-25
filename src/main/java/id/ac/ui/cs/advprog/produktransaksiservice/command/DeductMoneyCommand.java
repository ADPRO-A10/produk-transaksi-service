package id.ac.ui.cs.advprog.produktransaksiservice.command;
import id.ac.ui.cs.advprog.produktransaksiservice.model.Pembeli;

public class DeductMoneyCommand implements TransactionCommand {
    private Pembeli pembeli;
    private long totalHarga;

    public DeductMoneyCommand(Pembeli pembeli, long totalHarga) {
        this.pembeli = pembeli;
        this.totalHarga = totalHarga;
    }

    @Override
    public void execute() {
        pembeli.setBalance(pembeli.getBalance() - totalHarga);
    }

    @Override
    public void undo() {
        pembeli.setBalance(pembeli.getBalance() + totalHarga);
    }
}
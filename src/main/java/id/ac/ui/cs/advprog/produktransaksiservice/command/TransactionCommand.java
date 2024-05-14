package id.ac.ui.cs.advprog.produktransaksiservice.command;

public interface TransactionCommand {
    void execute();
    void undo();
}
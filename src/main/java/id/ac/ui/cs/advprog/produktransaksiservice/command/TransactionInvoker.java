package id.ac.ui.cs.advprog.produktransaksiservice.command;

import java.util.ArrayList;
import java.util.List;

public class TransactionInvoker {
    private List<TransactionCommand> commandList = new ArrayList<>();

    public void addCommand(TransactionCommand command) {
        commandList.add(command);
    }

    public void executeCommands() {
        for (TransactionCommand command : commandList) {
            command.execute();
        }
    }

}
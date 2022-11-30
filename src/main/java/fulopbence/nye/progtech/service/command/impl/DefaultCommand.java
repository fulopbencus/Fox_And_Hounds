package fulopbence.nye.progtech.service.command.impl;

import fulopbence.nye.progtech.service.command.Command;
import fulopbence.nye.progtech.ui.PrintWrapper;

/**
 *  Runs when gets an input which is not in the command list.
 */
public class DefaultCommand implements Command {

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";

    private final PrintWrapper printWrapper;

    public DefaultCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }


}

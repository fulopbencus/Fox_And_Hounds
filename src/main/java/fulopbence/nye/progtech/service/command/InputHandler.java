package fulopbence.nye.progtech.service.command;

import java.util.List;

import fulopbence.nye.progtech.ui.PrintWrapper;

/**
 * Component that handles the player's inputs.
 */
public class InputHandler {

    private List<Command> commandList;

    private PrintWrapper printWrapper;

    public InputHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Handles a chosen input from the list of possible inputs.
     */
    public void handleInput(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }

}

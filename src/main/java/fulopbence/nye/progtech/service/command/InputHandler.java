package fulopbence.nye.progtech.service.command;

import java.util.List;

/**
 * Component that handles the player's inputs.
 */
public class InputHandler {

    private List<Command> commandList;

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
            }
        }


    }

}

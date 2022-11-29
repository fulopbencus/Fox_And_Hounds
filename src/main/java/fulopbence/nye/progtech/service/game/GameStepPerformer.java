package fulopbence.nye.progtech.service.game;

import fulopbence.nye.progtech.service.command.InputHandler;
import fulopbence.nye.progtech.service.input.UserInputReader;

/**
 * Performs game step with the help of the input reader and handler.
 */

public class GameStepPerformer {

    private UserInputReader userInputReader;

    public GameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        this.userInputReader = userInputReader;
        this.inputHandler = inputHandler;
    }

    private InputHandler inputHandler;

    public void performGameStep() {
        String input = userInputReader.readInput();
        inputHandler.handleInput(input);
    }


}

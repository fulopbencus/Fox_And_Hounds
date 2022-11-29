package fulopbence.nye.progtech.service.command.impl;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * As the name states it's a command used for exiting the game.
 */
public class ExitCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String EXIT_COMMAND = "exit";

    private GameState gameState;

    public ExitCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Exit flag will be true");
        gameState.setUserWantsToExit(true);
    }
}

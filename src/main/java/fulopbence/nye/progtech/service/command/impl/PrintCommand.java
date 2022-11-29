package fulopbence.nye.progtech.service.command.impl;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.service.command.Command;
import fulopbence.nye.progtech.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The command used to print out the current state of the map.
 */

public class PrintCommand implements Command {

    private static final  Logger LOGGER = LoggerFactory.getLogger(PrintCommand.class);

    private static final String PRINT_COMMAND = "print";

    private MapPrinter mapPrinter;
    private GameState gameState;


    @Override
    public boolean canProcess(String input) {
        return PRINT_COMMAND.equals(input);
    }

    public PrintCommand(MapPrinter mapPrinter, GameState gameState) {
        this.mapPrinter = mapPrinter;
        this.gameState = gameState;
    }

    @Override
    public void process(String input) {
        mapPrinter.printMap(gameState.getMapVo());
    }
}

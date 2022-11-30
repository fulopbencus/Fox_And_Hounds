package fulopbence.nye.progtech.service.command.impl;

import fulopbence.nye.progtech.service.exception.PutException;
import fulopbence.nye.progtech.ui.PrintWrapper;
import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.ui.MapPrinter;
import fulopbence.nye.progtech.service.command.FoxPutPerformer;
import fulopbence.nye.progtech.service.map.validation.MapValidator;
import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Puts the fox to up and right by one square.
 */

public class FoxUpRightCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoxUpRightCommand.class);

    private static final String PUT_COMMAND_UR = "foxur";

    private  GameState gameState;
    private  FoxPutPerformer foxputPerformer;
    private  MapPrinter mapPrinter;
    private  PrintWrapper printWrapper;

    private MapVo mapVo;
    private MapValidator mapValidator;

    public FoxUpRightCommand(GameState gameState, FoxPutPerformer foxputPerformer, MapValidator mapValidator,
                      MapPrinter mapPrinter, PrintWrapper printWrapper) {
        this.gameState = gameState;
        this.foxputPerformer = foxputPerformer;
        this.mapValidator = mapValidator;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return PUT_COMMAND_UR.equals(input);
    }

    @Override
    public void process(String input) {
        int[][] oldMap = mapVo.getValues();
        int rowIndex = ; //Get the fox's position using a for loop which examines the maps values
        int columnIndex = ;
        int number = 4;

        LOGGER.info("Moving the fox to: {}, columnIndex = {}, number = {}", rowIndex, columnIndex, number);

        try {
            MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex, columnIndex, number);
            gameState.setMapVo(newMap);

            mapPrinter.printMap(newMap);
        } catch (PutException e) {
            System.out.println("Something went wrong while executing put operation");
        }
    }
}

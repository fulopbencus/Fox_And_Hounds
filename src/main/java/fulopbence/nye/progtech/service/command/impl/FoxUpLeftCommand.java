package fulopbence.nye.progtech.service.command.impl;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.command.Command;
import fulopbence.nye.progtech.service.command.FoxPutPerformer;
import fulopbence.nye.progtech.service.exception.PutException;
import fulopbence.nye.progtech.ui.MapPrinter;
import fulopbence.nye.progtech.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;

/**
 * Puts the fox to up and right by one square.
 */

public class FoxUpLeftCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(FoxUpRightCommand.class);

    private static final String PUT_COMMAND_UL = "foxul";

    private  GameState gameState;
    private  FoxPutPerformer foxputPerformer;
    private  MapPrinter mapPrinter;
    private  PrintWrapper printWrapper;
    private MapVo mapVo;

    public FoxUpLeftCommand(GameState gameState, FoxPutPerformer
            foxputPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper, MapVo mapVo) {
        this.gameState = gameState;
        this.foxputPerformer = foxputPerformer;
        this.mapPrinter = mapPrinter;
        this.printWrapper = printWrapper;
        this.mapVo = mapVo;
    }

    @Override
    public boolean canProcess(String input) {
        return PUT_COMMAND_UL.equals(input);
    }

    @Override
    public void process(String input) {
        MapVo map = gameState.getMapVo();
        int numberOfRows = mapVo.getNumberOfRows();
        int numberOfColumns = mapVo.getNumberOfColumns();
        int rowIndex = 0; //Get the fox's position using a for loop which examines the maps values
        int columnIndex = 0;
        int[][] oldMap = map.getValues();
        try {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (oldMap[i][j] == 4) {
                        rowIndex = i;
                        columnIndex = j;
                    }
                }
            }
            System.out.println(rowIndex+","+columnIndex);
        } catch (NullPointerException e) {
            System.out.println("NullPointer");
        }


        LOGGER.info("Moving the fox to: rowIndex = {}, columnIndex = {}, number = 4", rowIndex - 1, columnIndex - 1, 4);
        if (rowIndex - 1 < 0 || rowIndex - 1 >= mapVo.getNumberOfRows() || columnIndex - 1 < 0 || columnIndex - 1 >= mapVo.getNumberOfColumns()) {
            LOGGER.info("Moving failed");
            System.out.println("Don't try to move out of the map!");
        } else {
            try {
                MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex, columnIndex, 0);

                gameState.setMapVo(newMap);
            } catch (PutException e) {
                System.out.println("Something went wrong while executing put operation");
            }
            try {
                MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex - 1, columnIndex - 1, 4);

                gameState.setMapVo(newMap);
                mapPrinter.printMap(newMap);
            } catch (PutException e) {
                System.out.println("Something went wrong while executing put operation");
            }
        }
    }
}

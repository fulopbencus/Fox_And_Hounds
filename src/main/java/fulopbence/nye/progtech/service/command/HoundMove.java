package fulopbence.nye.progtech.service.command;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.PutException;
import fulopbence.nye.progtech.ui.MapPrinter;
import fulopbence.nye.progtech.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Moves the Hounds randomly.
 */
public class HoundMove {

    private static final Logger LOGGER = LoggerFactory.getLogger(HoundMove.class);

    /**
     * Method implementation.
     */
    public void move(GameState gameState, FoxPutPerformer
            foxputPerformer, MapPrinter mapPrinter, PrintWrapper printWrapper, MapVo mapVo) {
        MapVo map = gameState.getMapVo();
        int[] hound = {6, 7, 8, 9};
        int randomhound = hound[(int) (Math.random() * (3 + 1) + 0)];
        int numberOfRows = mapVo.getNumberOfRows();
        int numberOfColumns = mapVo.getNumberOfColumns();
        int rowIndex = 0; //Get the hound's position using a for loop which examines the maps values
        int columnIndex = 0;
        int[][] oldMap = map.getValues();
        try {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    if (oldMap[i][j] == randomhound) {
                        rowIndex = i;
                        columnIndex = j;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointer");
        }
        LOGGER.info("Hound is moving...");

        try {
            int a = (int) (Math.random() * (1 + 1) + 0); //Randomly chooses down-left or down-right move
            if (rowIndex >= mapVo.getNumberOfRows()) {
                move(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo);
            } else if (a == 0 && columnIndex - 1 >= 0 && oldMap[rowIndex + 1][columnIndex - 1] == 0) {
                try {
                    MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex, columnIndex, 0);

                    gameState.setMapVo(newMap);
                } catch (PutException e) {
                    System.out.println("Something went wrong while executing put operation");
                }
                MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex + 1, columnIndex - 1, randomhound);

                gameState.setMapVo(newMap);
                mapPrinter.printMap(newMap);
            } else if (columnIndex + 1 < mapVo.getNumberOfColumns() && oldMap[rowIndex + 1][columnIndex + 1] == 0) {
                try {
                    MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex, columnIndex, 0);

                    gameState.setMapVo(newMap);
                } catch (PutException e) {
                    System.out.println("Something went wrong while executing put operation");
                }
                MapVo newMap = foxputPerformer.perform(gameState.getMapVo(), rowIndex + 1, columnIndex + 1, randomhound);

                gameState.setMapVo(newMap);
                mapPrinter.printMap(newMap);
            } else {
                move(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo);
            }
        } catch (PutException e) {
            System.out.println("Something went wrong while executing put operation");
        }
    }
}

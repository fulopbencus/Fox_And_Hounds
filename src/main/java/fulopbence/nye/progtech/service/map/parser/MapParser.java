package fulopbence.nye.progtech.service.map.parser;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.MapReadException;

import java.util.List;

/**
 * Parses the copy of a raw map into a MapVo object.
 */

public class MapParser {

    private final int numberOfRows;
    private final int numberOfColumns;

    public MapParser(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    /**
     * Separates the maps' units.
     */
    public MapVo parse(List<String> rawMap) {
        checknumberOfRows(rawMap);

        int[][] values = getValues(rawMap);


        return new MapVo(numberOfRows, numberOfColumns, values);
    }

    private int[][] getValues(List<String> rawMap) {
        int[][] result = new int[numberOfRows][];

        for (int i = 0; i < numberOfRows; i++) {
            result[i] = new int[numberOfColumns];

            String row = rawMap.get(i);
            String[] numbersAsString = row.split("");

            for (int j = 0; j < numberOfColumns; j++) {
                int n = Integer.parseInt(numbersAsString[j]);
                result[i][j] = n;
            }
        }

        return result;
    }

    private void checknumberOfRows(List<String> rawMap) {
        if (rawMap.size() != numberOfRows) {
            throw new MapReadException("Number of rows are incorrect");
        }
    }

}

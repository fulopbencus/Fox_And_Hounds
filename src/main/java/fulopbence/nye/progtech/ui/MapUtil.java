package fulopbence.nye.progtech.ui;

import java.util.ArrayList;
import java.util.List;

import fulopbence.nye.progtech.model.MapVo;

/**
 * Gives information about the map.
 */
public class MapUtil {

    /**
     * Gets number of rows of the map.
     */
    public List<Integer> getRowOfMap(MapVo mapVo, int rowIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVo.getValues();
        for (int i = 0; i < mapVo.getNumberOfColumns(); i++) {
            result.add(map[rowIndex][i]);
        }

        return result;
    }

    /**
     * Gets the number of columns of a map.
     */

    public List<Integer> getColumnOfMap(MapVo mapVo, int columnIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVo.getValues();
        for (int i = 0; i < mapVo.getNumberOfRows(); i++) {
            result.add(map[i][columnIndex]);
        }

        return result;
    }

    /**
     * Checks if the fox is at the top row.
     */
    public boolean isMapCompleted(MapVo mapVo) {
        boolean result = false;

        int[][] map = mapVo.getValues();
        for (int i = 0; i < mapVo.getNumberOfColumns(); i++) {
            if (map[0][i] == mapVo.getFoxMapValue()) {
                result = true;
                System.out.println("       __---˝˝˝˝˝˝˝˝˝˝---__");
                System.out.println("       ||  THE FOX WINS! ||");
                System.out.println("       ˝˝---__________---˝˝");
            }
        }

        return result;
    }
}
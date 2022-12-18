package fulopbence.nye.progtech.ui;

import java.util.ArrayList;
import java.util.List;

import fulopbence.nye.progtech.model.GameState;
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

    /**
     * Checks if the fox can move to anywhere, and if not, the game is lost.
     */
    public boolean isGameLost(GameState gameState, MapVo mapVo){
        boolean result = false;
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
        } catch (NullPointerException e) {
            System.out.println("NullPointer");
        }
        if(rowIndex == 0){
            result = false;
        } else if(rowIndex + 1 >= numberOfRows){ //Checks the bottom situations |_ , __ , _|
                if(columnIndex - 1 < 0) {
                    if (oldMap[rowIndex - 1][columnIndex + 1] != 0) {
                        result = true;
                    }
                }else if(columnIndex + 1 >= numberOfColumns) {
                    if (oldMap[rowIndex - 1][columnIndex - 1] != 0) {
                        result = true;
                    }
                }else if(oldMap[rowIndex - 1][columnIndex - 1] != 0 && oldMap[rowIndex - 1][columnIndex + 1] != 0){
                    result = true;
                }
            }//End of bottom situation check
            else if(columnIndex + 1 >= numberOfColumns){//Checks the right side situation |->|
                    if(oldMap[rowIndex + 1][columnIndex - 1] != 0 && oldMap[rowIndex - 1][columnIndex - 1] != 0){
                        result = true;
                }
            }
            else if(columnIndex - 1 < 0){//Checks the left side situation |<-|
                    if(oldMap[rowIndex + 1][columnIndex + 1] != 0 && oldMap[rowIndex - 1][columnIndex + 1] != 0){
                        result = true;
                }
            }//Checks the within map bounds situation
            else if(oldMap[rowIndex + 1][columnIndex + 1] != 0 && oldMap[rowIndex - 1][columnIndex + 1] != 0 && oldMap[rowIndex + 1][columnIndex - 1] != 0 && oldMap[rowIndex - 1][columnIndex - 1] != 0){
                result = true;
            }
        if(result == true){System.out.println("You have LOST!");}
        return result;
    }
}
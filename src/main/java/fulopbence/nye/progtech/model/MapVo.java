package fulopbence.nye.progtech.model;

import java.util.Arrays;
import java.util.Objects;
/**
 * The game's map.
 */
public class MapVo {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final int[][] values;
    private static final int foxMapValue = 4;
    private static final int houndMapValue = 7;

    public MapVo(int numberOfRows, int numberOfColumns, int[][] values) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.values = deepCopy(values);
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int[][] getValues() {
        return deepCopy(values);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getFoxMapValue() {
        return foxMapValue;
    }

    public int getHoundMapValue() {
        return houndMapValue;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] result = null;

        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapVo mapVo = (MapVo) o;
        return numberOfRows == mapVo.numberOfRows && numberOfColumns == mapVo.numberOfColumns && Arrays.deepEquals(values, mapVo.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberOfRows, numberOfColumns);
        result = 31 * result + Arrays.deepHashCode(values);
        return result;
    }

    @Override
    public String toString() {
        return "MapVo{" +
                "numberOfRows=" + numberOfRows +
                ", numberOfColumns=" + numberOfColumns +
                ", values=" + Arrays.deepToString(values) +
                '}';
    }
}

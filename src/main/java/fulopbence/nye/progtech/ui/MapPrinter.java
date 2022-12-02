package fulopbence.nye.progtech.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.ui.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Declaring MapPrinter.
 */
public class MapPrinter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapPrinter.class);

    private static final String HORIZONTAL_SEPARATOR = "=";
    private static final String VERTICAL_THIN_SEPARATOR = " | ";
    private static final String VERTICAL_THICK_SEPARATOR = " || ";
    private static final String LEFT_SIDE_VERTICAL_THICK_SEPARATOR = "|| ";
    private static final String RIGHT_SIDE_VERTICAL_THICK_SEPARATOR = " ||";

    private final int boxWidth;
    private final int boxHeight;
    private final MapUtil mapUtil;
    private final PrintWrapper printWrapper;

    public MapPrinter(int boxWidth, int boxHeight, MapUtil mapUtil, PrintWrapper printWrapper) {
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.mapUtil = mapUtil;
        this.printWrapper = printWrapper;
    }

    /**
     * Prints the provided map to the standard output with separators.
     */
    public void printMap(MapVo mapVo) {
        LOGGER.info("Printing the map...");
        printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(mapVo))));

        for (int rowIndex = 0; rowIndex < mapVo.getNumberOfRows(); rowIndex++) {
            String rowToPrint = getRowToPrint(mapVo, rowIndex);
            printWrapper.printLine(rowToPrint);

            if (shouldPrintHorizontalSeparator(rowIndex)) {
                printWrapper.printLine((getSeparator(HORIZONTAL_SEPARATOR, getSeparatorWidth(mapVo))));
            }
        }
    }

    private String getRowToPrint(MapVo mapVo, int rowIndex) {
        List<String> row = getRowAsStringList(mapVo, rowIndex);
        List<List<String>> boxPartsList = getBoxPartsOfRow(row);
        List<String> boxParts = joinBoxParts(boxPartsList);

        return LEFT_SIDE_VERTICAL_THICK_SEPARATOR + String.join(VERTICAL_THICK_SEPARATOR, boxParts) + RIGHT_SIDE_VERTICAL_THICK_SEPARATOR;
    }

    private List<String> getRowAsStringList(MapVo mapVo, int rowIndex) {
        return mapUtil.getRowOfMap(mapVo, rowIndex).stream()
                .map(this::valueToString)
                .collect(Collectors.toList());
    }

    private List<List<String>> getBoxPartsOfRow(List<String> row) {
        List<List<String>> boxParts = new ArrayList<>();
        List<String> currentBox = new ArrayList<>();

        for (String s : row) {
            currentBox.add(s);

            if (currentBox.size() == boxWidth) {
                boxParts.add(currentBox);
                currentBox = new ArrayList<>();
            }
        }

        if (!currentBox.isEmpty()) {
            boxParts.add(currentBox);
        }

        return boxParts;
    }

    private List<String> joinBoxParts(List<List<String>> boxPartsList) {
        return boxPartsList.stream()
                .map(strings -> String.join(VERTICAL_THIN_SEPARATOR, strings))
                .collect(Collectors.toList());
    }

    private String getSeparator(String separatorCharacter, int times) {
        List<String> separators = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            separators.add(separatorCharacter);
        }
        return String.join("", separators);
    }

    private String valueToString(int value) {
        return value == 0 ? " " : String.valueOf(value);
    }

    private boolean shouldPrintHorizontalSeparator(int rowIndex) {
        return (rowIndex + 1) % boxHeight == 0;
    }

    private int getSeparatorWidth(MapVo mapVo) {
        int numberOfBoxes = mapVo.getNumberOfColumns() / boxWidth;
        return (boxWidth * 3 + (boxWidth - 1)) * numberOfBoxes + (numberOfBoxes - 1) * 2 + 4;
    }

}

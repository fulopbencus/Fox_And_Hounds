package fulopbence.nye.progtech.service.map.validation.impl;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.MapValidationException;
import fulopbence.nye.progtech.service.map.validation.MapValidator;

/**
 * Checks the starting number of hounds on the map.
 */

public class HoundCountValidator implements MapValidator {

    @Override
    public void validate(MapVo mapVo) {
        int houndCounter = 0;
        int houndMapValue = mapVo.getHoundMapValue();
        int numberOfRows = mapVo.getNumberOfRows();
        int numberOfColumns = mapVo.getNumberOfColumns();
        int[][] values = mapVo.getValues();

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (values[i][j] == houndMapValue) {
                    houndCounter++;
                }
            }

        }
        if (houndCounter != numberOfRows / 2) {
            throw new MapValidationException("There should be N/2 number of hounds where the map's dimensoin is N*N, this has"
                    + houndCounter + "and should have:" + numberOfRows / 2);
        }
    }
}




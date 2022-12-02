package fulopbence.nye.progtech.service.command;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.PutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class used to write a number to a given position of a map.
 */
public class FoxPutPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoxPutPerformer.class);

    public MapVo perform(MapVo mapVo, int rowIndex, int columnIndex, int number) throws PutException {

        int[][] map = mapVo.getValues();

        map[rowIndex][columnIndex] = number;

        return new MapVo(mapVo.getNumberOfRows(), mapVo.getNumberOfColumns(), map);
    }

}
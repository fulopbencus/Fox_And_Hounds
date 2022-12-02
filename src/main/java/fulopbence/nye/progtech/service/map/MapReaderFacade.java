package fulopbence.nye.progtech.service.map;

import java.util.List;

import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.exception.MapParseException;
import fulopbence.nye.progtech.service.exception.MapReadException;
import fulopbence.nye.progtech.service.exception.MapValidationException;
import fulopbence.nye.progtech.service.map.parser.MapParser;
import fulopbence.nye.progtech.service.map.validation.reader.MapReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a simplified "interface" to read maps easier.
 */

public class MapReaderFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapReaderFacade.class);

    private final MapReader mapReader;
    private final MapParser mapParser;



    public MapReaderFacade(MapReader mapReader, MapParser mapParser) {
        this.mapReader = mapReader;
        this.mapParser = mapParser;
    }

    /**
     * Reads map.
     */
    public MapVo readMap() {
        MapVo result;

        try {

            List<String> rawMap = mapReader.read();
            result = mapParser.parse(rawMap);
        } catch (MapReadException e) {
            LOGGER.error("Failed to read map", e);
            throw new RuntimeException("Failed to read map");
        } catch (MapParseException e) {
            LOGGER.error("Failed to parse map");
            throw new RuntimeException("Failed to parse map");
        } catch (MapValidationException e) {
            LOGGER.error("Failed to validate map");
            throw new RuntimeException("Failed to validate map");
        } catch (RuntimeException e) {
            LOGGER.error("Unknown exception");
            throw new RuntimeException("Unknown exception");
        }
        return result;
    }

}

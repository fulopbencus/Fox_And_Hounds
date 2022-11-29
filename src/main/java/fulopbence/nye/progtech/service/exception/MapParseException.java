package fulopbence.nye.progtech.service.exception;

/**
 * Custom exception which should get thrown, when there is a problem parsing the map.
 */
public class MapParseException extends RuntimeException {
    public MapParseException(String message) {
        super(message);
    }
}

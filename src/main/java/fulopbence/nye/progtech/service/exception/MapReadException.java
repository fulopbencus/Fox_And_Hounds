package fulopbence.nye.progtech.service.exception;

/**
 * Custom exception which should get thrown, when there is a problem reading the map.
 */
public class MapReadException extends RuntimeException {

    public MapReadException(String message) {
        super(message);
    }
}

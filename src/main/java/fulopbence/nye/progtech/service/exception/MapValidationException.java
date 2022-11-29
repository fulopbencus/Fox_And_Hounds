package fulopbence.nye.progtech.service.exception;

/**
 * Custom exception which should get thrown, when there is a problem validating the map.
 */

public class MapValidationException extends RuntimeException {

    public MapValidationException(String message) {

        super(message);
    }
}

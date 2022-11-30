package fulopbence.nye.progtech.service.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Component that reads the player's input.
 */
public class UserInputReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputReader.class);

    private final BufferedReader reader;

    public UserInputReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Reads the user input and returns it as a string.
     */
    public String readInput() {
        String input = null;

        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Exception occurred while reading user input", e);
        }

        return input;
    }

}

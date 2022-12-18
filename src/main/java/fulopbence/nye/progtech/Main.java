package fulopbence.nye.progtech;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.command.Command;
import fulopbence.nye.progtech.service.command.FoxPutPerformer;
import fulopbence.nye.progtech.service.command.HoundMove;
import fulopbence.nye.progtech.service.command.InputHandler;
import fulopbence.nye.progtech.service.command.impl.*;
import fulopbence.nye.progtech.service.game.GameController;
import fulopbence.nye.progtech.service.game.GameStepPerformer;
import fulopbence.nye.progtech.service.input.UserInputReader;
import fulopbence.nye.progtech.service.map.MapReaderFacade;
import fulopbence.nye.progtech.service.map.parser.MapParser;
import fulopbence.nye.progtech.service.map.validation.reader.MapReader;
import fulopbence.nye.progtech.service.map.validation.reader.impl.BufferedReaderMapReader;
import fulopbence.nye.progtech.ui.MapPrinter;
import fulopbence.nye.progtech.ui.MapUtil;
import fulopbence.nye.progtech.ui.PrintWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class.
 */
public class Main {
    /**
     * Entry point.
     */
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("fulopbence.nye.progtech");
        GameController gameController = applicationContext.getBean(GameController.class);
        gameController.gameLoop();
    }

}
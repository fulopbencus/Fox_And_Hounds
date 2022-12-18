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

/**
 * Main class. Where it's all connected.
 */
public class Main {
    /**
     * Entry point.
     */
    public static void main(String[] args) {

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/staticmap.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(reader);

        MapParser mapParser = new MapParser(8, 8);
        MapUtil mapUtil = new MapUtil();

        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser);
        MapVo mapVo = mapReaderFacade.readMap();

        FoxPutPerformer foxputPerformer = new FoxPutPerformer();
        HoundMove houndMove = new HoundMove();

        GameState gameState = new GameState(mapVo, false);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        UserInputReader userInputReader = new UserInputReader(stdin);

        PrintWrapper printWrapper = new PrintWrapper();
        MapPrinter mapPrinter = new MapPrinter(8, 8, mapUtil, printWrapper);

        List<Command> commandList = Arrays.asList(
                new PrintCommand(mapPrinter, gameState),
                new ExitCommand(gameState),
                new FoxDownLeftCommand(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo, houndMove),
                new FoxDownRightCommand(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo, houndMove),
                new FoxUpRightCommand(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo, houndMove),
                new FoxUpLeftCommand(gameState, foxputPerformer, mapPrinter, printWrapper, mapVo, houndMove),
                new DefaultCommand(printWrapper)
        );

        InputHandler inputHandler = new InputHandler(commandList);

        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInputReader, inputHandler);

        GameController gameController = new GameController(gameState, mapUtil, gameStepPerformer);
        gameController.gameLoop();
    }

}
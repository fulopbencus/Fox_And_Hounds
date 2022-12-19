package fulopbence.nye.progtech;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.model.MapVo;
import fulopbence.nye.progtech.service.command.Command;
import fulopbence.nye.progtech.service.command.FoxPutPerformer;
import fulopbence.nye.progtech.service.command.HoundMove;
import fulopbence.nye.progtech.service.command.InputHandler;
import fulopbence.nye.progtech.service.command.impl.DefaultCommand;
import fulopbence.nye.progtech.service.command.impl.ExitCommand;
import fulopbence.nye.progtech.service.command.impl.FoxDownLeftCommand;
import fulopbence.nye.progtech.service.command.impl.FoxDownRightCommand;
import fulopbence.nye.progtech.service.command.impl.FoxUpLeftCommand;
import fulopbence.nye.progtech.service.command.impl.FoxUpRightCommand;
import fulopbence.nye.progtech.service.command.impl.PrintCommand;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configurator.
 */
@Configuration
public class Config {


    @Bean
    GameState gameState() {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/staticmap.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        MapReader mapReader = new BufferedReaderMapReader(reader);
        MapParser mapParser = new MapParser(8, 8);
        MapReaderFacade mapReaderFacade = new MapReaderFacade(mapReader, mapParser);
        MapVo mapVo = mapReaderFacade.readMap();
        return new GameState(mapVo, false);
    }

    @Bean
    Command printCommand() {
        return new PrintCommand(mapPrinter(mapUtil(), printWrapper()), gameState());
    }

    @Bean
    Command exitCommand() {
        return new ExitCommand(gameState());
    }

    @Bean
    Command foxDownLeftCommand() {
        return new FoxDownLeftCommand(gameState(),
                foxPutPerformer(), mapPrinter(mapUtil(),
                printWrapper()), printWrapper(),
                gameState().getMapVo(), houndMove());
    }

    @Bean
    Command foxDownRightCommand() {
        return new FoxDownRightCommand(gameState(),
                foxPutPerformer(), mapPrinter(mapUtil(),
                printWrapper()), printWrapper(),
                gameState().getMapVo(), houndMove());
    }

    @Bean
    Command foxUpLeftCommand() {
        return new FoxUpLeftCommand(gameState(),
                foxPutPerformer(), mapPrinter(mapUtil(),
                printWrapper()), printWrapper(),
                gameState().getMapVo(), houndMove());
    }

    @Bean
    Command foxUpRightCommand() {
        return new FoxUpRightCommand(gameState(),
                foxPutPerformer(), mapPrinter(mapUtil(),
                printWrapper()), printWrapper(),
                gameState().getMapVo(), houndMove());
    }

    @Bean
    Command defaultCommand() {
        return new DefaultCommand(printWrapper());
    }

    @Bean
    InputHandler inputHandler() {
        return new InputHandler(List.of(
                printCommand(),
                exitCommand(),
                foxDownLeftCommand(),
                foxDownRightCommand(),
                foxUpLeftCommand(),
                foxUpRightCommand(),
                defaultCommand()
        ));
    }

    @Bean
    MapUtil mapUtil() {
        return new MapUtil();
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInputReader userInputReader, InputHandler inputHandler) {
        return new GameStepPerformer(userInputReader, inputHandler);
    }

    @Bean
    GameController gameController(GameState gameState, MapUtil mapUtil, GameStepPerformer gameStepPerformer) {
        return new GameController(gameState, mapUtil, gameStepPerformer);
    }

    @Bean
    UserInputReader userInputReader() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInputReader(bufferedReader);
    }

    @Bean
    MapPrinter mapPrinter(MapUtil mapUtil, PrintWrapper printWrapper) {
        return new MapPrinter(8, 8, mapUtil, printWrapper);
    }

    @Bean
    FoxPutPerformer foxPutPerformer() {
        return new FoxPutPerformer();
    }

    @Bean
    HoundMove houndMove() {
        return new HoundMove();
    }

    @Bean
    PrintWrapper printWrapper() {
        return new PrintWrapper();
    }

}

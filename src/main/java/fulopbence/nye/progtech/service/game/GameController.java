package fulopbence.nye.progtech.service.game;

import fulopbence.nye.progtech.model.GameState;
import fulopbence.nye.progtech.ui.MapUtil;

/**
 * Controls game cycles.
 */
public class GameController {


    private GameState gameState;
    private MapUtil mapUtil;

    private GameStepPerformer gameStepPerformer;

    public GameController(GameState gameState, MapUtil mapUtil, GameStepPerformer gameStepPerformer) {
        this.gameState = gameState;
        this.mapUtil = mapUtil;
        this.gameStepPerformer = gameStepPerformer;
    }

    /**
     * Commences a game loop.
     */

    public void gameLoop() {

        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
        }
    }

    private boolean isGameInProgress() {
        return !mapUtil.isMapCompleted(gameState.getMapVo()) && !gameState.isUserWantsToExit();

    }
}

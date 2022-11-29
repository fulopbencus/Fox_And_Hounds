package fulopbence.nye.progtech.model;

import java.util.Objects;
/**
 * Current state of the game.
 */
public class GameState {

    private MapVo mapVo;
    private boolean userWantsToExit;

    public GameState(MapVo mapVo, boolean userWantsToExit) {

        this.mapVo = mapVo;
        this.userWantsToExit = userWantsToExit;
    }

    public MapVo getMapVo() {
        return mapVo;
    }

    public void setMapVo(MapVo mapVo) {
        this.mapVo = mapVo;
    }

    public boolean isUserWantsToExit() {
        return userWantsToExit;
    }

    public void setUserWantsToExit(boolean userWantsToExit) {
        this.userWantsToExit = userWantsToExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameState gameState = (GameState) o;
        return userWantsToExit == gameState.userWantsToExit && mapVo.equals(gameState.mapVo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapVo, userWantsToExit);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "mapVo=" + mapVo +
                ", userWantsToExit=" + userWantsToExit +
                '}';
    }
}

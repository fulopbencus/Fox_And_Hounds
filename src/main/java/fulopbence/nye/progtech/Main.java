package fulopbence.nye.progtech;

import fulopbence.nye.progtech.service.game.GameController;
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
package sk.wio;

import sk.wio.service.GameManager;

public class Main {
    public static void main(String[] args) {
        final GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}

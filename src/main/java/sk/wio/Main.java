package sk.wio;

import sk.wio.service.GameManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}

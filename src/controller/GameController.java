package controller;

import model.GameModel;
import view.GameView;

public class GameController {
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.view = view;
        this.model = model;
    }

    public void playerHit() {
        model.playerTakesCard();
        if (model.getPlayer().isBusted()) {
            view.playerBusted(model.getPlayer());
        }
        view.updateHands(model.getPlayer(), model.getDealer());

    }

    public void playerStand() {
        model.dealerTakesCards();
        view.updateHands(model.getPlayer(), model.getDealer());


        String message = model.getWinner();
        view.updateStatus(message);
    }

    public void startNewGame() {
        model.newGame();
        view.updateHands(model.getPlayer(), model.getDealer());
        view.showHitAndStandButton();
    }
}


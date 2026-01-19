package controller;

import model.GameModel;
import view.GameView;

public class GameController {
    private final GameModel model = new GameModel();
    private GameView view;

    public GameController() {
        GameView view = new GameView(this);
//        this.model = model;
    }

    public void playerHit() {
        model.playerTakesCard();
        if (model.getPlayer().isBusted()) {
            view.playerBusted(model.getPlayer());
        }
        if (model.getPlayer().getHandValue() == 21) {
            playerStand();
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
        if (model.getPlayer().getHandValue() == 21) {
            playerStand();
        } else {
            view.updateHands(model.getPlayer(), model.getDealer());
            view.showHitAndStandButton();
        }
    }
}


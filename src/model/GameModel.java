package model;


public class GameModel {
    Deck deck = new Deck();
    private Dealer dealer = new Dealer();
    private Human player = new Human();

    public GameModel() {

    }

    public Dealer getDealer() {
        return dealer;
    }

    public Human getPlayer() {
        return player;
    }

    public void playerTakesCard() {
        player.takeCard(drawCard());
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            deck = new Deck();
        }
        return deck.draw();
    }

    public void newGame() {
        clearHands();
        player.takeCard(drawCard());
        dealer.takeCard(drawCard());
        player.takeCard(drawCard());
        dealer.takeCard(drawCard());
        dealer.setFaceDown(dealer.getHand().get(1));
    }

    public void clearHands() {
        player.clearHand();
        dealer.clearHand();
    }


    public void dealerTakesCards() {
        dealer.getHand().get(1).faceDown = false;
        while (dealer.getHandValue() < 17) {
            dealer.takeCard(drawCard());
        }
    }


    public String getWinner() {
        if (player.isBusted()) return "You Busted!";
        if (dealer.isBusted()) return "Dealer Busted! Player Wins!";
        if (player.getHandValue() > dealer.getHandValue()) return "Player Wins!";
        if (dealer.getHandValue() > player.getHandValue()) return "Dealer Wins";
        return "Tie!";
    }
}



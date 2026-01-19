package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Card> hand = new ArrayList<>();
    int score = 0;

    public Player() {
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandValue() {
        int handValue = 0;
        int aces = 0;
        for (Card c : hand) {
            if (!c.isFaceDown()) {
                if (c.getCardValue() == 11) aces++;
                handValue += c.getCardValue();
            }
        }
        while (handValue > 21 && aces > 0) {
            handValue -= 10;
            aces--;
        }
        return handValue;
    }

    public void clearHand() {
        hand.clear();
    }

    public boolean isBusted() {
        if (getHandValue() > 21) {
            return true;
        } else {
            return false;
        }
    }
}



package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                cards.add(new Card(j, i));
            }
        }
        for (Card c : cards) {
            System.out.println(c.getCardName() + " of " + c.getCardSuit() + " | value: " + c.cardValue);
        }
        Collections.shuffle(cards);
    }

    public List<Card> getcards() {
        return cards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }


    public Card draw() {
        return cards.remove(0);
    }
}


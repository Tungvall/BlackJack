package model;

import view.Suit;

public class Card {
    boolean faceDown = false;
    int cardValue;
    Suit cardSuit;
    String cardName;

    public Card(int cardValue, int cardSuit) {
        this.cardValue = cardValue;
        switch (cardValue) {
            case 1 -> cardName = "A";
            case 11 -> cardName = "J";
            case 12 -> cardName = "Q";
            case 13 -> cardName = "K";
            default -> cardName = "" + cardValue;
        }
        switch (cardSuit) {
            case 1 -> this.cardSuit = Suit.HEARTS;
            case 2 -> this.cardSuit = Suit.DIAMONDS;
            case 3 -> this.cardSuit = Suit.SPADES;
            case 4 -> this.cardSuit = Suit.CLUBS;
        }

    }

    public int getCardValue() {
        if (cardValue >= 10) return 10;
        if (cardValue == 1) return 11;
        return cardValue;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public String getCardName() {
        return cardName;
    }

    public String getIcon() {
        return switch (this.cardSuit) {
            case Suit.HEARTS -> "♥";
            case Suit.DIAMONDS -> "♦";
            case Suit.SPADES -> "♠";
            case Suit.CLUBS -> "♣";
            default -> "";
        };
    }

    public boolean isFaceDown() {
        return faceDown;
    }
}


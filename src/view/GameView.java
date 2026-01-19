package view;

import controller.GameController;
import model.Card;
import model.Dealer;
import model.Human;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    GameController controller;

    private JButton takeCardButton;
    private JButton standButton;
    private JButton newGameButton;

    private JLabel dealerLabel;
    private JLabel playerLabel;
    private JLabel statusLabel;

    private JPanel dealerCardsPanel;
    private JPanel playerCardsPanel;

    public GameView(GameController gameController) {
        setLayout(new BorderLayout());
        setTitle("BlackJack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1200, 800);

        initializeComponents();
        setupLayout();
        setupEventHandlers();

        setVisible(true);
        repaint();
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }


    private void setupEventHandlers() {
        takeCardButton.addActionListener(e -> controller.playerHit());
        standButton.addActionListener(e -> controller.playerStand());
        newGameButton.addActionListener(e -> controller.startNewGame());
    }

    private void initializeComponents() {

        dealerLabel = new JLabel("Dealer: 0", JLabel.CENTER);
        playerLabel = new JLabel("Spelare: 0", JLabel.CENTER);
        statusLabel = new JLabel("Välkommen till Blackjack!", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        dealerCardsPanel = new JPanel();
        dealerCardsPanel.setOpaque(false);
        playerCardsPanel = new JPanel();
        playerCardsPanel.setOpaque(false);

        takeCardButton = new JButton("Ta kort");
        standButton = new JButton("Stanna");
        newGameButton = new JButton("Nytt spel");

        takeCardButton.setEnabled(false);
        standButton.setEnabled(false);
        repaint();
    }

    private void setupLayout() {
        JPanel dealerPanel = new JPanel();
        dealerPanel.add(dealerLabel, BorderLayout.NORTH);
        dealerPanel.add(dealerCardsPanel, BorderLayout.CENTER);

        dealerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Dealer"));
        dealerPanel.setBackground(new Color(184, 219, 128));
        dealerPanel.setPreferredSize(new Dimension(600, 400));

        JPanel playerPanel = new JPanel();
        playerPanel.add(playerLabel, BorderLayout.NORTH);
        playerPanel.add(playerCardsPanel, BorderLayout.CENTER);

        playerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerPanel.setBackground(new Color(184, 219, 128));
        playerPanel.setPreferredSize(new Dimension(600, 300));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(takeCardButton);
        buttonPanel.add(standButton);
        buttonPanel.add(newGameButton);

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        add(dealerPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.add(buttonPanel, BorderLayout.CENTER);
        bottomContainer.add(statusPanel, BorderLayout.SOUTH);

        add(bottomContainer, BorderLayout.SOUTH);

        takeCardButton.setVisible(true);
        standButton.setVisible(true);
        newGameButton.setVisible(true);
    }


    public void updateHands(Human player, Dealer dealer) {
        playerCardsPanel.removeAll();
        dealerCardsPanel.removeAll();
        for (Card c : player.getHand()) {
            JComponent card;
            if (c.isFaceDown()) {
                card = CardFactory.create();
            } else {
                card = CardFactory.create(c);
            }
            playerCardsPanel.add(card);
        }

        for (Card c : dealer.getHand()) {
            JComponent card;
            if (c.isFaceDown()) {
                card = CardFactory.create();
            } else {
                card = CardFactory.create(c);
            }
            dealerCardsPanel.add(card);
        }

        dealerLabel.setText("Poäng: " + dealer.getHandValue());
        playerLabel.setText("Poäng: " + player.getHandValue());

        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();
        dealerCardsPanel.revalidate();
        dealerCardsPanel.repaint();
    }


    public void playerBusted(Human player) {
        statusLabel.setText("Player BUSTED with " + player.getHandValue());
        hideHitAndStandButton();
    }

    public void dealerBusted(Dealer dealer) {
        statusLabel.setText("Dealer BUSTED with " + dealer.getHandValue());
        hideHitAndStandButton();
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
        hideHitAndStandButton();
    }

    public void showHitAndStandButton() {
        takeCardButton.setEnabled(true);
        standButton.setEnabled(true);
        newGameButton.setEnabled(false);
        statusLabel.setText(" ");
    }

    public void hideHitAndStandButton() {
        takeCardButton.setEnabled(false);
        standButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }
}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A frame that allows the user to play Rock, Paper, Scissors against the computer.
 */
public class RockPaperScissorsFrame extends JFrame{

    JPanel mainPanel = new JPanel();
    imageScaler scaler = new imageScaler();

    JTextField numWinsField;
    JTextField numLossesField;
    JTextField numTiesField;

    JTextArea resultsTextArea;

    int numPlayerWins = 0;
    int numComputerWins = 0;
    int numTies = 0;

    ArrayList<Integer> playerMoves = new ArrayList<>();

    /**
     * Constructs a RockPaperScissorsFrame object.
     */
    public RockPaperScissorsFrame() {

        centerFrame();

        setTitle("Rock Paper Scissors");

        mainPanel.setLayout(new BorderLayout());

        buttonsPanel();

        statsPanel();

        resultsPanel();

        add(mainPanel);

    }

    private void centerFrame() {
        // get screen dimensions

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // center frame in screen

        setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        setLocation(screenWidth / 8, screenHeight / 8);
    }

    private void buttonsPanel() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));

        final int ICON_SIZE = 100;

        ImageIcon rockIcon = new ImageIcon(scaler.scaleImage("resources/rock.png", ICON_SIZE, ICON_SIZE));
        ImageIcon paperIcon = new ImageIcon(scaler.scaleImage("resources/paper.png", ICON_SIZE, ICON_SIZE));
        ImageIcon scissorsIcon = new ImageIcon(scaler.scaleImage("resources/scissors.png", ICON_SIZE, ICON_SIZE));

        JButton rockButton = new JButton(rockIcon);
        JButton paperButton = new JButton(paperIcon);
        JButton scissorsButton = new JButton(scissorsIcon);
        
        rockButton.addActionListener(e -> playMove("R"));
        paperButton.addActionListener(e -> playMove("P"));
        scissorsButton.addActionListener(e -> playMove("S"));

        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);

        Border border = BorderFactory.createLineBorder(Color.GRAY, 4);
        buttonsPanel.setBorder(border);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

    }

    private void statsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 3));

        JLabel playerWinsLabel = new JLabel("Wins", JLabel.CENTER);
        JLabel computerWinsLabel = new JLabel("Losses", JLabel.CENTER);
        JLabel tiesLabel = new JLabel("Ties", JLabel.CENTER);

        numWinsField = new JTextField("0",5);
        numWinsField.setEditable(false);
        numLossesField = new JTextField("0", 5);
        numLossesField.setEditable(false);
        numTiesField = new JTextField("0",5);
        numTiesField.setEditable(false);

        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);
        statsPanel.add(numWinsField);
        statsPanel.add(numLossesField);
        statsPanel.add(numTiesField);

        mainPanel.add(statsPanel, BorderLayout.NORTH);
    }

    private void resultsPanel() {

        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setLineWrap(true);
        resultsTextArea.setWrapStyleWord(true);
        resultsTextArea.setEditable(false);
        resultsTextArea.setFont(new Font("Avenir", Font.PLAIN, 14));

        JScrollPane resultsScrollPane = new JScrollPane(resultsTextArea);

        mainPanel.add(resultsScrollPane, BorderLayout.CENTER);
    }

    private void playMove(String move) {
        final int ROCK = 0;
        final int PAPER = 1;
        final int SCISSORS = 2;

        int playerMove;

        if (move.equals("R")) {
            playerMove = ROCK;
        } else if (move.equals("P")) {
            playerMove = PAPER;
        } else {
            playerMove = SCISSORS;
        }

        Strategy strategy = Strategies.returnRandomStrategy(.05);
        int computerMove = strategy.DetermineMove(playerMove, playerMoves);

        String winningOutput;
        String winner;

        if (playerMove == computerMove) {
            if (playerMove == ROCK) {
                winningOutput = "Rock and rock tie (Tie)";
            } else if (playerMove == PAPER) {
                winningOutput = "Paper and paper tie (Tie)";
            } else {
                winningOutput = "Scissors and scissors tie (Tie)";
            }
            winner = "None";
        } else if (playerMove == ROCK || computerMove == ROCK) {
            if (playerMove == SCISSORS || computerMove == SCISSORS) {
                if (playerMove == ROCK) {
                    winningOutput = "Rock breaks scissors (Player wins)";
                    winner = "Player";
                } else {
                    winningOutput = "Rock breaks scissors (Computer wins)";
                    winner = "Computer";
                }
            } else {
                if (playerMove == PAPER) {
                    winningOutput = "Paper covers rock (Player wins)";
                    winner = "Player";
                } else {
                    winningOutput = "Paper covers rock (Computer wins)";
                    winner = "Computer";
                }
            }
        } else {
            if (playerMove == SCISSORS) {
                winningOutput = "Scissors cuts paper (Player wins)";
                winner = "Player";
            } else {
                winningOutput = "Scissors cuts paper (Computer wins)";
                winner = "Computer";
            }
        }

        resultsTextArea.append(winningOutput + " CPU Strategy: " + strategy.getName() + "\n");

        if (winner.equals("Player")) {
            numPlayerWins++;
            numWinsField.setText(String.valueOf(numPlayerWins));
        } else if (winner.equals("Computer")) {
            numComputerWins++;
            numLossesField.setText(String.valueOf(numComputerWins));
        } else {
            numTies++;
            numTiesField.setText(String.valueOf(numTies));
        }

        playerMoves.add(playerMove);
    }

}

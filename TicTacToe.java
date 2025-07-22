import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        // Game board
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));
        Font buttonFont = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(buttonFont);
                buttons[i][j].addActionListener(this);
                board.add(buttons[i][j]);
            }
        }

        add(board, BorderLayout.CENTER);

        // Reset button
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return; // Ignore if already marked
        }

        clicked.setText(playerXTurn ? "X" : "O");
        clicked.setForeground(playerXTurn ? Color.BLUE : Color.RED);

        if (checkWin()) {
            statusLabel.setText("Player " + (playerXTurn ? "X" : "O") + " wins!");
            disableButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            playerXTurn = !playerXTurn;
            statusLabel.setText("Player " + (playerXTurn ? "X" : "O") + "'s turn");
        }
    }

    // Check for a win condition
    private boolean checkWin() {
        String playerSymbol = playerXTurn ? "X" : "O";

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (playerSymbol.equals(buttons[i][0].getText()) &&
                playerSymbol.equals(buttons[i][1].getText()) &&
                playerSymbol.equals(buttons[i][2].getText())) {
                return true;
            }
            if (playerSymbol.equals(buttons[0][i].getText()) &&
                playerSymbol.equals(buttons[1][i].getText()) &&
                playerSymbol.equals(buttons[2][i].getText())) {
                return true;
            }
        }

        if (playerSymbol.equals(buttons[0][0].getText()) &&
            playerSymbol.equals(buttons[1][1].getText()) &&
            playerSymbol.equals(buttons[2][2].getText())) {
            return true;
        }

        if (playerSymbol.equals(buttons[0][2].getText()) &&
            playerSymbol.equals(buttons[1][1].getText()) &&
            playerSymbol.equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    // Check for a draw
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false; // Empty spot found, not a draw
                }
            }
        }
        return true;
    }

    // Disable all buttons after game ends
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    // Reset the game
    private void resetGame() {
        playerXTurn = true;
        statusLabel.setText("Player X's turn");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}

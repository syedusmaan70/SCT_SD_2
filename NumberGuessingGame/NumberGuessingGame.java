import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private int randomNumber;
    private int attempts;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {

        setTitle("Number Guessing Game");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        startNewGame();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instructionLabel =
                new JLabel("Guess a number between 1 and 100");
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessField = new JTextField();
        guessField.setMaximumSize(new Dimension(250, 40));
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        guessField.setHorizontalAlignment(JTextField.CENTER);

        JButton guessButton = new JButton("Guess");
        guessButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageLabel = new JLabel("Enter your guess!");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        guessButton.addActionListener(e -> checkGuess());

        newGameButton.addActionListener(e -> {
            startNewGame();
            guessField.setText("");
            messageLabel.setText("Enter your guess!");
            attemptsLabel.setText("Attempts: 0");
            guessField.setEnabled(true);
            guessField.requestFocus();
        });

        guessField.addActionListener(e -> checkGuess());

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(instructionLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(guessField);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(guessButton);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(messageLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(attemptsLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(newGameButton);

        add(mainPanel);

        setVisible(true);
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void checkGuess() {

        String input = guessField.getText().trim();

        if (input.isEmpty()) {
            messageLabel.setText("Please enter a number.");
            return;
        }

        try {

            int guess = Integer.parseInt(input);

            if (guess < 1 || guess > 100) {
                messageLabel.setText(
                        "Enter a number between 1 and 100."
                );
                return;
            }

            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < randomNumber) {
                messageLabel.setText("Too Low! Try again.");
            }
            else if (guess > randomNumber) {
                messageLabel.setText("Too High! Try again.");
            }
            else {
                messageLabel.setText(
                        "Correct! You guessed it in "
                        + attempts + " attempts!"
                );

                guessField.setEnabled(false);
            }

        } catch (NumberFormatException e) {

            messageLabel.setText(
                    "Please enter a valid number."
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new NumberGuessingGame()
        );
    }
}
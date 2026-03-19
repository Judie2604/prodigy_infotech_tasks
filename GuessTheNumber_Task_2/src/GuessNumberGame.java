import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class GuessNumberGame extends JFrame {

    private int randomNumber;
    private int attempts = 0;

    private JTextField guessField;
    private JLabel resultLabel;
    private JLabel attemptsLabel;

    private ConfettiOverlay confetti;

    public GuessNumberGame() {

        setTitle("🎯 Guess The Number Game");
        setSize(400,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        randomNumber = new Random().nextInt(100) + 1;

        JLabel title = new JLabel("Guess a number between 1 and 100",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,16));

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        JButton guessButton = new JButton("Guess 🚀");

        resultLabel = new JLabel("Enter your guess!",SwingConstants.CENTER);
        attemptsLabel = new JLabel("Attempts: 0",SwingConstants.CENTER);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5,1,10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        mainPanel.add(title);
        mainPanel.add(guessField);
        mainPanel.add(guessButton);
        mainPanel.add(resultLabel);
        mainPanel.add(attemptsLabel);

        add(mainPanel, BorderLayout.CENTER);
        guessButton.addActionListener(e -> checkGuess());

        // confetti overlay
        confetti = new ConfettiOverlay();
        setGlassPane(confetti);

        setVisible(true);
    }

    private void checkGuess() {

        try {

            int guess = Integer.parseInt(guessField.getText());
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if(guess < randomNumber){
                resultLabel.setText("Too Low 📉");
            }

            else if(guess > randomNumber){
                resultLabel.setText("Too High 📈");
            }

            else{
                resultLabel.setText("Correct! 🎉");
            }

        }

        catch(Exception e){
            resultLabel.setText("⚠ Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new GuessNumberGame();
    }
}

class ConfettiOverlay extends JPanel {

    private int[] x = new int[120];
    private int[] y = new int[120];
    private final Color[] colors = new Color[120];

    private Timer timer;
    private Random rand = new Random();

    public ConfettiOverlay(){

        setOpaque(false);

        for(int i=0;i<x.length;i++){
            x[i] = rand.nextInt(400);
            y[i] = -rand.nextInt(400);
            colors[i] = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
        }

        timer = new Timer(40,e->{

            for(int i=0;i<x.length;i++){
                y[i] += 6;
            }

            repaint();
        });
    }

    // public void start(){

    //     setVisible(true);
    //     timer.start();

    //     // stop after 2 seconds
    //     new Timer(2000,e->{
    //         timer.stop();
    //         setVisible(false);
    //     }).start();
    // }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        for(int i=0;i<x.length;i++){
            g.setColor(colors[i]);
            g.fillOval(x[i],y[i],8,8);
        }
    }
}
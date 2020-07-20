package life;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLife extends JFrame {
    protected JLabel GenerationLabel;
    protected JLabel AliveLabel;
    protected JLifeTable game;
    protected RandomTable rn;
    protected JToggleButton pauseToggle;
    protected JButton restartButton;
    protected volatile boolean pause = false;
    protected volatile boolean restart = false;
    protected int seed = 0;

    public GameOfLife() {
        super("Life Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        GenerationLabel = new JLabel("GenerationLabel");
        GenerationLabel.setName("GenerationLabel");

        AliveLabel = new JLabel("AliveLabel");
        AliveLabel.setName("AliveLabel");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
        pauseToggle = new JToggleButton("Pause");
        pauseToggle.setName("PlayToggleButton");
        restartButton = new JButton("Reset");
        restartButton.setName("ResetButton");

        buttonsPanel.add(pauseToggle);
        buttonsPanel.add(restartButton);

        pauseToggle.addActionListener(e ->pause = pauseToggle.isSelected());
        restartButton.addActionListener(e -> restart = !restart);

        JPanel messages = new JPanel();
        messages.add(buttonsPanel);
        messages.add(GenerationLabel);
        messages.add(AliveLabel);
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));

        add(messages,BorderLayout.WEST);

        seed = new Random().nextInt(500);
        rn = new RandomTable(30,30,'O',' ');
        rn.setRandomSeed(seed);
        rn.setAlgorithm(new NormalAlgorithm());

        game = new JLifeTable();
        game.setSize(300,300);
        game.setTable(rn);
        add(game, BorderLayout.CENTER);
        setVisible(true);

        AliveLabel.setText(String.format("Alive: %d",rn.getNumberOfAliveCells()));
        GenerationLabel.setText(String.format("Generation #0"));
        start();

    }

    private void start() {

        Thread worker = new Thread() {
            public void run() {

                for (int i = 1; i <= 50; i++) {
                    if (restart) {
                        rn.setRandomSeed(seed);
                        i = 1;
                    }
                    rn.nextGeneration();
                    AliveLabel.setText(String.format("Alive: %d",rn.getNumberOfAliveCells()));
                    GenerationLabel.setText(String.format("Generation #%d",i));
                    repaint();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (pause);
                }
            }
        };
        worker.start();

    }

    @Override
    public void repaint(long time, int x, int y, int width, int height) {
        super.repaint(time, x, y, width, height);
        this.game.repaint();
    }
}


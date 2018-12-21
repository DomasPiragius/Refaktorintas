package pkg2048;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;
import java.net.URL;
import java.util.Objects;

public class GUI {

    private Game game;

    private Font largeFeedbackFont = new Font("Sanserif", Font.PLAIN, 40);
    private Font smallFeedbackFont = new Font("Sanserif", Font.PLAIN, 20);

    private JLabel scoreLabel;

    private Map<Integer, ImageIcon> numbers;
    private gameboard l = new gameboard();

    private MyFrame frame;

    public GUI() {
        int width = 328;
        int height = 424;
        int margin_width = 16;
        int margin_height = 296;

        game = new Game();
        frame = new MyFrame();
        frame.setFocusable(true);
        frame.addKeyListener(new MyFrame());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadNumbers();
        JPanel upPanel = new JPanel();
        upPanel.setLayout(new GridLayout());
        upPanel.setPreferredSize(new Dimension(width, 112));

        JLabel title = new JLabel("2048", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        upPanel.add(title);

        scoreLabel = new JLabel("<html>Score:<br>0</html>", SwingConstants.CENTER);
        upPanel.add(scoreLabel);
        upPanel.add(new JLabel("<html>High Score:<br>224450</html>", SwingConstants.CENTER));
        Color color = new Color(238, 207, 84);
        upPanel.setBackground(color);

        JPanel left_margin = new JPanel();
        left_margin.setPreferredSize(new Dimension(margin_width, margin_height));
        left_margin.setBackground(color);

        JPanel right_margin = new JPanel();
        right_margin.setPreferredSize(new Dimension(margin_width, margin_height));
        right_margin.setBackground(color);

        JPanel bottom_margin = new JPanel();
        bottom_margin.setPreferredSize(new Dimension(width, margin_width));
        bottom_margin.setBackground(color);

        frame.getContentPane().add(upPanel, BorderLayout.NORTH);
        frame.getContentPane().add(left_margin, BorderLayout.WEST);
        frame.getContentPane().add(right_margin, BorderLayout.EAST);
        frame.getContentPane().add(bottom_margin, BorderLayout.SOUTH);
        frame.getContentPane().add(l, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
    }

    private void loadNumbers() {
        numbers = new Hashtable<>();
        ClassLoader cldr = this.getClass().getClassLoader();
        URL url0000 = cldr.getResource("images/0.png");
        URL url0002 = cldr.getResource("images/2.png");
        URL url0004 = cldr.getResource("images/4.png");
        URL url0008 = cldr.getResource("images/8.png");
        URL url0016 = cldr.getResource("images/16.png");
        URL url0032 = cldr.getResource("images/32.png");
        URL url0064 = cldr.getResource("images/64.png");
        URL url0128 = cldr.getResource("images/128.png");
        URL url0256 = cldr.getResource("images/256.png");
        URL url0512 = cldr.getResource("images/512.png");
        URL url1024 = cldr.getResource("images/1024.png");
        URL url2048 = cldr.getResource("images/2048.png");
        numbers.put(0, new ImageIcon(Objects.requireNonNull(url0000)));
        numbers.put(2, new ImageIcon(Objects.requireNonNull(url0002)));
        numbers.put(4, new ImageIcon(Objects.requireNonNull(url0004)));
        numbers.put(8, new ImageIcon(Objects.requireNonNull(url0008)));
        numbers.put(16, new ImageIcon(Objects.requireNonNull(url0016)));
        numbers.put(32, new ImageIcon(Objects.requireNonNull(url0032)));
        numbers.put(64, new ImageIcon(Objects.requireNonNull(url0064)));
        numbers.put(128, new ImageIcon(Objects.requireNonNull(url0128)));
        numbers.put(256, new ImageIcon(Objects.requireNonNull(url0256)));
        numbers.put(512, new ImageIcon(Objects.requireNonNull(url0512)));
        numbers.put(1024, new ImageIcon(Objects.requireNonNull(url1024)));
        numbers.put(2048, new ImageIcon(Objects.requireNonNull(url2048)));
    }

    class gameboard extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            int[][] board = game.getTable();
            for (int y = 1; y < 5; y++) {
                for (int x = 1; x < 5; x++) {
                    int X = (8 * x) + (64 * (x - 1));
                    int Y = (8 * y) + (64 * (y - 1));
                    int thisNumber = board[y - 1][x - 1];

                    if (numbers.containsKey(thisNumber)) {
                        ImageIcon thisTile = numbers.get(thisNumber);
                        thisTile.paintIcon(this, g, X, Y);
                    }
                }
            }
        }
    }

    class winScreen extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(0, 120, 0));
            g.drawString("You won!", 20, 50);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255, 255, 255));
            g.drawString("Press Enter", 20, 70);
        }
    }

    class loseScreen extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setFont(largeFeedbackFont);
            g.setColor(new Color(80, 0, 0));
            g.drawString("You lost..", 20, 50);
            g.setFont(smallFeedbackFont);
            g.setColor(new Color(255, 255, 255));
            g.drawString("Press Enter", 20, 90);
        }
    }

    class MyFrame extends JFrame implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (game.getState() == State.CONTINUE) {
                if (key == KeyEvent.VK_UP) {
                    game.move("UP");
                    game.addNumber();
                    game.updateState();
                    l.repaint();
                    updateScore();
                } else if (key == KeyEvent.VK_DOWN) {
                    game.move("DOWN");
                    game.addNumber();
                    game.updateState();
                    l.repaint();
                    updateScore();
                } else if (key == KeyEvent.VK_LEFT) {
                    game.move("LEFT");
                    game.addNumber();
                    game.updateState();
                    l.repaint();
                    updateScore();
                } else if (key == KeyEvent.VK_RIGHT) {
                    game.move("RIGHT");
                    game.addNumber();
                    game.updateState();
                    updateScore();
                    l.repaint();
                }
                State b = game.getState();
                if (b == State.LOSE) {
                    frame.getContentPane().remove(l);
                    frame.getContentPane().add(new loseScreen(), BorderLayout.CENTER);
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();
                } else if (b == State.WIN) {
                    frame.getContentPane().remove(l);
                    frame.getContentPane().add(new winScreen(), BorderLayout.CENTER);
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();
                }
            } else {
                if (key == KeyEvent.VK_ENTER) {
                    game = new Game();
                    frame.remove(((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER));
                    frame.getContentPane().add(l);
                    l.repaint();
                    frame.getContentPane().invalidate();
                    frame.getContentPane().validate();
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    private void updateScore() {
        scoreLabel.setText("<html>Score:<br>" + game.getScore() + "<html>");
    }
}

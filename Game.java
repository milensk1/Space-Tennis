
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {
    private BufferedImage background;

    Ball ball = new Ball(this);
    Player player = new Player(this);
    int speed = 1;

    private int getScore() {
        return speed - 1;

    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }
        });
        setFocusable(true);
        Sound.BACK.loop();
    }

    private void move() {
        ball.move();
        player.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            background = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(background, 0, 0, 500, 700, null);
        ball.paint(g2d);
        player.paint(g2d);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Calibri",Font.BOLD, 17));
        g2d.drawString(String.valueOf(getScore()), 466, 18);
        g2d.drawString(String.valueOf(Score.HighestScoreRead()), 93, 19);
    }

    public void gameOver() {
        Sound.BACK.stop();
        Sound.GAMEOVER.play();
        if (getScore() > Score.HighestScoreRead()){
            Score.HighestScoreWrite(getScore());
        }

        int confirmDialogValue =JOptionPane.showConfirmDialog(this, "Your score is: " + getScore()+ "\nDo you want to try again?",
                "The Earth is destroyed! :( ", JOptionPane.YES_OPTION ,JOptionPane.NO_OPTION);

        System.out.println(confirmDialogValue);
        if (confirmDialogValue == 0) {
           try {
               Sound.GAMEOVER.stop();
               this.startGame();
           } catch (InterruptedException i){
               i.printStackTrace();
           }

        }
        System.exit(0);
    }

    private void startGame()  throws InterruptedException  {
        JFrame frame = new JFrame("Space Tennis");
        Game game = new Game();
        frame.add(game);
        frame.setSize(500, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        game.startGame();

    }
}
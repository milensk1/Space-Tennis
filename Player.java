import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private BufferedImage player;
    private static final int Y = 610;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    int x = 200;
    int xa = 0;
    private static String displayNow = "playerFrame1Flames.png";
    private static String displayNext = "playerFrame2Flames.png";
    private Game game;

    public Player(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        //g.fillRect(x, Y, WIDTH, HEIGHT);
        String temp = "";
        try {
            player = ImageIO.read(new File(displayNow));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(player, x, Y, WIDTH, HEIGHT, null);

        temp = displayNow;
        displayNow = displayNext;
        displayNext = temp;
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -game.speed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = game.speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y - HEIGHT;
    }
}
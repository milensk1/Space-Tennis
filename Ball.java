import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ball {
	private static final int ALIEN_WIDTH = 58;
	private static final int ALIEN_HEIGHT = 29;
	private BufferedImage ball;

	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - ALIEN_WIDTH)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - ALIEN_HEIGHT)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			y = game.player.getTopY() - ALIEN_WIDTH;
			game.speed++;
			Sound.BALL.play();
		} else
			changeDirection = false;


		if (changeDirection)
			Sound.WALLHIT.play();
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.player.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		try {
			ball = ImageIO.read(new File("alien.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(ball, x, y, ALIEN_WIDTH, ALIEN_HEIGHT, null);
		//.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
	}
}
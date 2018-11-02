package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mark 
 * Russo New enemy ghost to be implemented in level 5 
 * November 28, 2017
 */

public class EnemyBossGhost extends GameObject {

	// Instance Variables
	private Handler handler;
	private int timer;
	private Random r = new Random();
	private Image img;
	private int speed;
	private GameObject player;
	private double bulletVelX;
	private double bulletVelY;

	public EnemyBossGhost(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		this.velX = 0;
		this.velY = 0;
		this.timer = 60;
		img = getImage("images/EnemyGhost.png");

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	// Allows the ghost to follow the player on the screen
	public void tick() {
		this.x += velX;
		this.y += velY;
		double diffX = this.x - player.getX() - 10;
		double diffY = this.y - player.getY() - 10;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		velX = ((this.speed / distance) * diffX); // numerator affects speed of
													// enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of
													// enemy

		timer--;
		if (timer <= 0) {
			leaveTrail();
			timer = 10;

		}

	}

	// Leaves the trail of the enemy boss ghost
	// trail is creates in the EnemyBossGhostTrail class
	public void leaveTrail() {
		handler.addObject(
				new EnemyBossGhostTrail(this.x, this.y, bulletVelX, bulletVelY, ID.EnemyBossGhostTrail, this.handler));
	}

	// Renders the ghost image that was imported
	public void render(Graphics g) {
		g.drawImage(img, (int) this.x, (int) this.y, 96, 96, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(16), (int) Game.scaleY(16));
	}

	// Gets the ghost image
	public Image getImage(String path) {
		Image image = null;
		try {
			URL imageURL = Game.class.getResource(path);
			image = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return image;
	}

}

package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySmarter extends GameObject {

	private Handler handler;
	private GameObject player;
	private int speed;
	private int tickCount;
	private Random r = new Random();

	public EnemySmarter(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	public void tick() {
		this.x += velX;
		this.y += velY;
		tickCount += 1;
		////////////////////////////// pythagorean theorem
		////////////////////////////// below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy

		if (tickCount > 100 && tickCount < 150) {
			velX = 0;
			velY = 0;
		}
		if (tickCount == 151) {
			this.x = player.getX() + (r.nextInt(10) + 50);
			this.y = player.getY() + (r.nextInt(20) + 50);
			tickCount = 0;
		}
	}

	public void render(Graphics g) {
		Color purple = new Color(148,0,211);
		g.setColor(purple);
		g.fillRect((int) x, (int) y, (int) Game.scaleX(32), (int) Game.scaleY(32));

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(32), (int) Game.scaleY(32));
	}

}

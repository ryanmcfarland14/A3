package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemyShotgunBullet extends GameObject {

	private Handler handler;

	public EnemyShotgunBullet(double x, double y, double velX, double velY, ID id, Handler handler) {
		super(x, y, id);
		this.x = x; // Same as with the trails: ignore scaling of GameObjects.
		this.y = y;
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;


		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 4, 4, 0.025, this.handler));

		removeBullets();
	}

	public void removeBullets() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.EnemyShotgunBullet) {
				if (tempObject.getX() >= Game.WIDTH || tempObject.getX() <= 0) {
					handler.removeObject(tempObject);
				}
				if (tempObject.getY() >= Game.HEIGHT || tempObject.getY() <= 0) {
					handler.removeObject(tempObject);
				}
			}

		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 4, 4);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 4, 4);
	}

}

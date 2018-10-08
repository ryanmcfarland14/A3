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

public class EnemyBurst extends GameObject {

	private Handler handler;
	private int timer;
	private int size;
	private String side;
	private boolean isMoving = false;
	private Random r = new Random();

	public EnemyBurst(double x, double y, double velX, double velY, int size, String side, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		this.timer = 60;
		this.side = side;
		this.size = size;
		setPos();
		setVel();
	}

	public void tick() {
		if (isMoving == false) {
			isMoving = true;
			if (this.side.equals("left")) {
				handler.object.add(
						new EnemyBurstWarning(
								0, 0, (int) Game.scaleX(25), Game.HEIGHT, 
								ID.EnemyBurstWarning, handler));
			} else if (this.side.equals("right")) {
				handler.object.add(
						new EnemyBurstWarning(
								1920 - 45, 0, (int) Game.scaleX(25), Game.HEIGHT, 
								ID.EnemyBurstWarning, handler));
			} else if (this.side.equals("top")) {
				handler.object.add(
						new EnemyBurstWarning(
								0, 90, Game.WIDTH, (int) Game.scaleY(25), 
								ID.EnemyBurstWarning, handler));
			} else if (this.side.equals("bottom")) {
				handler.object	.add(
						new EnemyBurstWarning(
								0, 1080 - 85, Game.WIDTH, (int) Game.scaleY(25), 
								ID.EnemyBurstWarning, handler));
			}
		}
		
		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.orange, this.size, this.size, 0.025, this.handler));

		timer--;
		if (timer <= 0) {
			this.x += velX;
			this.y += velY;

		}
		if (this.side.equals("left") && this.x > Game.WIDTH) {
			handler.removeObject(this);
		}
		if (this.side.equals("right") && this.x < 0) {
			handler.removeObject(this);
		}
		if (this.side.equals("top") && this.y > Game.HEIGHT) {
			handler.removeObject(this);
		}
		if (this.side.equals("bottom") && this.y < 0) {
			handler.removeObject(this);
		}
	}

	public void setPos() {
		if (this.side.equals("left")) {
			this.x = -(size);
			this.y = r.nextInt(((Game.HEIGHT - size) - 0) + 1) + 0;
		} else if (this.side.equals("right")) {
			this.x = Game.WIDTH + 200;
			this.y = r.nextInt(((Game.HEIGHT - size) - 0) + 1) + 0;

		} else if (this.side.equals("top")) {
			this.y = -(size);
			this.x = r.nextInt(((Game.WIDTH - size) - 0) + 1) + 0;

		} else if (this.side.equals("bottom")) {
			this.y = Game.HEIGHT + 200;
			this.x = r.nextInt(((Game.WIDTH - size) - 0) + 1) + 0;

		}
	}

	public void setVel() {
		if (this.side.equals("left")) {
			this.velY = 0;
		} else if (this.side.equals("right")) {
			this.velX = -(this.velX);
			this.velY = 0;

		} else if (this.side.equals("top")) {
			this.velX = 0;

		} else if (this.side.equals("bottom")) {
			this.velX = 0;
			this.velY = -(this.velY);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x, (int) y, this.size, this.size);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 200, 200);
	}
	
	@Override
	public String toString() {
		return id.toString();
	}
}

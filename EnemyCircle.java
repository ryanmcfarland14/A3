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

public class EnemyCircle extends GameObject {

	private Handler handler;
	private int size;

	public EnemyCircle(double x, double y, int velX, int velY, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		size = 20;
	}

	public void tick() {
		this.x += velX;
		this.y += velY;
		if(size<300) {
			size += 1;
		}
		
		if (this.y <= 90 || this.y >= Game.HEIGHT - size - 60) {
			velY *= -1;
		}
		if (this.x <= 0 || this.x >= Game.WIDTH - size) {
			velX *= -1;
		}
		
		if(size==300) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) x, (int) y, (int) Game.scaleX(size), (int) Game.scaleY(size));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, (int) Game.scaleX(size), (int) Game.scaleY(size));
	}

}

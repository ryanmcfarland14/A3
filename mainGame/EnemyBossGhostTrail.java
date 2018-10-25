package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBossGhostTrail extends GameObject {

	private Handler handler;

	public EnemyBossGhostTrail(double x, double y, double velX, double velY, ID id, Handler handler) {
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

		handler.addObject(new Trail(x, y, ID.Trail, Color.getColor(createRandomColor()), 10, 10, 0.025, this.handler));

		removeBullets();
	}

	public void removeBullets() {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.EnemyBossGhostTrail) {
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

	private static String createRandomColor() {
		  int R = (int)(Math.random()*256);
		  int G = (int)(Math.random()*256);
		  int B = (int)(Math.random()*256);
	    Color color = new Color(R, G, B);
	    return null;
	}

	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}

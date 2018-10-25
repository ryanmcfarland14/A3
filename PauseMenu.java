package mainGame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.net.URL;

import mainGame.Game.STATE;

public class PauseMenu {
	private Game game;
	private Handler handler;
	private HUD hud;
	private int timer;
	private int tempCounter;
	private LevelText pausePrompt;

	public PauseMenu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		tempCounter = 0;
		pausePrompt = new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Press P to resume",
				ID.Levels1to10Text);

	}

	public void tick() {
		timer--;
		if (timer <= 0) {
			handler.object.clear();
			timer = 300;
		}
		handler.tick();
	}

	public void render(Graphics g) {
		if (game.gameState==STATE.PauseMenu) {
			if (tempCounter < 1) {
				handler.addObject(pausePrompt);
				tempCounter++;
			}
			handler.render(g);
		}
	}
	
	public void removePrompt() {
		handler.removeObject(pausePrompt);
		tempCounter=0;
	}
	

}

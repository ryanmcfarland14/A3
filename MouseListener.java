package mainGame;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import mainGame.Game.STATE;

/**
 * Handles all mouse input
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class MouseListener extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn1to10 spawner;
	private Spawn10to20 spawner2;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player;
	private String upgradeText;

	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to10 spawner, Spawn10to20 spawner2,
			UpgradeScreen upgradeScreen, Player player, Upgrades upgrades) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		this.spawner2 = spawner2;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.upgrades = upgrades;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (game.gameState == STATE.GameOver) {

			if (game.previousGameState == STATE.Game) {
				handler.object.clear();
				upgrades.resetUpgrades();
				player.initialize();
				hud.setLevel(1);
				spawner.restart();
				spawner2.restart();
	  			spawner2.addLevels();
	  			handler.object.clear();
	  			Spawn1to10.LEVEL_SET = 1;
	  			game.gameState = STATE.Game;
	  			 handler.addObject(player);
			} else if (game.previousGameState == STATE.Survival) {
				handler.object.clear();
				player.initialize();
				hud.setLevel(1);
				game.getSurvival().initialize();
				game.gameState = STATE.Survival;
				handler.addObject(player);
			}
		}

		else if (game.gameState == STATE.Game) {

		}

		else if (game.gameState == STATE.Upgrade) {
			if (mouseOver(mx, my, 100, 300, 1721, 174)) {
				upgradeText = upgradeScreen.getPath(1);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(1);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, 100, 300 + (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(2);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(2);

				game.gameState = STATE.Game;
			} else if (mouseOver(mx, my, 100, 300 + 2 * (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(3);

				upgrades.activateUpgrade(upgradeText);

				upgradeScreen.removeUpgradeOption(3);

				game.gameState = STATE.Game;
			}

		}

		else if (game.gameState == STATE.Menu) {
			// Waves Button
			if (mouseOver(mx, my, 805, 545, 300, 55)) {
				handler.object.clear();
				game.gameState = STATE.Game;
				player.initialize();
				handler.addObject(player);
				Sound.stopSoundMenu();
				Sound.playSoundWaves();
			}

			// Survival Button
			if (mouseOver(mx, my, 805, 610, 300, 55)) {
				handler.object.clear();
				game.gameState = STATE.Survival;

				player.initialize();
				game.getSurvival().initialize();
				handler.addObject(player);
        
        Sound.playButtonPress();
				Sound.stopSoundMenu();
				Sound.playSoundSurvival();
			}

			// Help Button
			else if (mouseOver(mx, my, 805, 740, 300, 55)) {
				// game.gameState = STATE.Help;
        Sound.playButtonPress();
        
				JOptionPane.showMessageDialog(game,
						"How To Play: To play, Waves, you must first understand that you are playing" + " \n"
								+ " as the small white box in the center of the screen, with the purpose to try to "
								+ " \n"
								+ "stay alive as long as possible while dodging enemies. To start avoiding enemies,"
								+ " \n" + " you simply use the keys, â€œW-A-S-Dâ€� to navigate the page.",
						"Help Menu", JOptionPane.INFORMATION_MESSAGE);
			}

			// Credits
			else if (mouseOver(mx, my, 805, 805, 300, 55)) {
				Sound.playButtonPress();
				JOptionPane.showMessageDialog(game,
						"Made by Brandon Loehle for his "
								+ "final project in AP Computer Science senior year, 2015 - 2016."
								+ "\n\nThis game is grossly unfinished with minor bugs. However,"
								+ " it is 100% playable, enjoy!");
			}

			// Quit Button
			else if (mouseOver(mx, my, 805, 870, 300, 55)) {
				System.exit(1);
			}
			
			//Leaderboard Button
			else if (mouseOver(mx, my, 805, 675, 300, 55)) {
				game.gameState=STATE.Leaderboard;
				Sound.playButtonPress();
			}
		}

		// Back Button for Help screen
		else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 850, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via
	 * Graphics
	 * 
	 * @param mx
	 *            mouse x position
	 * @param my
	 *            mouse y position
	 * @param x
	 *            button x position
	 * @param y
	 *            button y position
	 * @param width
	 *            button width
	 * @param height
	 *            button height
	 * @return boolean, true if the mouse is contained within the button
	 */
	private boolean mouseOver(int mx, int my, int x, int y, 
			int width, int height) {
		//mx = (int) (mx * (Game.WIDTH / 1920f));
		x = (int) (x * (Game.WIDTH / 1920f));
		width = (int) (width * (Game.WIDTH / 1920f));
		//my = (int) (my * (Game.HEIGHT / 1080f));
		y = (int) (y * (Game.HEIGHT / 1080f));
		height = (int) (height * (Game.HEIGHT / 1080f));
		System.out.println("" + mx + " " + x + " " + 
				width + " " + my + " " + y + " " + height);
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}

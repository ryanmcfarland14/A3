package mainGame;

import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * This class closely resembles Spawn1to10. Please refer to that class for
 * documentation
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Spawn10to20 {

	private Handler handler;
	private HUD hud;
	private Game game;
	private int temp = 0;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private int onScreenTimer; // used to make the level text disappear off the
								// screen
	private int levelsRemaining;

	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>();
	private int index;
	private int levelNumber = 0;
	private int tempCounter = 0;
	private LevelText levelString;
	public static int LEVEL_SET_2_RESET = 0;

	public Spawn10to20(Handler handler, HUD hud, Spawn1to10 spawner, Game game) {
		restart();
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		spawnTimer = 10;
		levelTimer = 150;
		onScreenTimer = 100;
		levelsRemaining = 9;
		hud.setLevel(1);
		tempCounter = 0;
		addLevels();
		index = 0;
		levelNumber = 11;
		levelString = new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Level " + levelNumber,
				ID.Levels1to10Text);
	}

	public void addLevels() {
		for (int i = 1; i <= 10; i++) {
			levels.add(i);
		}
	}

	public void tick() {
		if (levelNumber == 11) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(new EnemyCircle(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 13, 13, ID.EnemyCircle,
						handler));
				spawnTimer = 80;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 12) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 30) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 2, ID.EnemySweep, handler));
			} else if (spawnTimer == 20) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -2, ID.EnemySweep, handler));
			} else if (spawnTimer == 10) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 4, ID.EnemySweep, handler));
			} else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -4, ID.EnemySweep, handler));
				spawnTimer = 45;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 13) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmarter(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -5, ID.EnemySmarter, handler));
				spawnTimer = 100;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				spawnTimer = 10;
				tempCounter = 0;

				levelNumber += 1;
				levelsRemaining -= 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) {
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 14) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 100, 100,
						-30, ID.EnemyShooter, this.handler));
				levelTimer = 1300;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 15) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-250, 250, 75, 75, 250, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 120;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 16) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 35) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, 2, ID.EnemySweep, handler));
			} else if (spawnTimer == 25) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -2, ID.EnemySweep, handler));
			} else if (spawnTimer == 15) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, 4, ID.EnemySweep, handler));
			} else if (spawnTimer == 0) {
				handler.addObject(
						new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 30, -4, ID.EnemySweep, handler));
				spawnTimer = 30;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 17) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1000;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -9, ID.EnemySmart, handler));
				spawnTimer = 50;
			}
			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 18) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 200, 200,
						-40, ID.EnemyShooter, this.handler));
				levelTimer = 2500;
				tempCounter++;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 19) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-300, 300, 60, 60, 300, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 60;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levels.remove(levelNumber);
				levelNumber += 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // used the same way as tempCounter
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		}

		else if (levelNumber == 20) {
			if (onScreenTimer != 0) {
				if (temp < 1) {
					levelString.setString("Last Boss Fight!!!");
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
			if (tempCounter < 1) {
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 1));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 2));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 150, ID.BossEye, handler, 3));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 4));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 5));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 - 50, ID.BossEye, handler, 6));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 150, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 7));
				handler.addObject(new BossEye(Game.WIDTH / 2 - 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 8));
				handler.addObject(new BossEye(Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 50, ID.BossEye, handler, 9));
				tempCounter++;
			}

		} else if(levelNumber > 20){
			
		}
		
		// WINNER
		// else if(levelNumber){
		// levelTimer --;
		// if(tempCounter < 1){
		// handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2
		// - 200,
		// "Same levels...", ID.Levels1to10Text));
		// handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT /
		// 2,
		// "...but a little harder now", ID.Levels1to10Text));
		// tempCounter++;
		// }
		// if(levelTimer <= 0){
		// handler.clearEnemies();
		// tempCounter = 0;
		// levelNumber = levels.get(index);
		// }
		//
		// }

	}

	public void skipLevel() {
		if (levelsRemaining == 1) {
			levelsRemaining--;
			tempCounter = 0;
			temp = 0;
			onScreenTimer = 100;
			levelNumber = 20;
		} else if (levelsRemaining > 1) {
			levels.remove(index);
			levelsRemaining--;
			System.out.println(levelsRemaining);
			tempCounter = 0;
			temp = 0;
			onScreenTimer = 100;
			levelNumber += 1;
		} else if (levelsRemaining <= 0) {
			game.gameState = STATE.GameWin;
			//System.exit(0); // quits the game for now
			Sound.stopSoundWaves();
			Sound.playSoundWin();
		}
	}

	public void restart() {
		levelNumber = -10;
		tempCounter = 0;
		levelTimer = 150;
		// randomMax = 10;
		// index = r.nextInt(randomMax);

	}

}

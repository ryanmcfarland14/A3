package mainGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * Contains the programming of levels 1-10, as well as handles level progression
 * 
 * @author Brandon Loehle 5/30/16
 */

public class Spawn1to10 {

	public static int LEVEL_SET = 1;
	private Handler handler;
	private HUD hud;
	private Game game;
	private int temp = 0;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private int onScreenTimer; // used to make the level text disappear off the
								// screen
	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>(); // MAKE THIS AN ARRAY
															// LIST SO I CAN
															// REMOVE OBJECTS
	private int levelsRemaining;
	private int levelNumber;
	private int tempCounter;
	private LevelText levelString;
	
	private boolean hardmode = false;

	public Spawn1to10(Handler handler, HUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		handler.object.clear();
		hud.setScore(0);
		hud.setLevel(1);
		spawnTimer = 10;
		levelTimer = 150;
		onScreenTimer = 100;
		levelsRemaining = 9; // 10 Levels excluding the boss
		tempCounter = 0;
		addLevels();
		levelNumber = 1;
		levelString = new LevelText(Game.WIDTH / 2 - Game.scaleX(675), Game.HEIGHT / 2 - Game.scaleY(150),
				"Level " + levelNumber, ID.Levels1to10Text);
		hud.setLevel(levelNumber);
		hardmode = false;

	}

	/**
	 * Pre-load every level
	 */
	public void addLevels() {
		for (int i = 0; i <= 9; i++) {
			levels.add(i);
		}
	}
	
	public void enableHardMode() {
		hardmode = true;
	}

	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {
		if (levelNumber == 1) {// this is level 1
			spawnTimer--;// keep decrementing the spawning spawnTimer 60 times a
							// second
			levelTimer--;// keep decrementing the level spawnTimer 60 times a
							// second
			if (tempCounter < 1) {// called only once, but sets the levelTimer
									// to how long we want this level to
									// run for
				levelTimer = 2000;// 2000 / 60 method calls a second = 33.33
									// seconds long
				tempCounter++;// ensures the method is only called once
			}
			if (spawnTimer == 0) {// time to spawn another enemy
				handler.addObject(
						new EnemyBasic(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 9, 9, ID.EnemyBasic, handler));// add
				spawnTimer = 100;// reset the spawn timer
			}
			if (levelTimer == 0) {// level is over
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on
													// HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				levelNumber += 1;
				levelsRemaining -= 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) { // us
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 2) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 2000;
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
				spawnTimer = 80;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				levelNumber += 1;
				levelsRemaining -= 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) {
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}

		} else if (levelNumber == 3) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1500;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -5, ID.EnemySmart, handler));
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
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 4) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShotgun(Game.scaleX(40), Game.scaleY(100), -20, ID.EnemyShotgun,
						this.handler));
				handler.addObject(new EnemyShotgun(Game.scaleX(1400), Game.scaleY(100), -20, ID.EnemyShotgun,
						this.handler));
				levelTimer = 1300;
				tempCounter++;
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
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 5) {
			levelTimer--;
			levelString.setString("Ghost Watch Out!");
			if (tempCounter < 1) {
				handler.addObject(new EnemyBossGhost(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -5, ID.EnemyBossGhost, handler));
				levelTimer = 1300;
				tempCounter++;
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
					levelString.setString("Level 6");
					handler.addObject(levelString);
					Sound.playSoundNewLevel();
//					levelString.setString("Level " + levelNumber);
//					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 6) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1200;
				tempCounter++;
			}
			if (spawnTimer == 35) {
				handler.addObject(new EnemyRaindrop(0, 0, 0, 9, ID.EnemyRaindrop, handler));
			} else if (spawnTimer == 0) {
				handler.addObject(new EnemyRaindrop(0, 0, 0, 9, ID.EnemyRaindrop, handler));
				spawnTimer = 100;
			}

			if (levelTimer == 0) {
				handler.clearEnemies();
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
				System.out.println(levels.size());

				levelNumber += 1;
				levelsRemaining -= 1;
				temp = 0;
				onScreenTimer = 100;
			}
			if (onScreenTimer != 0) {
				if (temp < 1) {
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 7) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1000;
				tempCounter++;
			}
			if (spawnTimer == 0) {
				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 50;
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
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 8) {
			levelTimer--;
			if (tempCounter < 1) {
				handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 200, 200,
						-15, ID.EnemyShooter, this.handler));
				levelTimer = 2500;
				tempCounter++;
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
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		} else if (levelNumber == 9) {
			spawnTimer--;
			levelTimer--;
			if (tempCounter < 1) {
				levelTimer = 1400;
				tempCounter++;
			}
			if (spawnTimer <= 0) {
				handler.addObject(new EnemyBurst(-200, 200, 40, 40, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
				spawnTimer = 90;
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
					Sound.playSoundNewLevel();
					levelString.setString("Level " + levelNumber);
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}
		}

		else if (levelNumber == 10) {// arbitrary number for the boss
			if (onScreenTimer != 0) {
				if (temp < 1) {
					Sound.playSoundNewLevel();
					levelString.setString("Boss Fight!!!");
					handler.addObject(levelString);
					temp++;
				}
				onScreenTimer--;
			} else if (onScreenTimer == 0) {
				handler.removeObject(levelString);
			}

			if (tempCounter < 1) {
				handler.addObject(new EnemyBoss(ID.EnemyBoss, handler));
				tempCounter++;
			} else if (tempCounter >= 1) {
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.EnemyBoss) {
						if (tempObject.getHealth() <= 0) {
							handler.removeObject(tempObject);
							LEVEL_SET++;
							game.gameState = STATE.Upgrade;
						}
					}
				}
			}

		}
		hud.updateLevelText(levelNumber);
		if(hardmode) spawnTimer--;
	}

	public void playSound(){
		if(onScreenTimer != 0){
		if(temp < 1){
			Sound.playSoundNewLevel();
		}
		}
	}

	public void skipLevel() {

		if (levelsRemaining > 0) {
			levelNumber += 1;
			levelsRemaining--;
			tempCounter = 0;
			temp = 0;
			onScreenTimer = 100;
		} else if (levelsRemaining == 0) {
			LEVEL_SET++;
			game.resetUpgradePaths();
			game.resetUpgradeAdds();
			game.gameState = STATE.Upgrade;
		}
	}
	
	// This isn't called yet.
	public void removeLevels(){
		for (int i = 0; i <= 9; i++) {
			levels.remove(i);
		}
	}
	
	public void setLevelTo(int x) {
		levelNumber = x;
		game.gameState = STATE.Game;
		tempCounter = 0;
		spawnTimer = 0;
	}
	
	public void restart() {
		if(game.gameState == STATE.Menu){
			levelNumber = 1;
			tempCounter = 0;
			levelTimer = 0;
			levelsRemaining = 10;
		}
	}
}
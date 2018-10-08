package mainGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Survival {
	private Handler handler;
	private SurvivalHUD hud;
	private Game game;
	private Player player;
	private int levelTimer;
	private int spawnTimer;
	private Random r = new Random();
	private EnemyFactory factory;
	private double difficulty = 0;
	private LinkedList<GameObject> createdEnemies;
	private LinkedList<GameObject> enemyWaitingList;
	
	public Survival(Handler handler, SurvivalHUD hud, Game game, Player player) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		this.player = player;
		handler.object.clear();
		createdEnemies = new LinkedList<GameObject>();
		enemyWaitingList = new LinkedList<GameObject>();
	}
	
	public void initialize() {
		spawnTimer = 600;
		levelTimer = 590;
		factory = new EnemyFactory(handler);
		createdEnemies.clear();
		enemyWaitingList.clear();
		difficulty = 0;
		hud.score = 0;
		//logToConsole();
	}

	public void tick() {
		incrementTimer();
		//logToConsole();
	}
	
	private void incrementTimer() {
		hud.setDifficulty((int) difficulty);
		levelTimer++;
		if (levelTimer >= spawnTimer) {
			levelTimer = 0;
			spawnEnemy();
			logToConsole();
			if (createdEnemies.size() > (difficulty + 3)) {
				removeEnemy();
			}
			spawnTimer -= 50;
			if (spawnTimer <= 400) {
				factory.increaseDifficulty(0.5);
				spawnTimer = 600;
			}
		}
	}
	
	private void spawnEnemy() {
		if (enemyWaitingList.size() == 0) {
			enemyWaitingList.addAll(factory.generateEnemy());
			createdEnemies.add(enemyWaitingList.removeFirst());
			levelTimer = 0;
		} else {
			createdEnemies.add(enemyWaitingList.removeFirst());
			levelTimer -= 100;
		}
		handler.addObject(createdEnemies.peekLast());
	}
	
	private void logToConsole() {
		System.out.println("Level Timer: " + levelTimer);
		System.out.println("Spawn Timer: " + spawnTimer);
		System.out.println(enemyWaitingList.toString());
	}
	
	private void removeEnemy() {
		hud.setScore((int) (hud.getScore() + (difficulty * factory.getScoreFactor(createdEnemies.peekFirst().id))));
		handler.removeObject(createdEnemies.removeFirst());
	}
	
	private class EnemyFactory {
		private Handler handler;
		private LinkedList<Integer> recentSpawns;
		private final int recentSpawnsLimit = 3;
		
		private ArrayList<ID> enemyIDs;
		private ArrayList<Integer> enemySpawnCounts;
		private ArrayList<Integer> enemyScoreFactors;
		
		private final int enemyBasicSpawnCount = 3;
		private final int enemyBasicScoreFactor = 5;
		private final int enemySweepSpawnCount = 5;
		private final int enemySweepScoreFactor = 5;
		private final int enemySmartSpawnCount = 1;
		private final int enemySmartScoreFactor = 25;
		private final int enemyShooterSpawnCount = 1;
		private final int enemyShooterScoreFactor = 25;
		private final int enemyBurstSpawnCount = 1;
		private final int enemyBurstScoreFactor = 10;
		private final int enemyShotgunSpawnCount = 1;
		private final int enemyShotgunScoreFactor = 10;
		private final int enemyRaindropSpawnCount = 8;
		private final int enemyRaindropScoreFactor = 2;
		
		
		private int spawnSafetyRadius = 60;
		private int[] playerPosition = new int[2];
		private int[] spawnPosition = new int[2];
		private String[] side = { "left", "right", "top", "bottom" };
		private int enemyType;
		
		private int spawnDistance = 0;
		
		public EnemyFactory(Handler handler) {
			this.handler = handler;
			recentSpawns = new LinkedList<Integer>();
			enemyIDs = new ArrayList<ID>();
			enemySpawnCounts = new ArrayList<Integer>();
			enemyScoreFactors = new ArrayList<Integer>();
			populateLists();
		}
		
		public GameObject selectEnemy() {
			enemyType = r.nextInt(enemyIDs.size());
			while (recentSpawns.contains(enemyType)) {
				enemyType = r.nextInt(enemyIDs.size());
			}
			return createEnemy(enemyType);
		}
		
		public GameObject createEnemy(int enemyType) {
			/*
			 * 0 = EnemyBasic
			 * 1 = EnemySweep
			 * 2 = EnemySmart
			 * 3 = EnemyShooter
			 * 4 = EnemyBurst
			 * 5 = EnemyShotgun
			 * 6 = EnemyRaindrop
			 */
			enemyType = r.nextInt(5);
			while (recentSpawns.contains(enemyType)) {
				enemyType = r.nextInt(5);
			}
			addToRecent(enemyType);
			ID enemyID = enemyIDs.get(enemyType);
			setSpawnPosition();
			switch (enemyType) {
				case 0:
					return new EnemyBasic(
							spawnPosition[0], 
							spawnPosition[1], 
							(int) difficulty + 5, 
							(int) difficulty + 5, 
							enemyID, 
							handler);
				case 1:
					return new EnemySweep(
							spawnPosition[0], 
							spawnPosition[1],
							(int) difficulty + 12, 
							(int) difficulty + 1, 
							enemyID, 
							handler);
				case 2: 
					return new EnemySmart(
							spawnPosition[0], 
							spawnPosition[1],
							Math.max(-6, (int) (((difficulty / 2) + 1) * -1)), 
							enemyID, 
							handler);
				case 3:
					return new EnemyShooter(
							spawnPosition[0], 
							spawnPosition[1],
							(int) (5 * difficulty) + 60, 
							(int) (5 * difficulty) + 60, 
							(int) ((difficulty + 12) * -1), 
							enemyID, 
							handler);
				case 4:
					return new EnemyBurst(
							(double) spawnPosition[0], 
							(double) spawnPosition[1],
							(int) 35 + difficulty, 
							(int) 35 + difficulty, 
							(int) ((int) 150 + (3 * difficulty)), 
							side[r.nextInt(4)], 
							enemyID, 
							handler);
				case 5:
					return new EnemyShotgun(
							spawnPosition[0], 
							spawnPosition[1],
							(int) (((2 * difficulty) + 4) * -1), 
							enemyID, 
							handler);
				case 6:
					return new EnemyRaindrop(
							spawnPosition[0], 
							0,
							(r.nextInt((int) difficulty + 1) / 3) - ((int) (difficulty / 2)), 
							(int) (5 + difficulty),
							enemyID, 
							handler);
				default:
					return new EnemyBasic(
							spawnPosition[0], 
							spawnPosition[1], 
							(int) difficulty + 5, 
							(int) difficulty + 5, 
							enemyID, 
							handler);
			}
		}
		
		public ArrayList<GameObject> generateEnemy() {
			GameObject spawnedEnemy = selectEnemy();
			handler.addObject(spawnedEnemy);
			ArrayList<GameObject> spawnedEnemies = new ArrayList<GameObject>();
			spawnedEnemies.add(spawnedEnemy);
			int enemyIndex = enemyIDs.indexOf(spawnedEnemy.id);
			for (int i = 1; i < enemySpawnCounts.get(enemyIndex) + (int) (difficulty / 3); i++) {
				spawnedEnemies.add(createEnemy(enemyIndex));
			}
			return spawnedEnemies;
		}
		
		private void populateLists() {
			enemyIDs.add(ID.EnemyBasic);
			enemySpawnCounts.add(enemyBasicSpawnCount);
			enemyScoreFactors.add(enemyBasicScoreFactor);
			
			enemyIDs.add(ID.EnemySweep);
			enemySpawnCounts.add(enemySweepSpawnCount);
			enemyScoreFactors.add(enemySweepScoreFactor);
			
			enemyIDs.add(ID.EnemySmart);
			enemySpawnCounts.add(enemySmartSpawnCount);
			enemyScoreFactors.add(enemySmartScoreFactor);
			
			enemyIDs.add(ID.EnemyShooter);
			enemySpawnCounts.add(enemyShooterSpawnCount);
			enemyScoreFactors.add(enemyShooterScoreFactor);
			
			enemyIDs.add(ID.EnemyBurst);
			enemySpawnCounts.add(enemyBurstSpawnCount);
			enemyScoreFactors.add(enemyBurstScoreFactor);
			
			enemyIDs.add(ID.EnemyShotgun);
			enemySpawnCounts.add(enemyShotgunSpawnCount);
			enemyScoreFactors.add(enemyShotgunScoreFactor);
			
			enemyIDs.add(ID.EnemyRaindrop);
			enemySpawnCounts.add(enemyRaindropSpawnCount);
			enemyScoreFactors.add(enemyRaindropScoreFactor);
		}
		
		private void setSpawnPosition() {
			playerPosition[0] = (int) player.getX(); 
			playerPosition[1] = (int) player.getY();
			spawnPosition[0] = r.nextInt(Game.WIDTH);
			spawnPosition[1] = r.nextInt(Game.HEIGHT - 80) + 60;
			spawnPosition[1] = r.nextInt(Game.HEIGHT - 80) + 90;
			spawnDistance = (int) Math.sqrt(
					Math.pow((playerPosition[0] - spawnPosition[0]), 2) + 
					Math.pow((playerPosition[1] - spawnPosition[1]), 2));
			//System.out.println(playerPosition[0]);
			//System.out.println(playerPosition[1]);
			//System.out.println(spawnPosition[0]);
			//System.out.println(spawnPosition[1]);
			while (spawnDistance < spawnSafetyRadius) {
				spawnPosition[0] = r.nextInt(Game.WIDTH);
				spawnPosition[1] = r.nextInt(Game.HEIGHT - 80) + 60;
				spawnDistance = (int) Math.sqrt(
						Math.pow((playerPosition[0] - spawnPosition[0]), 2) + 
						Math.pow((playerPosition[1] - spawnPosition[1]), 2));
			}
		}
		
		public void increaseDifficulty(double amount) {
			difficulty += amount;
		}
		
		private void addToRecent(int spawnedEnemy) {
			recentSpawns.add(spawnedEnemy);
			recentSpawns.removeLast();
			if (recentSpawns.size() > recentSpawnsLimit) {
				int newestEnemy = recentSpawns.getLast();
				recentSpawns.clear();
				recentSpawns.add(newestEnemy);
			}			
		}
		
		public int getScoreFactor(ID id) {
			return enemyScoreFactors.get(enemyIDs.indexOf(id));
		}
	}
}

package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The main Heads Up Display of the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class SurvivalHUD extends HUD{
	private double greenValue = 255;

	private int difficulty = 1;
	
	private Color scoreColor = Color.white;

	

	public SurvivalHUD() {
		font = new Font("Amoebic", 1, 30);
		HUDBackground = new HUDRectangle(0, 0, 
				Color.RED, 
				1920, 90);
		healthBarBackground = new HUDRectangle(10, 10, 
				Color.BLACK, 
				410, 44);
		healthBar = new HUDRectangle(15, 15, 
				new Color(75, (int) greenValue, 0), 
				400, 32);
		scoreText = new HUDText(700, 64, scoreColor, font);
		levelText = new HUDText(1100, 64, scoreColor, font);
		HUDElementList = new ArrayList<HUDElement>();
		HUDElementList.add(HUDBackground);
		HUDElementList.add(healthBarBackground);
		HUDElementList.add(healthBar);
		HUDElementList.add(scoreText);
		HUDElementList.add(levelText);
	}

	@Override
	public void tick() {
		greenValue = Game.clamp(greenValue, 0, 255);
	}

	@Override
	public void render(Graphics g) {
		for (HUDElement element : HUDElementList) {
			element.render(g);
		}
	}


	public void updateScoreColor(Color color) {
		this.scoreColor = color;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void tickScore() {
		updateScoreText(score);
		updateLevelText(difficulty);
	}

	public int getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}

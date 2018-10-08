package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import mainGame.Game.STATE;

/**
 * The main menu
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Leaderboard {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Image img;
	private int timer;
	private Random r;
	private ArrayList<Color> colorPick = new ArrayList<Color>();
	private int colorIndex;
	private Spawn1to10 spawner;
	private Score score;

	public Leaderboard(Game game, Handler handler, HUD hud, Spawn1to10 spawner) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		timer = 10;
		r = new Random();
		addColors();
	
		score = new Score();

		img = null;
		try {
			URL imageURL = Game.class.getResource("images/LeaderBoard4.jpg");
			img = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		handler.addObject(new MenuFireworks((r.nextInt(Game.WIDTH) - 25), 500, 50, 50, 0, -2,
			colorPick.get(r.nextInt(6)), ID.Firework, this.handler));
	}

	public void addColors() {
		colorPick.add(Color.blue);
		colorPick.add(Color.white);
		colorPick.add(Color.green);
		colorPick.add(Color.red);
		colorPick.add(Color.cyan);
		colorPick.add(Color.magenta);
		colorPick.add(Color.yellow);
	}

	public void tick() {
		
		handler.tick();
	}

	public void render(Graphics g) {
		
			handler.render(g);
			Font font = new Font("Amoebic", 1, (int) (100 * (Game.HEIGHT / 1080f)));
			Font font2 = new Font("Amoebic", 1, (int) (60 * (Game.HEIGHT / 1080f)));
			g.drawImage(img, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			drawMenuString(g, font, Color.BLACK, "Leaderboard", new int[] {Game.WIDTH/2, 100});
			drawMenuString(g, font2, Color.WHITE, "Hit Backspace to return to the main menu", new int[] {Game.WIDTH/4, 200});
	
			score.loadScores();
			int[] scores = score.getScores();
			String[] names = score.getNames();
			
			for(int i = 0; i < scores.length; i++){
				drawMenuString(g, font, Color.WHITE, 
					Integer.toString(scores[i]), new int[] {Game.WIDTH/2+500, 300 + (100 * i)});
				drawMenuString(g, font, Color.WHITE, 
						names[i], new int[] {Game.WIDTH/2-322, 300 + 100 * i});
			}
	}

	private void drawMenuItem(Graphics g, Font font, Color color, 
		int[] dimensions, String text, int[] textPos) {
		g.setFont(font);
		g.setColor(color);
		g.drawRect(
			(int) (dimensions[0] * (Game.WIDTH / 1920f)), 
			(int) (dimensions[1] * (Game.HEIGHT / 1080f)), 
			(int) (dimensions[2] * (Game.WIDTH / 1920f)), 
			(int) (dimensions[3] * (Game.HEIGHT / 1080f)));
		drawMenuString(g, font, color, text, textPos);
	}

	private void drawMenuString(Graphics g, Font font, Color color,
		String text, int[] textPos) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(
			text, 
			(int) (textPos[0] * (Game.WIDTH / 1920f)), 
			(int) (textPos[1] * (Game.HEIGHT / 1080f)));
	}

			//else if (game.gameState == STATE.Help) {// if the user clicks on "help"
	//			Font font = new Font("impact", 1, 50);
	//			Font font2 = new Font("impact", 1, 30);
	//
	//			g.setFont(font);
	//			g.setColor(Color.white);
	//			g.drawString("Help", 900, 70);
	//
	//			g.setFont(font2);
	////			
	////			String stringToPrint = "How To Play: To play, Waves, you must first understand that you are playing" +" \n"
	////					 + " as the small white box in the center of the screen, with the purpose to try to " + " \n"
	////					 + " stay alive as long as possible while dodging enemies. To start avoiding enemies," +  " \n"
	////					 + " you simply use the arrow keys to navigate the page." + " \n"
	//// 					 + " To pause the game, press key "P." To resume the game press key "P."";
	////			
	////			g.drawString(stringToPrint, 40, 200);
	//			
	//			g.setFont(font2);
	//			g.setColor(Color.white);
	//			g.drawRect(850, 300, 200, 64);
	//			g.drawString("Back", 920, 340);
	//		}
	//
}
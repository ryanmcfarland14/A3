package mainGame;

/**
 * @author Mark Russo November 4, 2017
 * This class creates sound for the game
 * Implemented in the Game class
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import mainGame.Game.STATE;
import sun.audio.*;
import java.awt.event.*;
import java.io.*;

public class Sound {

	// Instance Variables
	private static Clip clip;
	private static Clip clip2;
	private static Clip clip3;
	private static Clip clip4;
	private static Clip clip5;
	private static Clip clip6;

	// Sound Method for the game sound
	public static void playSoundWaves() {
		// Try catch audio
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sound.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-60.0f); // Reduce volume per decibels.
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}

		catch (Exception e) {
			System.out.println("Problem starting music");
			e.printStackTrace();
		}
	}

	// Stop method for waves game sound
	public static void stopSoundWaves() {
		clip.stop();
		clip.close();
	}

	// Method for the MenuSound
	public static void playSoundMenu() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("MenuTheme.wav"));
			clip2 = AudioSystem.getClip();
			clip2.open(audioInputStream);
			// adjusts the volume of the sound
			FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume per decibels.
			clip2.start();
			clip2.loop(Clip.LOOP_CONTINUOUSLY);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Stop method for menu sound
	public static void stopSoundMenu() {
		clip2.stop();
		clip2.close();
		System.out.println("clip closed");
	}

	//Survival Game mode theme
	public static void playSoundSurvival() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("SurvivalTheme.wav"));
			clip2 = AudioSystem.getClip();
			clip2.open(audioInputStream);
			// adjusts the volume of the sound
			FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); // Reduce volume per decibels.
			clip2.start();
			clip2.loop(Clip.LOOP_CONTINUOUSLY);

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Stop method for survival theme
	public static void stopSoundSurvival() {
		clip2.stop();
		clip2.close();
		System.out.println("clip closed");
	}

	// Game over sound
	public static void playSoundOver() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("FailSound.wav"));
			clip3 = AudioSystem.getClip();
			clip3.open(audioInputStream);
			clip3.start();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Sound when the user passes level 20 and wins
	public static void playSoundWin() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("GameWon.wav"));
			clip3 = AudioSystem.getClip();
			clip3.open(audioInputStream);
			clip3.start();
			clip3.loop(Clip.LOOP_CONTINUOUSLY);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// New level sound
	public static void playSoundNewLevel() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("LevelUP.wav"));
			clip4 = AudioSystem.getClip();
			clip4.open(audioInputStream);
			// adjusts the volume of the sound
			FloatControl gainControl = (FloatControl) clip4.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(5.0f);
			clip4.start();
			System.out.println("NEW LEVEL SOUND");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Sound when buttons are clicked on the home screen
	public static void playButtonPress() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("MenuButton.wav"));
			clip5 = AudioSystem.getClip();
			clip5.open(audioInputStream);
			// adjusts the volume of the sound
			FloatControl gainControl = (FloatControl) clip5.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(5.0f);
			clip5.start();
			System.out.println("Button Press");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	// low health warning sound
	public static void playLowHealth() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("LowHealth.wav"));
			clip6 = AudioSystem.getClip();
			clip6.open(audioInputStream);
			// adjusts the volume of the sound
			FloatControl gainControl = (FloatControl) clip6.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(5.0f);
			clip6.start();
			System.out.println("LowHealth");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

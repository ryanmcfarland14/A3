package mainGame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score{
	private int [] scores;
	private String [] names;
	private File saveFile;
	
	public Score(){
		names = new String[5];
		scores = new int[5];
		saveFile = new File("./scores.txt");
	}
	
	public int[] getScores(){
		return scores;
	}
	
	
	public String[] getNames(){
		return names;
	}
	
	public void loadScores(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(saveFile));
			
			String line = reader.readLine();
			String[] readScores = line.split(",");
		
			for(int i = 0 ; i < scores.length; i++){
				String score = "";
				
				if(i < readScores.length){
					scores[i] = Integer.parseInt(readScores[i]);
				} else{
					scores[i] = 0;
				}
			}
			
			line = reader.readLine();
			
			String[] readNames = line.split(",");
		
			for(int i = 0; i < names.length; i++){
				String curName = "";
				
				if(i < readNames.length){
					curName = readNames[i];
				} else{
					curName = "";
				}
				
				names[i] = curName;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No save file found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addScore(int score, String name){
		
		loadScores();
		
		for(int i = 0 ; i<scores.length; i++){
			if(score > scores[i]){
				 for(int j = scores.length -1; j > i; j--){
					 scores[j] = scores[j-1];
				 }
				 
				 scores[i]=score;
				 names[i]= name.length() <= 10 ? name : name.substring(0, 10);
				 
				 break;
			}
		}
		
		saveScores();
	}
		//check to see if score is in top 5
	
	
	private void saveScores(){
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(saveFile));
			
			String [] scoresStringArr = new String[5];
		
			//Convert all scores to strings
			for(int i = 0 ; i < scores.length; i++){
				scoresStringArr[i] = Integer.toString(scores[i]);
			}
		
			String scoreString = String.join(",", scoresStringArr);
			String nameString = String.join(",", names) + "\n";
	
			fw.write(scoreString);
			fw.newLine();
			fw.write(nameString);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

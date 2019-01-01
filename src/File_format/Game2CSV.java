package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Robot.Game;

public class Game2CSV {

	Game game ; 

	public void CreateCSV(Game game ){
		PrintWriter pw;
		try {
			pw = new PrintWriter(new File("Save.csv"));


			StringBuilder sb = new StringBuilder() ; 
			sb.append("Type,ID,Lat,Lon,Alt,Speed/Weight,Radius \n");
			ArrayList<String> data= game.getGame();
			for (int i = 0; i <data.size(); i++) {
				sb.append(data.get(i) + "\n");
			}

			pw.write(sb.toString());
			pw.close();
			System.out.println("done!");
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}	
	}

}

package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Game_objects.Game;



public class Game2CSV {

	Game game ; 

	public void CreateCSV(Game game ){
		PrintWriter pw;
		try {
			pw = new PrintWriter(new File("Save.csv"));


			StringBuilder sb = new StringBuilder() ; 
			sb.append("Type,ID,Lat,Lon,Alt,Speed/Weight,Radius \n");
			StringBuilder SB = game.getGame();
			sb.append(SB);
			
			pw.write(sb.toString());
			pw.close();
			System.out.println("done!");
			System.out.println(SB.toString());
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}	
	}

}

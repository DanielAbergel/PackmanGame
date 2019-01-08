package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Game_objects.Game;


/**
 * 
 *@author Netanel Ben-Isahar
 *@author daniel abargel
 *
 *this class suppose to convert a game to csv file.
 */

public class Game2CSV {

	Game game ; 

	
	/**
	 * this constructor collect every object and store their data.
	 * @param game represent a game.
	 */
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

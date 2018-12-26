package Game_objects;

import java.io.IOException;


import Maps.Map;

/**
 * 
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 * this class is the game class that holds all of the game components.
 */
public class Game {
	
	
	public Map GameMap;
	public Fruits fruits;
	public Packmans packmans;
	
	
	/**
	 * this constructor builds the game.
	 * @param map represent the map.
	 */
	public Game(Map map) 
	{
		fruits = new Fruits();
		packmans = new Packmans();
		GameMap = map;
		
	}
	/**
	 * 
	 * @param path represent the path to the csv file that we want to open
	 * @throws IOException
	 */
	

}

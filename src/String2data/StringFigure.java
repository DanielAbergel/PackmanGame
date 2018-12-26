package String2data;

import java.util.ArrayList;

import GUI.Game;
import Game_objects.Fruit;
import Game_objects.Ghost;
import Game_objects.Packman;
import Geom.GpsPoint;
import Maps.Map;

public class StringFigure {
	public Game game;

	public StringFigure()
	{
		
	}
	public void figure(ArrayList<String> board_data)
	{
		for(int i=0;i<board_data.size();i++)
		{
			String stringLine=board_data.get(i).toString();
			whichObject(stringLine,game.map);
		}

	}
	private void whichObject(String line,Map map)
	{
		String[] arr=line.split(",");
		switch(arr[0])
		{
		case "P": buildTHEpacman(arr,game.map);
		break;
		case "F": buildTHEfruit(arr,game.map);
		break;
		case "G": buildTHEghost(arr,game.map);
		break;
		case "B": buildTHEbox(arr,game.map);
		break;
		
		}
		
	}


	/**
	 * this fiction build the packman.
	 * @param arr getting an array with all of information from the line.
	 * @param map getting a map
	 */
	private void buildTHEpacman(String[] arr, Map map)
	{

		int id=Integer.parseInt(arr[1]);
		GpsPoint gpsLocation=new GpsPoint(Double.parseDouble(arr[2]),Double.parseDouble( arr[3]),Double.parseDouble( arr[4]));
		int speed=Integer.parseInt(arr[5]);
		int radius=Integer.parseInt(arr[6]);

		Packman temp=new Packman(id,gpsLocation,speed,radius,map);

		this.game.packmans.add(temp);


	}
	/**
	 * 
	 *this function build the fruit.
	 * @param arr getting an array with all of information from the line.
	 * @param map getting a map
	 */
	private void buildTHEfruit(String[] arr, Map map)
	{
		int id=Integer.parseInt(arr[1]);
		GpsPoint gpsLocation=new GpsPoint(Double.parseDouble(arr[2]),Double.parseDouble( arr[3]),Double.parseDouble( arr[4]));
		int value=Integer.parseInt(arr[5]);

		Fruit temp=new Fruit(id,gpsLocation,value,map);

		this.game.fruits.add(temp);

	}
	private void buildTHEghost(String[] arr, Map map)
	{
		int id=Integer.parseInt(arr[1]);
		GpsPoint gpsLocation=new GpsPoint(Double.parseDouble(arr[2]),Double.parseDouble( arr[3]),Double.parseDouble( arr[4]));
		int speed=Integer.parseInt(arr[5]);
		int radius=Integer.parseInt(arr[6]);

		Ghost temp=new Ghost(id,gpsLocation,speed,radius);

		this.game.ghosts.add(temp);
	}
	
	private void buildTHEbox(String[] arr, Map map)
	{
		int id=Integer.parseInt(arr[1]);
		GpsPoint gpsLocation=new GpsPoint(Double.parseDouble(arr[2]),Double.parseDouble( arr[3]),Double.parseDouble( arr[4]));
	}


}

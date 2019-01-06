package Game_objects;

import java.util.ArrayList;

import File_format.Board2Game;
import Maps.Map;
import Maps.Pixel;

public class Game 
{
	ArrayList<Ghost> Ghosts ; 
	ArrayList<GeoBox> GeoBoxs ; 
	ArrayList<Packman> Packmans ; 
	ArrayList<Fruit> Fruits ;
	Player player; 
	Map GameMap  ; 
	Board2Game B2G ;

	public Game() 
	{
		GameMap = new Map();
		Ghosts = new ArrayList<Ghost>();
		Packmans = new ArrayList<Packman>();
		GeoBoxs = new ArrayList<GeoBox>();
		Fruits = new ArrayList<Fruit>();
		player = new Player(new Pixel(0,0),GameMap);
		B2G = new Board2Game();
	}
	public ArrayList<Fruit> getFruits() {
		return Fruits;
	}
	public Player getPlayer() {
		return player;
	}
	public Map getGameMap() {
		return GameMap;
	}
	public ArrayList<GeoBox> getGeoBoxs() {
		return GeoBoxs;
	}
	public ArrayList<Packman> getPackmans() {
		return Packmans;
	}
	public ArrayList<Ghost> getGhosts() {
		return Ghosts;
	}
	
	public void setGame(ArrayList<String> Data)
	{
		B2G.SetGame(this, Data,getGameMap());
	}
	public void clear()
	{
		GeoBoxs.clear();
		Packmans.clear();
		Ghosts.clear();
		Fruits.clear();
	}
	public String toString() {
		StringBuilder SB = new StringBuilder();
		SB.append(getPlayer().toString() + "\n");
		for (int i = 0; i < Fruits.size(); i++) 
		{
			SB.append(Fruits.get(i).toString() + "\n");
		}
		for (int i = 0; i < Ghosts.size(); i++) 
		{
			SB.append(Ghosts.get(i).toString() + "\n");
		}
		for (int i = 0; i < Packmans.size(); i++) 
		{
			SB.append(Packmans.get(i).toString() + "\n");
		}
		for (int i = 0; i < GeoBoxs.size(); i++) 
		{
			SB.append(GeoBoxs.get(i).toString() + "\n");
		}
		return SB.toString();
	}
}

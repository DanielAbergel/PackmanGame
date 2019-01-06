package File_format;
import java.util.ArrayList;

import Game_objects.Fruit;
import Game_objects.Game;
import Game_objects.GeoBox;
import Game_objects.Ghost;
import Game_objects.Packman;
import Geom.Point3D;
import Maps.Map;
import Robot.Play;





public class Board2Game {

	Game _game ; 
	ArrayList<String> _data ; 
	StringBuilder SB ; 

	public Board2Game()
	{
		_data = new ArrayList<String>();
	

	}

	private void Create(ArrayList<String> data ,  Map map)
	{
		_game.clear();
		for (int i = 0; i < data.size(); i++)
		{

			switch (data.get(i).charAt(0)) {
			case 'M':
				SetPlayer(data.get(i),map);
				break;
			case 'P':
				addPackman(data.get(i),map);
				break;
			case 'G':
				addGhost(data.get(i),map);
				break;
			case 'F':
				addTarget(data.get(i),map);
				break;
			case 'B':
				addBox(data.get(i),map);
				break;
			default:
				break;
			}

		}

	}

	private void SetPlayer(String Player,Map map) {
		// TODO Auto-generated method stub


	}
	private void addTarget(String Target,Map map) {
		// TODO Auto-generated method stub
		String[] data = Target.split(",");
		_game.getFruits().add(new Fruit(new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),0), map));

	}
	private void addGhost(String Ghost,Map map) {
		// TODO Auto-generated method stub
		String[] data = Ghost.split(",");
		_game.getGhosts().add(new Ghost(new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),0), map));

	}
	private void addBox(String Box,Map map) {
		String[] data = Box.split(",");
		_game.getGeoBoxs().add(new GeoBox(new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),0),new Point3D(Double.parseDouble(data[6]),Double.parseDouble(data[5]),0),map));

	}
	private void addPackman(String Packman ,Map map) {
		String[] data = Packman.split(",");
		_game.getPackmans().add(new Packman(new Point3D(Double.parseDouble(data[3]),Double.parseDouble(data[2]),0), map));

	}

	public  void SetGame(Game game , ArrayList<String> Data,Map map) 
	{
		_game = game ; 
		Create(Data,map);

	}

	


}

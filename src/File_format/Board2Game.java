package File_format;
import java.util.ArrayList;

import Game_objects.Fruit;
import Game_objects.Game;
import Game_objects.GeoBox;
import Game_objects.Ghost;
import Game_objects.Packman;
import Game_objects.Player;
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
		String[] data = Player.split(",");
		_game.setPlayer(new Player(new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3])), map));
	}
	private void addTarget(String Target,Map map) {
		// TODO Auto-generated method stub
		String[] data = Target.split(",");
		_game.getFruits().add(new Fruit(new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),0), map));

	}
	private void addGhost(String Ghost,Map map) {
		// TODO Auto-generated method stub
		String[] data = Ghost.split(",");
		_game.getGhosts().add(new Ghost(new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),0), map));

	}
	private void addBox(String Box,Map map) {
		String[] data = Box.split(",");
		_game.getGeoBoxs().add(new GeoBox(new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),0),new Point3D(Double.parseDouble(data[5]),Double.parseDouble(data[6]),0),map));

	}
	private void addPackman(String Packman ,Map map) {
		String[] data = Packman.split(",");
		_game.getPackmans().add(new Packman(new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),0), map));

	}

	public  void SetGame(Game game , ArrayList<String> Data,Map map) 
	{
		_game = game ; 
		Create(Data,map);

	}

	public static void main(String[] args) {
		String file_name = "data/Ex4_OOP_example2.csv";
		Play play1 = new Play(file_name);
		
		// 2) Set your ID's - of all the group members
		play1.setIDs(1111,2222,3333);
		
		// 3)Get the GPS coordinates of the "arena"
	
		Game game = new Game();
		// 4) get the game-board data
		ArrayList<String> board_data = play1.getBoard();
		Board2Game B2G = new Board2Game();
		B2G.SetGame(game, play1.getBoard(), game.getGameMap());
		
		for(int i=0;i<board_data.size();i++) {
			System.out.println(board_data.get(i));
		}
		System.out.println();
		System.out.println();
		System.out.println(game);
		
	}


}

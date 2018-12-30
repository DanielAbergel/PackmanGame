package File_format;
import java.util.ArrayList;

import Coords.GeoBox;
import Robot.Fruit;
import Robot.Game;
import Robot.Packman;
import Robot.Play;




public class Board2Game {

	Game _game ; 
	ArrayList<String> _data ; 

	public Board2Game()
	{
		_data = new ArrayList<String>();
	}

	private void Create(ArrayList<String> data)
	{
		_game.clear();
		for (int i = 0; i < data.size(); i++)
		{

			switch (data.get(i).charAt(0)) {
			case 'M':
				SetPlayer(data.get(i));
				break;
			case 'P':
				addPackman(data.get(i));
				break;
			case 'G':
				addGhost(data.get(i));
				break;
			case 'F':
				addTarget(data.get(i));
				break;
			case 'B':
				addBox(data.get(i));
				break;
			default:
				break;
			}

		}

	}

	private void SetPlayer(String Player) {
		// TODO Auto-generated method stub
		_game.setPlayer(new Packman(Player));

	}
	private void addTarget(String Target) {
		// TODO Auto-generated method stub
		_game.add(new Fruit(Target));

	}
	private void addGhost(String Ghost) {
		// TODO Auto-generated method stub
		_game.addGhost(new Packman(Ghost));

	}
	private void addBox(String Box) {
		_game.add(new GeoBox(Box));

	}
	private void addPackman(String Packman) {
		_game.add(new Packman(Packman));

	}

	public  void SetGame(Game game , ArrayList<String> Data) 
	{
		_game = game ; 
		Create(Data);
	}




}

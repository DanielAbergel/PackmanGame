package GUI;


import java.util.ArrayList;

import Game_objects.Fruit;
import Game_objects.Fruits;
import Game_objects.Ghosts;
import Game_objects.Packman;
import Game_objects.Packmans;
import Maps.Map;
import Robot.Play;
import String2data.StringFigure;



public class Game {
	
	public String file_name;
	public Play play1;
	public String map_data;
	public ArrayList<String> board_data;
	public Map map;
	public Packmans packmans;
	public Fruits fruits;
	public Ghosts ghosts;
	public StringFigure figure=new StringFigure();
	
	public Game(String file_name)
	{
		figure.game=this;
		this.file_name =file_name;
		this.play1 = new Play(file_name);
		play1.setIDs(204478150,315660712);
		map_data = play1.getBoundingBox();
		board_data = play1.getBoard();
		String [] arr=map_data.split(",");
		map=new Map(Double.parseDouble(arr[2]),Double.parseDouble(arr[3]),Double.parseDouble(arr[5]),Double.parseDouble(arr[6]));
		
	}
	
	
//	
//	public static void main(String[] args) {
//		// 1) Create a "play" from a file (attached to Ex4)
//		String file_name = "data/Ex4_OOP_example1.csv";
//		Play play1 = new Play(file_name);
//		
//		// 2) Set your ID's - of all the group members
//		play1.setIDs(204478150,315660712);
//		
//		// 3)Get the GPS coordinates of the "arena"
//		String map_data = play1.getBoundingBox();
//		System.out.println("Bounding Box info: "+map_data);
//		
//		// 4) get the game-board data
//		ArrayList<String> board_data = play1.getBoard();
//		for(int i=0;i<board_data.size();i++) {
//			System.out.println(board_data.get(i));
//		}
//		System.out.println();
//		System.out.println("Init Player Location should be set using the bounding box info");
//		
//		// 5) Set the "player" init location - should be a valid location
//		play1.setInitLocation(32.1040,35.2071);
//		ArrayList<String> board_data1 = play1.getBoard();
//		for(int i=0;i<board_data1.size();i++) {
//			System.out.println(board_data1.get(i));
//		}
//		// 6) Start the "server"
//		play1.start(); // default max time is 100 seconds (1000*100 ms).
//		
//		// 7) "Play" as long as there are "fruits" and time
//		for(int i=0;i<10;i++) {
//			
//		// 7.1) this is the main command to the player (on the server side)
//			play1.rotate(i*10); 
//			System.out.println("***** "+i+"******");
//			
//		// 7.2) get the current score of the game
//			String info = play1.getStatistics();
//			System.out.println(info);
//		// 7.3) get the game-board current state
//			board_data = play1.getBoard();
//			for(int a=0;a<board_data.size();a++) {
//				System.out.println(board_data.get(a));
//			}
//			System.out.println();
//		}
//		// 8) stop the server - not needed in the real implementation.
//		play1.stop();
//		System.out.println("**** Done Game (user stop) ****");
//		
//		// 9) print the data & save to the course DB
//		String info = play1.getStatistics();
//		System.out.println(info);
//	}
}

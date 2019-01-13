package GUI;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.plaf.FileChooserUI;

import Algorithm.Algorithem;

import DataBase.DataBase;
import File_format.Board2Game;
import File_format.Game2CSV;
import Game_objects.Fruit;
import Game_objects.Game;
import Game_objects.GeoBox;
import Game_objects.Ghost;
import Game_objects.Packman;
import Game_objects.Player;
import Geom.GpsPoint;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

import Robot.Play;


/**
 * 
 *@author Netanel Ben-Isahar
 *@author daniel abargel
 *
 *this class is for the gui interface and include all of the graphic items inside.
 */
public class MainWindow extends JFrame implements MouseListener
{



	Game game ; 

	public BufferedImage PackManImage;
	public BufferedImage FruitImage;
	public BufferedImage GhostImage;
	public BufferedImage PlayerImage;
	MyThread thread ; 
	Play Server ; 
	Board2Game B2G ; 
	Game2CSV G2C ; 

	double playerDirection=0;
	boolean ifThereRun = true;
	boolean NeedRunSave = true ;
	char WhoAmI  = 'P'  ;  // G = Ghost  / P = Player / R = Robot / B = Box / F = Fruit 
	Algorithem algo ;
	Point3D Box ;
	int GameID = 0 ; 


	/**
	 * this constructor initializing the gui.
	 */
	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}

	/**
	 * this function is define the menu.
	 */
	private void InitMenu() {

		MenuBar  menubar = new MenuBar ();
		Menu file = new Menu("File"); 
		Menu gameM = new Menu("Game");
		MenuItem load = new MenuItem("Load");
		MenuItem save = new MenuItem("Save");
		MenuItem clear = new MenuItem("Clear");
		MenuItem packmanM = new MenuItem("PackMan");
		MenuItem fruitM = new MenuItem("Fruit");
		MenuItem Player = new MenuItem("Player");
		MenuItem Ghost = new MenuItem("Ghost");
		MenuItem Box = new MenuItem("Box");
		MenuItem run = new MenuItem("Run");
		MenuItem autoGame = new MenuItem("Auto");
		gameM.add(autoGame);
		menubar.add(file);
		menubar.add(gameM);
		file.add(load);
		file.add(save);
		file.add(clear);
		gameM.add(Box);
		gameM.add(packmanM);
		gameM.add(fruitM);
		gameM.add(Player);
		gameM.add(Ghost);
		gameM.add(run);
		this.setMenuBar(menubar);


		autoGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				G2C.CreateCSV(game);
				if(NeedRunSave)
				Server = new Play("Save.csv");
				Server.setIDs(315660712,204478150);
				Server.setInitLocation(game.getPlayer().getGps().y(),game.getPlayer().getGps().x());
				algo = new Algorithem(game,game.getGameMap());
				Server.start();
				ifThereRun = false ; 
				moveAuto();
			}

			private void moveAuto() {
				// TODO Auto-generated method stub
				MyThreadAuto t = new MyThreadAuto();
				t.start();
			}
		});


		Box.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WhoAmI = 'B';
			}
		});
		Ghost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WhoAmI = 'G';
			}
		});
		Player.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WhoAmI = 'P';
			}
		});
		fruitM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				WhoAmI = 'F';
			}
		});
		packmanM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				WhoAmI = 'R' ;
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				G2C.CreateCSV(game);
			}
		});
		load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(thread != null ) thread.stop();
				G2C.CreateCSV(game);
				game.clear();
				String direction="";
				JButton open=new JButton();
				JFileChooser fc =new JFileChooser();
				fc.setDialogTitle("chose your game file");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if(fc.showOpenDialog(open)==JFileChooser.APPROVE_OPTION)
				{
					direction=fc.getSelectedFile().getAbsolutePath();
					System.out.println(direction);
					direction=direction.replaceAll("\\\\", "\\\\\\\\");
					System.out.println(direction);
					;
				}
				Server = new Play(direction);
				GameID = Server.getHash1();
				System.out.println(GameID);
				NeedRunSave = false ;
				Server.setIDs(315660712,204478150);
				B2G.SetGame(game, Server.getBoard(), game.getGameMap());

				repaint();
			}
		});
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				G2C.CreateCSV(game);
				if(NeedRunSave)
				Server = new Play("Save.csv");
				Server.setInitLocation(game.getPlayer().getGps().y(),game.getPlayer().getGps().x());
				Server.setIDs(315660712,204478150);
				Server.start();
				ifThereRun = false ; 
				move();

			}
		});
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				game.clear();
				Server.stop();
			}
		});

	}
	public void move()
	{
		thread = new MyThread();
		thread.start();
	}
	private void initGUI() 
	{	
		InitMenu();


		G2C = new Game2CSV() ; 
		Server = new Play();
		B2G = new Board2Game();


		try {
			FruitImage = ImageIO.read(new File("Fruit.PNG"));
			PackManImage = ImageIO.read(new File("PackMan.PNG"));
			GhostImage = ImageIO.read(new File("Ghost.PNG"));
			PlayerImage = ImageIO.read(new File("Player.PNG"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		game = new Game();
		Server.start();
	}

	public void paint(Graphics g)
	{

		Image img = createImage(5000,5000);
		Graphics GrapthisPaint = img.getGraphics();
		GrapthisPaint.drawImage(game.getGameMap().myImage, 0, 0,this.getWidth(),this.getHeight(), this);
		game.getGameMap().ChangeFrameSize(new Pixel(this.getWidth(), this.getHeight()));
		if(true)
		{
			Pixel p = game.getGameMap().GPSPoint2Pixel(game.getPlayer().getGps());
			GrapthisPaint.drawImage(PlayerImage,(int)p.get_PixelX()-15,(int)p.get_PixelY()-15,this);

			for (int i = 0; i < game.getPackmans().size(); i++) {
				p = game.getGameMap().GPSPoint2Pixel(game.getPackmans().get(i).getGps());
				GrapthisPaint.drawImage(PackManImage,(int)p.get_PixelX()-20,(int)p.get_PixelY()-10,this);
			}
			for (int i = 0; i < game.getFruits().size(); i++) {
				p = game.getGameMap().GPSPoint2Pixel(game.getFruits().get(i).getGps());
				GrapthisPaint.drawImage(FruitImage,(int)p.get_PixelX()-12,(int)p.get_PixelY()-12,this);
			}
			for (int i = 0; i < game.getGhosts().size(); i++) {
				p = game.getGameMap().GPSPoint2Pixel(game.getGhosts().get(i).getGps());
				GrapthisPaint.drawImage(GhostImage,(int)p.get_PixelX()-15,(int)p.get_PixelY()-15,this);
			}

			for (int i = 0; i < game.getGeoBoxs().size(); i++) 
			{
				Pixel p1 = game.getGameMap().GPSPoint2Pixel(game.getGeoBoxs().get(i).getStartPoint());
				Pixel p2 = game.getGameMap().GPSPoint2Pixel(game.getGeoBoxs().get(i).getEndPoint());

				GrapthisPaint.fillRect((int)p1.get_PixelX(),(int)p2.get_PixelY(),(int)Math.abs(p1.get_PixelX()-p2.get_PixelX()),(int)Math.abs(p1.get_PixelY()-p2.get_PixelY()));




			}

		}
		g.drawImage(img,0,0,this);
	}	




	@Override
	public void mouseClicked(MouseEvent arg) {
		if(ifThereRun)
		{
			switch (WhoAmI) {
			case 'P':
				Point3D result=game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());
				game.setPlayer(new Player(result, game.getGameMap()));
				Server.setInitLocation(result.y(), result.x());
				break;
			case 'R':
				Point3D resultR =game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());
				game.addPackman(new Packman(resultR,game.getGameMap()));
				break;
			case 'G':
				Point3D resultG=game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());
				game.addGhost(new Ghost(resultG,game.getGameMap()));
				break;
			case 'F':
				Point3D resultF=game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());
				game.addFruit(new Fruit(resultF,game.getGameMap()));
				break;
			case 'B' :
				Box = game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());
				WhoAmI = 'Q' ; 
				break;
			case 'Q' :
				Point3D resultQ=game.getGameMap().Pixel2GPSPoint(arg.getX(), arg.getY());

				game.addGeoBox(new GeoBox(Box, resultQ, game.getGameMap()));
				WhoAmI = 'B';
				break;
			default:
				break;
			}


		}else {

			System.out.println("mouse Clicked");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Pixel player=game.getGameMap().GPSPoint2Pixel(new Point3D(game.getPlayer().getGps()));
			Point3D  playerGPS = game.getGameMap().Pixel2GPSPoint(player.get_PixelX(), player.get_PixelY());
			Pixel target=new Pixel(arg.getX(),arg.getY());
			Point3D  targetGPS = game.getGameMap().Pixel2GPSPoint(target.get_PixelX(), target.get_PixelY());
			azimuth(playerGPS.y(),playerGPS.x(), targetGPS.y(),targetGPS.x());
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {


	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	/* finds the azimuth angle */
	public void azimuth(double lat1, double lon1, double lat2, double lon2){




		double longitude1 = lon1;
		double longitude2 = lon2;
		double latitude1 = Math.toRadians(lat1);
		double latitude2 = Math.toRadians(lat2);
		double longDiff= Math.toRadians(longitude2-longitude1);
		double y= Math.sin(longDiff)*Math.cos(latitude2);
		double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

		playerDirection= (Math.toDegrees(Math.atan2(y, x))+360)%360;



	}


	public class MyThreadAuto extends Thread {

		@Override
		public void run()
		{

			while(Server.isRuning()) {


				Server.rotate(playerDirection);
				B2G.SetGame(game, Server.getBoard(),game.getGameMap());
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(algo.shortestPath.isEmpty() || !thereISFruit(algo.point3DInclude.get(Integer.parseInt(algo.shortestPath.get(algo.shortestPath.size()-1))))) {
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int index = algo.findShortest(game);

					algo.init(game, game.getGameMap());

					if(game.getFruits().size() != 0)
						algo.StartAlgo(game.getGameMap().GPSPoint2Pixel(game.getPlayer().getGps()), game.getGameMap().GPSPoint2Pixel(game.getFruits().get(index).getGps()));

				}
				else 
				{
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(check(algo.point3DInclude.get(Integer.parseInt(algo.shortestPath.get(0)))))
					{
						algo.shortestPath.remove(0);
						if(algo.shortestPath.size() != 0 )
							azimuth(game.getPlayer().getGps().y(), game.getPlayer().getGps().x(),algo.point3DInclude.get(Integer.parseInt(algo.shortestPath.get(0))).y(),algo.point3DInclude.get(Integer.parseInt(algo.shortestPath.get(0))).x());
					}
				}
				repaint();
			}
			Server.stop();
			ifThereRun = true;
			System.out.println(Server.getStatistics());
			String[] data = Server.getStatistics().split(",");
			DataBase DB = new DataBase();
			double avg = DB.getAvg(Server.getHash1());
			System.out.println("Your Time : " + data[2].substring(6)+ " | Avg Time : " + avg);
		}
		public boolean check(Point3D P)
		{
			Pixel p = game.getGameMap().GPSPoint2Pixel(game.getPlayer().getGps());
			Pixel p1 = game.getGameMap().GPSPoint2Pixel(P);

			return (Math.abs(p.get_PixelX()-p1.get_PixelX()) < 5) && (Math.abs(p1.get_PixelY()-p.get_PixelY()) < 5) ;
		}

		public boolean thereISFruit(Point3D P)
		{
			for (int i = 0; i < game.getFruits().size(); i++) 
			{
				if(P.close2equals1(game.getFruits().get(i).getGps(),50)) return true; 
			}
			return false;
		}
	}
	
	public class MyThread extends Thread
	{

		@Override
		public void run()
		{

			while(Server.isRuning()) {


				Server.rotate(playerDirection);
				B2G.SetGame(game, Server.getBoard(),game.getGameMap());
				try {
					Thread.sleep(100);
				} 
				catch (InterruptedException e) 
				{

					e.printStackTrace();
				}
				repaint();
			}
			Server.stop();
			ifThereRun = true;
			System.out.println(Server.getStatistics());
			String[] data = Server.getStatistics().split(",");
			DataBase DB = new DataBase();
			double avg = DB.getAvg(Server.getHash1());
			System.out.println("Your Score : " + data[2].substring(6)+ " | Avg Time : " + avg);
		}
	}



}



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
import Coords.GeoBox;
import Coords.LatLonAlt;
import File_format.Board2Game;
import File_format.Game2CSV;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
import Robot.Fruit;
import Robot.Game;
import Robot.Packman;
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
	Map GameMap ; 
	public BufferedImage PackManImage;
	public BufferedImage FruitImage;
	public BufferedImage GhostImage;
	public BufferedImage PlayerImage;
	MyThread thread ; 
	Play Server ; 
	Board2Game B2G ; 
	Game2CSV G2C ; 
	String Box = ""; 
	int playerDirection=0;
	boolean ifThereRun = true;
	char WhoAmI  = 'P'  ;  // G = Ghost  / P = Player / R = Robot / B = Box / F = Fruit 
	Algorithem algo ;



	public MainWindow() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}


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
		MenuItem KML = new MenuItem("KmlRun");
		MenuItem autoGame = new MenuItem("Auto");
		gameM.add(autoGame);
		menubar.add(file);
		menubar.add(gameM);
		file.add(load);
		file.add(save);
		file.add(clear);
		file.add(KML);
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
				Server = new Play("Save.csv");
				algo=new Algorithem(game, GameMap);
				ArrayList<String> data = game.getGame();
				for (int i = 0; i < data.size(); i++) {
					System.out.println(data.get(i));
				}
				System.out.println(" aaaaaaaaaaaaaaaaa" + game.getTarget(0).getOrientation());
				algo.StartAlgo(GameMap.GPSPoint2Pixel(new Point3D(game.getPlayer().getLocation().lat(),game.getPlayer().getLocation().lon(),0)), GameMap.GPSPoint2Pixel(new Point3D(game.getTarget(0).getLocation().lat(),game.getTarget(0).getLocation().lon(),0)));


				Server.start();
				ifThereRun = false ; 

				move();


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
				B2G.SetGame(game, Server.getBoard());
				repaint();
			}
		});
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				G2C.CreateCSV(game);
				Server = new Play("Save.csv");
				Server.start();
				ifThereRun = false ; 
				move();

			}
		});

	}
	public void move()
	{
		thread = new MyThread();
		GameAuto T = new GameAuto() ; 
		T.start();
		thread.start();
	}
	private void initGUI() 
	{	
		InitMenu();


		G2C = new Game2CSV() ; 
		Server = new Play();
		B2G = new Board2Game();
		//Server.setInitLocation(32.1040,35.2061);
		GameMap = new Map() ; 
		try {
			FruitImage = ImageIO.read(new File("Fruit.PNG"));
			PackManImage = ImageIO.read(new File("PackMan.PNG"));
			GhostImage = ImageIO.read(new File("Ghost.PNG"));
			PlayerImage = ImageIO.read(new File("Player.PNG"));
			GameMap.myImage = ImageIO.read(new File("Ariel1.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		game = new Game();
		Server.start();
	}

	public void paint(Graphics g)
	{


		g.drawImage(GameMap.myImage, 0, 0,this.getWidth(),this.getHeight(), this);
		GameMap.ChangeFrameSize(new Pixel(ret().getWidth(), this.getHeight()));
		if(true)
		{
			Packman player= (Packman)game.getPlayer();
			Pixel layerpix = GameMap.GPSPoint2Pixel(new Point3D(player.getLocation().lat(),player.getLocation().lon(),0));

			g.drawImage(PlayerImage,(int)(layerpix.get_PixelX()-20),(int)(layerpix.get_PixelY()-10),this);

			for (int i = 0; i < game.sizeB(); i++) 
			{


				Pixel p1 = GameMap.GPSPoint2Pixel(new Point3D(game.getBox(i).getMax().lat(),game.getBox(i).getMax().lon()));

				Pixel p2 =  GameMap.GPSPoint2Pixel(new Point3D(game.getBox(i).getMin().lat(),game.getBox(i).getMin().lon()));

				g.fillRect((int)p1.get_PixelX()-(int)(p1.get_PixelX()-p2.get_PixelX()),(int) p1.get_PixelY(), (int)(p1.get_PixelX()-p2.get_PixelX()),(int)( p2.get_PixelY()-p1.get_PixelY()));

			}

			for (int i = 0; i < game.getRobots().size(); i++) 
			{

				Packman PackmanTemp= (Packman)game.getRobots().get(i);
				Pixel PackmanPix = GameMap.GPSPoint2Pixel(new Point3D(PackmanTemp.getLocation().lat(),PackmanTemp.getLocation().lon(),0));
				//				System.out.println(ps);
				g.drawImage(PackManImage,(int)(PackmanPix.get_PixelX()-20),(int)(PackmanPix.get_PixelY()-10),this);
			}
			for (int i = 0; i < game.getTargets().size(); i++) 
			{

				Fruit FruitTemp= (Fruit)game.getTargets().get(i);

				Pixel FruitPix = GameMap.GPSPoint2Pixel(new Point3D(FruitTemp.getLocation().lat(),FruitTemp.getLocation().lon(),0));

				g.drawImage(FruitImage,(int)(FruitPix.get_PixelX()-20),(int)(FruitPix.get_PixelY()-10),this);
			}
			for (int i = 0; i < game.getGhosts().size(); i++) 
			{


				Packman GhostTemp= game.getGhosts().get(i);

				Pixel GhostPix = GameMap.GPSPoint2Pixel(new Point3D(GhostTemp.getLocation().lat(),GhostTemp.getLocation().lon(),0));

				g.drawImage(GhostImage,(int)(GhostPix.get_PixelX()-20),(int)(GhostPix.get_PixelY()-10),this);
			}
		}
		ArrayList<String> data = game.getGame();
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}	




	@Override
	public void mouseClicked(MouseEvent arg) {
		if(ifThereRun)
		{
			switch (WhoAmI) {
			case 'P':
				Point3D result=GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				game.setPlayer(new Packman(new LatLonAlt(result.x(),result.y(),0),1));
				Server.setInitLocation(result.y(), result.x());
				break;
			case 'R':
				Point3D resultR =GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				game.add(new Packman(new LatLonAlt(resultR.x(),resultR.y(),0),1));
				break;
			case 'G':
				Point3D resultG=GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				game.addGhost(new Packman(new LatLonAlt(resultG.x(),resultG.y(),0),1));
				break;
			case 'F':
				Point3D resultF=GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				game.add(new Fruit(new LatLonAlt(resultF.x(),resultF.y(),0)));
				break;
			case 'B' :
				Point3D resultB=GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				Box = "B,11," + new LatLonAlt(resultB.y(),resultB.x(),0).toString() ;
				WhoAmI = 'Q' ; 
				break;
			case 'Q' :
				Point3D resultQ=GameMap.Pixel2GPSPoint(arg.getX(), arg.getY());
				Box += "," + new LatLonAlt(resultQ.y(),resultQ.x(),0).toString() + ", " + 1 ;
				game.add(new GeoBox(Box));
				ArrayList<String> s = game.getGame();
				for (int i = 0; i < s.size(); i++) {
					System.out.println(s.get(i));
				}
				WhoAmI = 'B';

				break;
			default:
				break;
			}


		}else {

			System.out.println("mouse Clicked");
			System.out.println("("+ arg.getX() + "," + arg.getY() +")");
			Pixel player=GameMap.GPSPoint2Pixel(new Point3D(game.getPlayer().getLocation().lat(),game.getPlayer().getLocation().lon(),0));

			Pixel target=new Pixel(arg.getX(),arg.getY());
			azimuth(player, target);
		}
		repaint();
	}
	public MainWindow ret() {
		return this ; 
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
	public void azimuth(Pixel player,Pixel target)
	{
		double dy=target.get_PixelX()-player.get_PixelX();
		double dx=target.get_PixelY()-player.get_PixelY();
		double alpha=Math.abs(Math.atan(dy/dx)*(180/Math.PI));

		double azimuth;
		if(dx<0&&dy<0)
			azimuth=180+alpha;
		else if(dx<0&&dy>0)
			azimuth=180-alpha;
		else if(dx>0&&dy<0)
			azimuth=360-alpha;
		else
			azimuth=alpha;
		playerDirection=(int)(180- azimuth);

	}


	public class MyThread extends Thread {

		@Override
		public void run()
		{

			while(Server.isRuning()) {


				Server.rotate(playerDirection);
				B2G.SetGame(game, Server.getBoard());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
			Server.stop();
			game.getPlayer().getData();

		}

	}
	public class GameAuto extends Thread
	{
		@Override
		public void run() {
			Pixel PlayerPix  = new Pixel(-1,-1); 
			int i = 1 ; 
			azimuth(algo.PixelInclude.get(0),algo.PixelInclude.get(algo.PixelInclude.size()-1));
			ArrayList<String> dataP = new ArrayList<String>();
			while(Server.isRuning())
			{
				dataP = Server.getBoard();
				String[] Player = dataP.get(0).split(",");
				PlayerPix = GameMap.GPSPoint2Pixel(new Point3D(Double.parseDouble(Player[2]),Double.parseDouble(Player[3]),0));
				//System.out.println(PlayerPix);
				//System.out.println(algo.PixelInclude.get(Integer.parseInt(algo.shortestPath.get(i))));
				if(Math.abs(PlayerPix.get_PixelX()-algo.PixelInclude.get(i).get_PixelX()) < 5 && Math.abs(PlayerPix.get_PixelY()-algo.PixelInclude.get(i).get_PixelY() ) < 9 )
				{
					i++;
					if(algo.shortestPath.size()-1 != i)
						azimuth(PlayerPix, algo.PixelInclude.get(Integer.parseInt(algo.shortestPath.get(i))));
					else 
						azimuth(PlayerPix,algo.PixelInclude.get(algo.PixelInclude.size()-1));
				}
			}
		}
	}

}

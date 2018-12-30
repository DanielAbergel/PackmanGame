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
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.plaf.FileChooserUI;

import File_format.Board2Game;
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
	boolean PacOrFruit  ;  // false = packman , true = fruit
	Map GameMap ; 
	int x = -1;
	int y = -1;
	public BufferedImage PackManImage;
	public BufferedImage FruitImage;
	MyThread thread ; 
	Play Server ; 
	Board2Game B2G ; 

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
		MenuItem run = new MenuItem("Run");
		MenuItem KML = new MenuItem("KmlRun");
		menubar.add(file);
		menubar.add(gameM);
		file.add(load);
		file.add(save);
		file.add(clear);
		file.add(KML);
		gameM.add(packmanM);
		gameM.add(fruitM);
		gameM.add(run);
		this.setMenuBar(menubar);







		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Server.start();
				move();
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

		Server = new Play("data//Ex4_OOP_example3.csv");
		B2G = new Board2Game();
		Server.setInitLocation(32.1040,35.2061);
		GameMap = new Map() ; 
		try {
			FruitImage = ImageIO.read(new File("Fruit.PNG"));
			PackManImage = ImageIO.read(new File("PackMan.PNG"));
     		GameMap.myImage = ImageIO.read(new File("Ariel1.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		game = new Game();
	}



	public void paint(Graphics g)
	{


		g.drawImage(GameMap.myImage, -10, -10,this.getWidth(),this.getHeight(), this);
		GameMap.ChangeFrameSize(new Pixel(this.getWidth(), this.getHeight()));
		if(true)
		{
			Packman player= (Packman)game.getPlayer();
			Pixel layerpix = GameMap.GPSPoint2Pixel(new Point3D(player.getLocation().lat(),player.getLocation().lon(),0));
			g.drawImage(PackManImage,(int)(layerpix.get_PixelX()-20),(int)(layerpix.get_PixelY()-10),this);
			for (int i = 0; i < game.getRobots().size(); i++) 
			{
			
				Packman s= (Packman)game.getRobots().get(i);
				Pixel ps = GameMap.GPSPoint2Pixel(new Point3D(s.getLocation().lat(),s.getLocation().lon(),0));
//				System.out.println(ps);
				g.drawImage(PackManImage,(int)(ps.get_PixelX()-20),(int)(ps.get_PixelY()-10),this);
			}
			for (int i = 0; i < game.getTargets().size(); i++) 
			{
				

				Fruit s= (Fruit)game.getTargets().get(i);

				Pixel ps = GameMap.GPSPoint2Pixel(new Point3D(s.getLocation().lat(),s.getLocation().lon(),0));

				g.drawImage(FruitImage,(int)(ps.get_PixelX()-20),(int)(ps.get_PixelY()-10),this);
			}
		}





	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");

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


	public class MyThread extends Thread {

		@Override
		public void run()
		{
			for (int i = 0; i < 20; i++) {
				Server.rotate(360);
				ArrayList<String> s = Server.getBoard();
				for (int j = 0; j < s.size(); j++) {
					System.out.println(s.get(j));
				}
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

		}

	}
}
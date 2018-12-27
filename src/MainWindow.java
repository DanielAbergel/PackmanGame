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
	public double d ;
	int x = -1;
	int y = -1;
	public BufferedImage PackManImage;
	public BufferedImage FruitImage;
	MyThread thread ; 
	Map map;
	Play play1 ;

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
		play1 = new Play("data/Ex4_OOP_example9.csv");
		play1.setInitLocation(32.1040,35.2071);
		play1.start();
		map = new Map();
		try {
			FruitImage = ImageIO.read(new File("Fruit.PNG"));
			PackManImage = ImageIO.read(new File("PackMan.PNG"));
			map.myImage = ImageIO.read(new File("Ariel1.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < play1.getBoard().size(); i++) {
			System.out.println(play1.getBoard().get(i));
		}
		game = new Game();
		Packman s = new Packman((String)play1.getBoard().get(0));
		game.setPlayer(s);
	
	}



	public void paint(Graphics g)
	{


		g.drawImage(map.myImage, -10, -10,this.getWidth(),this.getHeight(), this);
//		map.ChangeFrameSizePacman(new Pixel(this.getWidth(), this.getHeight()), game.getRobots(),game.getTargets());
		
		
	
		Pixel p = map.GPSPoint2Pixel(new Point3D(game.getPlayer().getLocation().lon(),game.getPlayer().getLocation().lat(),0));
		
		g.drawImage(PackManImage,(int)(p.get_PixelX()-20),(int)(p.get_PixelY()-10),this);
			for (int i = 0; i < game.getRobots().size(); i++) 
			{
				Packman s= (Packman)game.getRobots().get(i);
				Pixel ps = map.GPSPoint2Pixel(new Point3D(s.getLocation().lon(),s.getLocation().lat(),0));
				g.drawImage(PackManImage,(int)(ps.get_PixelX()-20),(int)(ps.get_PixelY()-10),this);
			}
			for (int i = 0; i < game.getTargets().size(); i++) 
			{
				System.out.println(game.getTargets().size());
				
				Fruit s= (Fruit)game.getTargets().get(i);
				
				Pixel ps = map.GPSPoint2Pixel(new Point3D(s.getLocation().lon(),s.getLocation().lat(),0));
			
				g.drawImage(FruitImage,(int)(ps.get_PixelX()-20),(int)(ps.get_PixelY()-10),this);
			}




		
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");


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

//	public double getXsize()
//	{
//		return game.GameMap.myImage.getWidth();
//	}

//	public double getYsize()
//	{
//		return game.GameMap.myImage.getHeight();
//	}

	public class MyThread extends Thread {

		@Override
		public void run()
		{
			for (int i = 0; i < 1000; i++) {
				play1.rotate(360);
				game = play1._game;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
			}
		

		}

	}
}
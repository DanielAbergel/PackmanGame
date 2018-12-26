package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Game_objects.Fruit;
import Game_objects.Packman;
import Maps.Map;
import Maps.Pixel;

public class newmainwindow extends JFrame implements MouseListener {
	
	Game game;
	int x = -1;
	int y = -1;
	
	public newmainwindow() 
	{
		game=new Game("data/Ex4_OOP_example1.csv");
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
		menubar.add(file);
		menubar.add(gameM);
		file.add(load);
		file.add(save);
		file.add(clear);
		gameM.add(packmanM);
		gameM.add(fruitM);
		gameM.add(run);
		this.setMenuBar(menubar);


	}
	private void initGUI() 
	{	
		InitMenu();
		Map GameMap = game.map;
		try {
			
			GameMap.myImage = ImageIO.read(new File("Ariel1.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public void paint(Graphics g)
	{


		g.drawImage(game.map.myImage, -10, -10,this.getWidth(),this.getHeight(), this);
		game.map.ChangeFrameSizePacman(new Pixel(this.getWidth(), this.getHeight()), game.packmans,game.fruits);
		if(x!=-1 && y!=-1)
		{

			
			
		}
	}
	
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		if(!PacOrFruit) 
		{
			game.packmans.add(new Packman(0, new Pixel(x, y), 1, 0, game.GameMap));
		}
		else
		{
			game.fruits.add(new Fruit(0, new Pixel(x, y), 0, game.GameMap));
		}

		repaint();
	}


}

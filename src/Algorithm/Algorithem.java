package Algorithm;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Game_objects.Game;
import Game_objects.GeoBox;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

import graph.Graph;
import graph.Graph_Algo;
import graph.Node;

public class Algorithem 
{
	ArrayList<Pixel> PixelList ;
	ArrayList<Line2D> Lines ; 
	public ArrayList<Pixel> PixelInclude ;
	public ArrayList<String> shortestPath;

	public Algorithem(Game game , Map map)
	{
		PointFinder  PF = new PointFinder() ; 
		PixelList = PF.getPixels(game, map) ;
		PixelInclude = new ArrayList<Pixel>();
		Lines = new ArrayList<Line2D>();
		UpdateLines(PixelList);
		shortestPath = new ArrayList<String>();
		RemovePoints();
	}



	public void StartAlgo(Pixel myLocation,Pixel destLocation)
	{

		System.out.println(myLocation);
		System.out.println(destLocation);
	
		for (int i = 0; i < PixelList.size(); i++) {
			System.out.println(PixelList.get(i));
		}
		PixelInclude.add(myLocation);
		PixelInclude.addAll(PixelList);
		PixelInclude.add(destLocation);
		int size = PixelInclude.size() ; 


		Graph G = new Graph();
		String Source = "0";
		String Target = "" + (size-1); 

		G.add(new Node(Source)); // Node "a" (0)
		for(int i=1;i<size-1;i++) 
		{
			Node d = new Node(""+i);
			G.add(d);
		}
		G.add(new Node(Target)); // Node "b" (15)

		for (int i = 0; i < size-1; i++) {
			for (int j = 0; j < size; j++) 
			{
				if(i != j)
				{
					if(DidISeehim(PixelInclude.get(i), PixelInclude.get(j)))
					{
						System.out.println(i + ">>" + j );
						G.addEdge("" + i, "" + j, PixelInclude.get(i).dis(PixelInclude.get(j)));
					}
				}

			}
		}

		Graph_Algo.dijkstra(G, Source);

		Node b = G.getNodeByName(Target);
		System.out.println("***** Graph Demo for OOP_Ex4 *****");
		System.out.println(b);
		System.out.println("Dist: "+b.getDist());
		 shortestPath = b.getPath();
		for(int i=0;i<shortestPath.size();i++) {
			System.out.print(","+shortestPath.get(i));
		}
	}

	
	



	/********************Private functions*********************/
	private boolean DidISeehim(Pixel p1 , Pixel p2) 
	{
		Line2D Line = new Line2D.Double(p1.get_PixelX(),p1.get_PixelY(),p2.get_PixelX(),p2.get_PixelY());
		boolean ans = true ; 
		boolean checkIFonLine = true;
		for (int i = 0; i < Lines.size() && ans; i++)
		{
			checkIFonLine = true ; 
			Pixel pixel1Line = new Pixel(Lines.get(i).getX1(),Lines.get(i).getY1());
			Pixel pixel2Line = new Pixel(Lines.get(i).getX2(),Lines.get(i).getY2());
			if(p1.equals(pixel1Line) || p1.equals(pixel2Line) || p2.equals(pixel1Line) || p2.equals(pixel2Line)) checkIFonLine = false ;
			if(checkIFonLine && IFintersects(Line,Lines.get(i))) ans = false ; 
		}
		return ans;
	}
	private boolean IFintersects(Line2D line1 , Line2D line2 )
	{
		return line1.intersectsLine(line2);
	}
	private void UpdateLines(ArrayList<Pixel> Pixels) {

		for (int i = 0; i < Pixels.size(); i = i + 4) 
		{

			Lines.add(new Line2D.Double(Pixels.get(i).get_PixelX(),Pixels.get(i).get_PixelY(),Pixels.get(i+3).get_PixelX(),Pixels.get(i+3).get_PixelY())); // left wall
			Lines.add(new Line2D.Double(Pixels.get(i).get_PixelX(),Pixels.get(i).get_PixelY(),Pixels.get(i+2).get_PixelX(),Pixels.get(i+2).get_PixelY())); // down wall
			Lines.add(new Line2D.Double(Pixels.get(i+2).get_PixelX(),Pixels.get(i+2).get_PixelY(),Pixels.get(i+1).get_PixelX(),Pixels.get(i+1).get_PixelY())); // right wall
			Lines.add(new Line2D.Double(Pixels.get(i+3).get_PixelX(),Pixels.get(i+3).get_PixelY(),Pixels.get(i+1).get_PixelX(),Pixels.get(i+1).get_PixelY())); // up wall
			Lines.add(new Line2D.Double(Pixels.get(i).get_PixelX(),Pixels.get(i).get_PixelY(),Pixels.get(i+1).get_PixelX(),Pixels.get(i+1).get_PixelY()));
			Lines.add(new Line2D.Double(Pixels.get(i+2).get_PixelX(),Pixels.get(i+2).get_PixelY(),Pixels.get(i+3).get_PixelX(),Pixels.get(i+3).get_PixelY()));
		}

	}
	private void RemovePoints() {
		ArrayList<Pixel> RemoveList = new ArrayList<Pixel>(PixelList);
		for (int i = RemoveList.size()-1; 0 < i; i--) {
			for (int j = 0; j < PixelList.size(); j++) 
			{
				try {
					if(RemoveList.get(i).get_PixelX() > PixelList.get(j).get_PixelX() && RemoveList.get(i).get_PixelX() < PixelList.get(j+1).get_PixelX() && RemoveList.get(i).get_PixelY() < PixelList.get(j).get_PixelY() && RemoveList.get(i).get_PixelY() > PixelList.get(j+1).get_PixelY())
					{
						RemoveList.remove(i);
						break;
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		PixelList = RemoveList ;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		Game game = new Game();
		Map map = new Map();
	
		game.getGeoBoxs().add(new GeoBox(new Point3D(35.20552370064878,32.10346280397053,0),new Point3D(35.20727202866246,32.10393044420183,0),map));
		game.getGeoBoxs().add(new GeoBox(new Point3D(35.206075800165465,32.102943206521985,0),new Point3D(35.20659109643439,32.10354074384279,0),map));
	
		

		Algorithem A = new Algorithem(game,map);
		A.StartAlgo(map.GPSPoint2Pixel(new Point3D(35.20519244274052,32.10376157384208,0)), map.GPSPoint2Pixel(new Point3D(35.207437661689454,32.10351476387798,0)));
	}
	/*
	 * M,2,32.10376157384208,35.20519244274052,0.0,1.0,1.0
F,0,32.10351476387798,35.207437661689454,0.0,1.0
B,11,32.10346280397053,35.20552370064878,0.0,32.10393044420183,35.20727202866246,0.0,1.0
B,11,32.102943206521985,35.206075800165465,0.0,32.10354074384279,35.20659109643439,0.0,1.0
	 */

}


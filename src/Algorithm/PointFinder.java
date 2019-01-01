package Algorithm;

import java.awt.Point;
import java.util.ArrayList;

import Coords.GeoBox;
import Coords.MyCoords;
import Geom.Point3D;
import Robot.Game;

public class PointFinder {

	ArrayList<Point3D> Points ; 
	MyCoords Convert ; 

	public PointFinder() {
		Points = new ArrayList<Point3D>();
		Convert = new MyCoords() ; 
	}
	public ArrayList<Point3D> getPoint3D (Game game){
		for (int i = 0; i < game.sizeB(); i++)
		{
			Points.add(game.getBox(i).getMin());
			Points.add(game.getBox(i).getMax());
			addNearPoints(game.getBox(i).getMin(), game.getBox(i).getMax());
		}

		return Points ;
	}
	private void addNearPoints(Point3D Min , Point3D Max)
	{
		Point3D Vec = Convert.vector3D(Min, Max) ; 
		System.out.println(Vec);
		Point3D NearMin = new Point3D(Min.GPS2Meter()) ;
		Point3D	NearMax	= new Point3D(Min) ; 
		NearMin.add(Vec.x(), 0);
		NearMax.add(0,Vec.y());
		Min.Meter2GPS();
		Points.add(NearMax.Meter2GPS());
		Points.add(NearMin.Meter2GPS());
	}
	public static void main(String[] args) {
		PointFinder hw = new PointFinder() ; 
		Game game = new Game();
		game.add(new GeoBox("B,5,32.1021493912899,35.20281754867361,0.0,32.105414027818185,35.203302498815304,0.0,1.0"));
		System.out.println(game.sizeB());
		ArrayList<Point3D> List = hw.getPoint3D(game);
		if(List == null) System.out.println("aaaaaaaaaaa");
		for (int i = 0; i < List.size(); i++) {
			System.out.println( List.get(i));
		}
	}
}

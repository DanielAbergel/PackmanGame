package Algorithm;

import java.awt.Point;
import java.util.ArrayList;

import Coords.GeoBox;
import Coords.MyCoords;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
import Robot.Game;

public class PointFinder {

	ArrayList<Point3D> Points ; 
	MyCoords Convert ; 

	public PointFinder() {
		Points = new ArrayList<Point3D>();
		Convert = new MyCoords() ; 
	}
	public ArrayList<Pixel> getPixels (Game game, Map map){
		for (int i = 0; i < game.sizeB(); i++)
		{
			Points.add(new Point3D(game.getBox(i).getMin().lon(),game.getBox(i).getMin().lat(),0));
			Points.add(new Point3D(game.getBox(i).getMax().lon(),game.getBox(i).getMax().lat(),0));
			addNearPoints(Points.get(Points.size()-2), Points.get(Points.size()-1));
			
		}
		ArrayList<Pixel>  PointPix = new ArrayList<Pixel>();
		for (int i = 0; i < Points.size(); i++) {
			PointPix.add(map.GPSPoint2Pixel(Points.get(i)));
		}
		
		
		return PointPix ;
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
		Points.add(NearMin.Meter2GPS());
		Points.add(NearMax.Meter2GPS());
	}
	
	
	
	public static void main(String[] args) {
		PointFinder hw = new PointFinder() ; 
		Game game = new Game();
		Map map = new Map();
		game.add(new GeoBox("B,5,32.1021493912899,35.20281754867361,0.0,32.105414027818185,35.203302498815304,0.0,1.0"));

		ArrayList<Pixel> List = hw.getPixels(game , map);

		for (int i = 0; i < List.size(); i++) {
			System.out.println( List.get(i));
		}
	}
}
/*
 * for (int i = 0; i < PointPix.size(); i=i+4) {
			PointPix.get(i).set_PixelX(PointPix.get(i).get_PixelX()+1);
			PointPix.get(i).set_PixelY(PointPix.get(i).get_PixelY()-1);
			PointPix.get(i+1).set_PixelX(PointPix.get(i+1).get_PixelX()+1);
			PointPix.get(i+1).set_PixelY(PointPix.get(i+1).get_PixelY()+1);
			PointPix.get(i+2).set_PixelX(PointPix.get(i+2).get_PixelX()+1);
			PointPix.get(i+2).set_PixelY(PointPix.get(i+2).get_PixelY()-1);
			PointPix.get(i+3).set_PixelX(PointPix.get(i+3).get_PixelX()-1);
			PointPix.get(i+3).set_PixelY(PointPix.get(i+3).get_PixelY()+1);
		}
*/
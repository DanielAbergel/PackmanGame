package Algorithm;

import java.awt.Point;
import java.util.ArrayList;


import Coords.MyCoords;
import Game_objects.Game;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

/**
 * 
 * @author Netanel Ben-Isahar
 * @author Daniel Abergel
 * this class is responsible of collecting the boxes corners.
 */
public class PointFinder {

	ArrayList<Point3D> Points ; 
	MyCoords Convert ; 

	/**
	 * this constructor is define points and convert.
	 */
	public PointFinder() {
		Points = new ArrayList<Point3D>();
		Convert = new MyCoords() ; 
	}
	/**
	 * this function collect the 2 first points of the box use another function to collect the two others.
	 * @param game represents a game.
	 * @param map represents a map.
	 * @return a pixel arraylist of points.
	 */
	public ArrayList<Pixel> getPixels (Game game, Map map){
		for (int i = 0; i < game.getGeoBoxs().size(); i++)
		{
			Points.add(new Point3D(game.getGeoBoxs().get(i).getStartPoint()));
			Points.add(new Point3D(game.getGeoBoxs().get(i).getEndPoint()));
			addNearPoints(Points.get(Points.size()-2), Points.get(Points.size()-1));
			
		}
		ArrayList<Pixel>  PointPix = new ArrayList<Pixel>();
		for (int i = 0; i < Points.size(); i++) {
			PointPix.add(map.GPSPoint2Pixel(Points.get(i)));
			
			PointPix.get(i).set((i+1) % 4);
		}
		
		
		
		
		return PointPix ;
	}
	/**
	 * this function collect the 2 other points.
	 * @param Min represents a min point.
	 * @param Max represents a max point.
	 */
	private void addNearPoints(Point3D Min , Point3D Max)
	{
		Point3D Vec = Convert.vector3D(Min, Max) ; 
	
		Point3D NearMin = new Point3D(Min.GPS2Meter()) ;
		Point3D	NearMax	= new Point3D(Min) ; 
		NearMin.add(Vec.x(), 0);
		NearMax.add(0,Vec.y());
		Min.Meter2GPS();
		Points.add(NearMin.Meter2GPS());
		Points.add(NearMax.Meter2GPS());
	}
	
	
	

}

package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Fruit extends General{

	static int size = 0 ; 
	public Fruit(Pixel pixel, Map map) 
	{
		super(pixel, map);
	
	}
	public Fruit(Point3D Gps, Map map)
	{
		super(Gps, map);
	}
	public String toString() {
		return "F," + size + "," + Gps.toString() + ",1" ;
	}
}

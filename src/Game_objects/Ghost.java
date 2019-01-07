package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Ghost extends General{

	static int  size = 0 ; 
	
	public Ghost(Pixel pixel, Map map)
	{
		super(pixel, map);
		size++;
	}
	public Ghost(Point3D GPS, Map map) 
	{
		super(GPS, map);
		size++;
	}
	@Override
	public String toString() {
		return "G," + size + "," + Gps.toString() + ",10,1";
	}
}

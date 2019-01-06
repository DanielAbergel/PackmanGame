package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Ghost extends General{

	public Ghost(Pixel pixel, Map map)
	{
		super(pixel, map);
	}
	public Ghost(Point3D GPS, Map map) 
	{
		super(GPS, map);
	}
	@Override
	public String toString() {
		return "G," + Gps.toString() ;
	}
}

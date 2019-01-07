package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Player extends General {

	public Player(Pixel pixel, Map map)
	{
		super(pixel, map);
	}
	public Player(Point3D GPS, Map map) 
	{
		super(GPS, map);
	}
	@Override
	public String toString() {
		return "M,1," + Gps.toString() + ",20,1"  ;
	}
}

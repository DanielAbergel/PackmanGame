package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
/**
 * 
 * @author Netanel Ben-Isahar
 * this class represents and build a fruit object.
 */
public class Fruit extends General{

	static int size = 0 ; 
	/**
	 * this constructor build the fruit by pixel.
	 * @param pixel represents pixel point of the fruit location.
	 * @param map represents the map.
	 */
	 public Fruit(Pixel pixel, Map map) 
	{
		super(pixel, map);

	}
	/**
	 * this constructor build the fruit by Gps.
	 * @param Gps represents gps point of the fruit location.
	 * @param map represents the map.
	 */
	public Fruit(Point3D Gps, Map map)
	{
		super(Gps, map);
	}
	/**
	 * printing the object.
	 */
	public String toString() {
		return "F," + size + "," + Gps.toString() + ",1" ;
	}
}

package Game_objects;


import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
/**
 * @author Daniel Abergel
 * @author Netanel Ben-Isahar
 * this class represents and build a packman object.
 */
public class Packman extends General {

	static int size = 0 ; 
	/**
	 * this constructor build the packman by pixel.
	 * @param pixel represents pixel point of the packman location.
	 * @param map represents the map.
	 */
	public Packman(Pixel pixel, Map map) 
	{
		super(pixel, map);
		size ++;
	}
	/**
	 * this constructor build the packman by Gps.
	 * @param Gps represents gps point of the packman location.
	 * @param map represents the map.
	 */
	public Packman(Point3D Gps, Map map)
	{
		super(Gps, map);
		size++;
	}

	/**
	 * printing the object.
	 */
	public String toString() {
		return "P," + size + "," + Gps.toString() + ",10,1" ;
	}
}

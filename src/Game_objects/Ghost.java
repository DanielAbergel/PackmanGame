package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
/**
 * 
 * @author Netanel Ben-Isahar
 *this class represents and build a ghost object.
 */
public class Ghost extends General{

	static int  size = 0 ; 
	/**
	 this constructor build the ghost by pixel.
	 * @param pixel represents pixel point of the ghost location.
	 * @param map represents the map.
	 */
	public Ghost(Pixel pixel, Map map)
	{
		super(pixel, map);
		size++;
	}
	/**
	 * this constructor build the ghost by Gps.
	 * @param Gps represents gps point of the ghost location.
	 * @param map represents the map.
	 */
	public Ghost(Point3D GPS, Map map) 
	{
		super(GPS, map);
		size++;
	}
	/**
	 * printing the object.
	 */
	@Override
	public String toString() {
		return "G," + size + "," + Gps.toString() + ",10,1";
	}
}

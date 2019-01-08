package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

/**
 * 
 * @author Netanel Ben-Isahar
 * this class represents and build a player object.
 */
public class Player extends General {

	/**
	 * this constructor build the player by pixel.
	 * @param pixel represents pixel point of the player location.
	 * @param map represents the map.
	 */
	public Player(Pixel pixel, Map map)
	{
		super(pixel, map);
	}
	/**
	 * 
	 * this constructor build the player by Gps.
	 * @param Gps represents gps point of the player location.
	 * @param map represents the map.
	 */
	public Player(Point3D GPS, Map map) 
	{
		super(GPS, map);
	}
	/**
	 * printing the object.
	 */
	@Override
	public String toString() {
		return "M,1," + Gps.toString() + ",20,1"  ;
	}
}

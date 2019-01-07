package Game_objects;


import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
public class Packman extends General {

	static int size = 0 ; 
		public Packman(Pixel pixel, Map map) 
		{
			super(pixel, map);
			size ++;
		}
		public Packman(Point3D Gps, Map map)
		{
			super(Gps, map);
			size++;
		}

		public String toString() {
			return "P," + size + "," + Gps.toString() + ",10,1" ;
		}
}

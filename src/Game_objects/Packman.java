package Game_objects;


import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
public class Packman extends General {


		public Packman(Pixel pixel, Map map) 
		{
			super(pixel, map);
		
		}
		public Packman(Point3D Gps, Map map)
		{
			super(Gps, map);
		}

		public String toString() {
			return "P," + Gps.toString() ;
		}
}

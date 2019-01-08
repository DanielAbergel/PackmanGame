package Game_objects;

import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
/**
 * 
 * @author Netanel Ben-Isahar
 *
 * this class represents a box.
 */
public class GeoBox {

	Point3D StartPoint ; 
	Point3D EndPoint ; 
	Pixel StartPix;
	Pixel EndPix;
	/**
	 * this constructor build the box by gps points.
	 * @param Start represents the starting point.
	 * @param End represents the ending point.
	 * @param map represents the map.
	 */
	public GeoBox(Point3D Start , Point3D End , Map map) 
	{
		StartPoint = Start ; 
		StartPix = map.GPSPoint2Pixel(StartPoint);
		EndPoint = End ; 
		EndPix = map.GPSPoint2Pixel(End);
	}
	/**
	* this constructor build the box by pixel points.
	 * @param Start represents the starting point.
	 * @param End represents the ending point.
	 * @param map represents the map.
	 */
	public GeoBox(Pixel Start , Pixel End , Map map)
	{
		StartPix = Start ; 
		StartPoint = map.Pixel2GPSPoint(Start.get_PixelX(), End.get_PixelY());
		EndPix = End ; 
		EndPoint = map.Pixel2GPSPoint(End.get_PixelX(), End.get_PixelY());
	}
	public Pixel getEndPix() {
		return EndPix;
	}
	public Pixel getStartPix() {
		return StartPix;
	}
	public Point3D getEndPoint() {
		return EndPoint;
	}
	public Point3D getStartPoint() {
		return StartPoint;
	}
	public String toString() {
		return "B,1," + StartPoint.toString() + " , " + EndPoint.toString()  + ",1";
	}
}

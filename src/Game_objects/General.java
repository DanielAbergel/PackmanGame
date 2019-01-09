package Game_objects;


import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;
/**
 * 
 * @author Netanel Ben-Isahar
 * this class is responsible of holding a gps point and a pixel point.
 */
public class General {

	Point3D Gps ; 
	Pixel Pix ; 
	/**
	 * this constructor is getting a gps point and convert it to a pixle point.
	 * @param GPS represents the gps point.
	 * @param map represents the map.
	 */
	public General(Point3D GPS,Map map) 
	{
		this.Gps = GPS;
		this.Pix = map.GPSPoint2Pixel(GPS);
	}
	/**
	 * 
	 * this constructor is getting a pixel point and convert it to a GPS point.
	 * @param pixel represents the pixel point.
	 * @param map represents the map.
	 */
	public General(Pixel pixel , Map map) 
	{
		this.Pix = pixel;
		this.Gps = map.Pixel2GPSPoint(pixel.get_PixelX(), pixel.get_PixelY());
	}

	synchronized public Point3D getGps() {
		return Gps;
	}
	synchronized public Pixel getPix() {
		return Pix;
	}
	synchronized public void setGps(Point3D gps ,Map map) {
		Gps = gps;
		Pix = map.GPSPoint2Pixel(Gps);	
	}
	synchronized public void setPix(Pixel pix , Map map) {
		Pix = pix;
		Gps = map.Pixel2GPSPoint(pix.get_PixelX(), pix.get_PixelY());
	}


}

package Game_objects;


import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class General {

	Point3D Gps ; 
	Pixel Pix ; 
	
	public General(Point3D GPS,Map map) 
	{
		this.Gps = GPS;
		this.Pix = map.GPSPoint2Pixel(GPS);
	}
	public General(Pixel pixel , Map map) 
	{
		this.Pix = pixel;
		this.Gps = map.Pixel2GPSPoint(pixel.get_PixelX(), pixel.get_PixelY());
	}
	
	public Point3D getGps() {
		return Gps;
	}
	public Pixel getPix() {
		return Pix;
	}
	public void setGps(Point3D gps ,Map map) {
		Gps = gps;
		Pix = map.GPSPoint2Pixel(Gps);	
	}
	public void setPix(Pixel pix , Map map) {
		Pix = pix;
		Gps = map.Pixel2GPSPoint(pix.get_PixelX(), pix.get_PixelY());
	}
	
	
}

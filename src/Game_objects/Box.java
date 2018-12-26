package Game_objects;

import Geom.GpsPoint;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Box {

	private int _id;
	public GpsPoint GPSstartingPoint;
	public GpsPoint GPSendingPoint;
	public Pixel PixelstartingPoint;
	public Pixel PixelendingPoint;
	
	public Box(int id,GpsPoint GPSstartingPoint,GpsPoint GPSendingPoint,Map map)
	{
		this._id=id;
		this.GPSstartingPoint=GPSstartingPoint;
		this.GPSendingPoint=GPSendingPoint;
		this.PixelstartingPoint= new Pixel(new Point3D(GPSstartingPoint.getLon(),GPSstartingPoint.getLat(),GPSstartingPoint.getAlt()),map);
		this.PixelendingPoint= new Pixel(new Point3D(GPSendingPoint.getLon(),GPSendingPoint.getLat(),GPSendingPoint.getAlt()),map);
		
	}




}

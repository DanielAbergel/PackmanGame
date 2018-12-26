package Game_objects;

import Geom.GpsPoint;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

/**
 * 
 * The class represent a fruit object.
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 *
 */
public class Fruit {

	private int _id;
	public int _value;
	public GpsPoint _GPS; 
	private Point3D _GPSConvert;
	public Pixel _PixelLocation;
	public double EatenTime ; 
	
	/**
	 * This constructor build the fruit object.
	 * @param id represent the id of fruit.
	 * @param GpsLocation represent the GPS location of the fruit.
	 * @param value represent the value of fruit.
	 * @param map represent the map.
	 */
	public Fruit(int id,GpsPoint GpsLocation,int value , Map map)
	{
		this._id=id;
		this._GPS=GpsLocation;
		this._value=value;
		_GPSConvert = new Point3D(GpsLocation.getLon(),GpsLocation.getLat(),GpsLocation.getAlt());
		this._PixelLocation = new Pixel(_GPSConvert, map);
		EatenTime = 0 ;
	}
	
	/**
	 * This constructor build the fruit object.
	 * @param id represent the id of fruit.
	 * @param PixelLocation represent the pixel location of the fruit.
	 * @param value represent the value of fruit.
	 * @param map represent the map.
	 */
	public Fruit(int id,Pixel PixelLocation,int value, Map map)
	{
		this._id=id;
		this._PixelLocation=PixelLocation;
		this._value=value;
		this._GPSConvert = new Point3D(map.Pixel2GPSPoint(PixelLocation.get_PixelX(),PixelLocation.get_PixelY()));
		this._GPS = new GpsPoint(_GPSConvert);
		EatenTime = 0 ; 
	}
	
	/**
	 * This function gets the GPS conversion.
	 * @return the GPS conversion.
	 */
	public Point3D _GPSConvert() {
		return _GPSConvert;
	}

	/**
	 * This function setup the GPS conversion.
	 * @param xYZlocation represent the GPS conversion.
	 */
	public void _GPSConvert(Point3D xYZlocation) {
		_GPSConvert = xYZlocation;
	}

	/**
	 * This function gets the id of the fruit.
	 * @return the id of the fruit.
	 */
	public int getId() {
		return _id;
	}

	/**
	 * This function setup the id of the fruit.
	 * @param id represent the id of the fruit.
	 */
	public void setId(int id) {
		this._id = id;
	}

	/**
	 * This function gets the GPS location.
	 * @return the GPS location.
	 */
	public GpsPoint getGpsLocation() {
		return _GPS;
	}

	/**
	 * This function setup the GPS location.
	 * @param gpsLocation represent the GPS location.
	 */
	public void setGpsLocation(GpsPoint gpsLocation) {
		this._GPS = gpsLocation;
	}


	/**
	 * This function gets the pixel location.
	 * @return the GPS location.
	 */
	public Pixel getPixelLocation() {
		return _PixelLocation;
	}

	/**
	 * This function setup the pixel location.
	 * @param gpsLocation represent the pixel location.
	 */
	public void setPixelLocation(Pixel pixelLocation) {
		this._PixelLocation = pixelLocation;
	}

	/**
	 * This function gets the value of the fruit.
	 * @return the id of the fruit.
	 */
	public int getValue() {
		return _value;
	}

	/**
	 * This function setup the value of the fruit.
	 * @param value represent the value of the fruit.
	 */
	public void setValue(int value) {
		this._value = value;
	}
	
	/**
	 * This function setup the time when fruit is eaten.
	 * @param time
	 */
	public void SetEatenTime(double time) {
		EatenTime = time ;
	}
}

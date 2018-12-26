package Game_objects;

import Geom.GpsPoint;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Ghost {

	private int _id;
	public GpsPoint _GPS;
	public Point3D _GPSConvert;
	public Pixel _PixelLocation;
	private int _speed;
	private int _radius;

	/** this constructor build a Ghost.
	 * @param id the Ghost id.
	 * @param GpsLocation the Ghost gps location.
	 * @param speed the Ghost speed.
	 * @param radius the Ghost radius.
	 * @param map the game map
	 */
	public Ghost(int id,GpsPoint GpsLocation,int speed,int radius ,Map map)
	{
		this._id=id;
		this._GPS=GpsLocation;
		this._speed=speed;
		this._radius=radius;
		_GPSConvert = new Point3D(GpsLocation.getLon(),GpsLocation.getLat(),GpsLocation.getAlt());
		this._PixelLocation = new Pixel(_GPSConvert, map);
	}

}
package Geom;
import  Coords.MyCoords;

/**
 * This class represent a GPS point with fields : _lat , _lon , _alt
 * @author DanielAbergel
 */
public class GpsPoint  implements Geom_element  {
	
	private double _lat;
	private double _lon;
	private double _alt;
	
	/**
	 * the constructor Receiving the lat lon alt and make a GPS Point
	 * the constructor check if the GPS Point is a valid point
	 * @param lat represent lat in the GPS Point
	 * @param lon represent lon in the GPS Point
	 * @param alt represent alt in the GPS Point
	 */
	public GpsPoint(double lat , double lon , double alt) 
	{
		if(!IsValid()) 
		{
			throw new RuntimeException("the gps Point not valid GpsPoint");
		}
		this._lat = lat;
		this._lon = lon;
		this._alt = alt;
		
	}
	
	public GpsPoint(Point3D p) 
	{
		this._lat = p.y();
		this._lon = p.x();
		this._alt = p.z();
	}
	/**
	 * the function check if the point is valid . 
	 * @return true if the point is valid false otherwise
	 */
	public boolean IsValid() {
		return this._lat<90&&this._lat>-90&&this._lon>-180&&this._lon<180&&this._alt>-450;
	}
	public double getAlt() {
		return _alt;
	}
	
	public void setAlt(double alt) 
	{
		this._alt = alt;
	}
	
	public double getLon()
	{
		return _lon;
	}
	
	public void setLon(double lon)
	{
		this._lon = lon;
	}
	
	public double getLat() 
	{
		return _lat;
	}
	
	public void setLat(double lat)
	{
		this._lat = lat;
	}
	

	public String toString() {
		// TODO Auto-generated method stub
		return  _lat + "," + _lon  +"," + _alt;
	}
	/*
	 * (non-Javadoc)
	 * @see Geom.Geom_element#distance3D(Geom.Point3D)
	 */
	@Override
	public double distance3D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * (non-Javadoc)
	 * @see Geom.Geom_element#distance3D(Geom.Point3D)
	 */
	@Override
	public double distance2D(Point3D p) {
		// TODO Auto-generated method stub
		return 0;
	}

}

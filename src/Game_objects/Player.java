package Game_objects;

import Geom.GpsPoint;
import Geom.Point3D;
import Maps.Map;
import Maps.Pixel;

public class Player {

	public int id;
	public GpsPoint GPSLocation;
	public Pixel PIXELLocation;
	public int score;
	public int killBYghosts;
	public int outOFbox;
	
	public Player(GpsPoint GPSLocation,Map map)
	{
		this.GPSLocation=GPSLocation;
		this.PIXELLocation= new Pixel(new Point3D(GPSLocation.getLon(),GPSLocation.getLat(),GPSLocation.getAlt()),map);
		this.id=0;
		this.score=0;
		this.killBYghosts=0;
		this.outOFbox=0;
		
	}

	public GpsPoint getGPSLocation() {
		return GPSLocation;
	}

	public void setGPSLocation(GpsPoint gPSLocation) {
		GPSLocation = gPSLocation;
	}

	public Pixel getPIXELLocation() {
		return PIXELLocation;
	}

	public void setPIXELLocation(Pixel pIXELLocation) {
		PIXELLocation = pIXELLocation;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getKillBYghosts() {
		return killBYghosts;
	}

	public void setKillBYghosts(int killBYghosts) {
		this.killBYghosts = killBYghosts;
	}

	public int getOutOFbox() {
		return outOFbox;
	}

	public void setOutOFbox(int outOFbox) {
		this.outOFbox = outOFbox;
	}

	public int getId() {
		return id;
	}
}

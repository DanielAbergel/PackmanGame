
/**
 * 
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 * 
 * this class represent a location of a point on the screen by pixels.
 */
public class Pixel {
	
	private double _PixelX;
	private double _PixelY;
	
	/**
	 * this constructor build the x&y pixels values of the point.
	 * @param PixelX represent the x value of the point.
	 * @param PixelY represent the y value of the point.
	 */
	public  Pixel(double PixelX , double PixelY){
		set_PixelX(PixelX);
		set_PixelY(PixelY);
	}

	/**
	 * this function convert a gps point to pixels.
	 * @param GPS represent thr gps point.
	 * @param map represent thr map
	 */
	public Pixel(Point3D GPS ,Map map) {
		
		Pixel temp = map.GPSPoint2Pixel(GPS);
		set_PixelX(temp._PixelX);
		set_PixelY(temp._PixelY);
		
	}
	/**
	 * copy constructor
	 * @param other represent the original point.
	 */
	public Pixel(Pixel other)
	{
		set_PixelX(other.get_PixelX());
		set_PixelY(other.get_PixelY());
	}
	public double get_PixelX() {
		return _PixelX;
	}

	public void set_PixelX(double _PixelX) {
		this._PixelX = _PixelX;
	}

	public double get_PixelY() {
		return _PixelY;
	}

	public void set_PixelY(double _PixelY) {
		this._PixelY = _PixelY;
	}
	
	public Pixel Subtract(Pixel p)
	{
		return new Pixel(this._PixelX-p.get_PixelX(), this._PixelY-p.get_PixelY());
	}
	public Pixel Add(Pixel p)
	{
		return new Pixel(this._PixelX+p.get_PixelX(), this._PixelY+p.get_PixelY());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this._PixelX  + " , " + this._PixelY;
	}
	
}

package Maps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import Coords.MyCoords;
import Game_objects.Fruit;
import Game_objects.Packman;
import Geom.Point3D;
/**
 * 
 * @author Netanel Ben-Isahar
 * @author daniel abergel
 * this class represent a map with objects that represent the edges of the map.
 * it also support some conversion functions.
 *
 */
public class Map 
{

	private Point3D StartPoint ; 
	private Point3D EndPoint ; 
	private Pixel FrameSize ; 
	public BufferedImage myImage;

/**
 * this constructor build the map with the appropriate values.
 */
	public Map()
	{
		StartPoint =  new Point3D(35.20234,32.10584,0); 
		EndPoint = new Point3D(35.21237,32.10193,0);
		FrameSize = new Pixel(1433, 642);
		StartPoint.GPS2Meter();
		EndPoint.GPS2Meter();
		try {
			myImage = ImageIO.read(new File("Ariel1.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * this function calculate the gps location after adding pixels distance
	 * @param PixelXMove represent the move on the x axis
	 * @param PixelYMove represent the move on the y axis
	 * @return the new gps point after the move
	 */

	public  Point3D Pixel2GPSPoint( double PixelXMove , double PixelYMove )
	{
		Pixel  p  =  Pixel2Meter(FrameSize.get_PixelX(), FrameSize.get_PixelY());
		PixelXMove = PixelXMove * p.get_PixelX(); 
		PixelYMove = PixelYMove * p.get_PixelY();
		Point3D result = new Point3D(PixelXMove + StartPoint.x(),PixelYMove + StartPoint.y(),0);
		result.Meter2GPS();
		return result ;
	}

	/**
	* this function calculate the pixel location after adding pixels distance
	 * @param PixelXMove represent the move on the x axis
	 * @param PixelYMove represent the move on the y axis
	 * @return the new pixel point after the move
	 */
	private Pixel Pixel2Meter(double PixelXSize , double PixelYSize )
	{


		double disX = EndPoint.x() - StartPoint.x() ;
		double disY = EndPoint.y() - StartPoint.y();
		double PixelAsMeterX = disX / PixelXSize ; 
		double PixelAsMeterY = disY / PixelYSize ;
		Pixel _Pixel = new Pixel(PixelAsMeterX, PixelAsMeterY);
		return _Pixel ;
	}
	/**
	 * this function convert between gps point to pixels
	 * @param Point represent the 3D point
	 * @return the pixel location
	 */
	public  Pixel GPSPoint2Pixel(Point3D Point)
	{
		Point.GPS2Meter();
		Pixel  Worth  =  Pixel2Meter(FrameSize.get_PixelX(), FrameSize.get_PixelY());
		double disX = Point.x() - StartPoint.x() ;
		double disY = Point.y() - StartPoint.y();
		double dx = disX / Worth.get_PixelX() ; 
		double dy = disY / Worth.get_PixelY() ; 
		Point.Meter2GPS();
		Pixel Pix = new Pixel(dx, dy);
		if(isVaildPixel(Pix)) 
			return Pix ; 
		else 
		{
//			throw new RuntimeException("The Pixel is out of bounds");
		} 
		return Pix ; 
	}
	/**
	 * this function is checking if the point is in our area
	 * @param p represent the pixel point
	 * @return true if the point is in our area
	 */

	private boolean isVaildPixel(Pixel p)
	{
		Pixel PSubtract = FrameSize.Subtract(p) ;
		return PSubtract.get_PixelX() > 0 && PSubtract.get_PixelY() > 0;
	}
	
	/**
	 * this function calculate the correct pixels position after rescaling the picture 
	 * @param p represent the new end point
	 * @param PackArr arraylist of packmans
	 * @param FruitArr arraylist of fruits
	 */
	public void ChangeFrameSizePacman(Pixel p , ArrayList<Packman> PackArr  ,ArrayList<Fruit> FruitArr)
	{

		
		FrameSize.set_PixelX(p.get_PixelX());
		FrameSize.set_PixelY(p.get_PixelY());

		
		for (int i = 0; i < PackArr .size(); i++) {
			Pixel temp = GPSPoint2Pixel(new Point3D(PackArr.get(i)._GPS));
			PackArr .get(i)._PixelLocation.set_PixelX(temp.get_PixelX());
			PackArr .get(i)._PixelLocation.set_PixelY(temp.get_PixelY());
			
		}	
		for (int i = 0; i < FruitArr .size(); i++) {
			Pixel temp = GPSPoint2Pixel(new Point3D(FruitArr.get(i)._GPS));
			FruitArr .get(i)._PixelLocation.set_PixelX(temp.get_PixelX());
			FruitArr .get(i)._PixelLocation.set_PixelY(temp.get_PixelY());
			
		}	
	}
	
	
}

package Game_objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represent an array list of fruits.
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 *
 */
public class Fruits extends ArrayList<Fruit>{
	
	/**
	 * This function return an iterator of the array list.
	 * @return iterator of the fruit array list.
	 */
	@Override
	public Iterator<Fruit> iterator() {
		// TODO Auto-generated method stub
		return super.iterator();
	}
	
	/**
	 * toString of the fruit array list.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 StringBuilder StringB  = new StringBuilder();
		 Iterator<Fruit> it = this.iterator();
		 while(it.hasNext())
		 StringB.append(it.next().toString() + "\n");
		 return StringB.toString();
	}
	
}

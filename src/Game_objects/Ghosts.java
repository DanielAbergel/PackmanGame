package Game_objects;

import java.util.ArrayList;
import java.util.Iterator;

public class Ghosts extends ArrayList<Ghost>{
	
	/**
	 * This function return an iterator of the array list.
	 * @return iterator of the Ghosts array list.
	 */
	@Override
	public Iterator<Ghost> iterator() {
		// TODO Auto-generated method stub
		return super.iterator();
	}
	
	/**
	 * toString of the Ghosts array list.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 StringBuilder StringB  = new StringBuilder();
		 Iterator<Ghost> it = this.iterator();
		 while(it.hasNext())
		 StringB.append(it.next().toString() + "\n");
		 return StringB.toString();
	}
	
}



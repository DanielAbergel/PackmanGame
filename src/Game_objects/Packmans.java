package Game_objects;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 * 
 * this class represnt a list of pacmans.
 */
public class Packmans extends ArrayList<Packman> {

	@Override
	public Iterator<Packman> iterator() {
		// TODO Auto-generated method stub
		return super.iterator();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 StringBuilder StringB  = new StringBuilder();
		 Iterator<Packman> it = this.iterator();
		 while(it.hasNext())
		 StringB.append(it.next().toString() + "\n");
		 return StringB.toString();
	}
	

}

package Game_objects;

import java.util.ArrayList;

import GUI.Line;
/**
 * 
 * @author Netanel Ben-Isahar
 * @author daniel abargel
 * 
 * this class represnt a pacman path is a game.
 * it use a list of lines.
 */
public class Path {
	
	public ArrayList<Line> Lines ; 
	
	public Path() 
	{
		// TODO Auto-generated constructor stub
		Lines = new ArrayList<Line> ();
	}
	
	@Override
	public String toString() {
		String toString = "";
		for (int i = 0; i < Lines.size(); i++) {
			toString += " --> " +Lines.get(i).toString();
		}
		return toString;
	}
	

}

package File_format;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Robot.Game;
import Robot.Play;

class Board2GameTest {

	Play Server ; 
	Game game ; 
	ArrayList<String> solutionExpected ; 
	ArrayList<String> solution; 

	void init() 
	{
		game = new Game();
		solution = new ArrayList<String>();
		solutionExpected = new ArrayList<String>();
		String file_name = "data/Ex4_OOP_example2.csv";
		Server = new Play(file_name);
	}

	@Test
	void testBoard2Game() {

		init();
		solution = Server.getBoard();
		Board2Game B2G = new Board2Game();
		B2G.SetGame(game, Server.getBoard());
		solutionExpected = game.getGame();
		if(solution.size() != solutionExpected.size()) fail("no valid solution");
		for (int i = 0; i < solution.size(); i++) 
		{
			String[] solutionStr  = solution.get(i).split(",");
			String[] solutionExpectedStr  = solutionExpected.get(i).split(",");
			for (int j = 0; j < solutionExpectedStr.length; j++) {
				if(j == 2)if(!solutionStr[j].equals(solutionExpectedStr[j+1]))  fail("no valid solution");
				else if(j == 3) if(!solutionStr[j].equals(solutionExpectedStr[j-1])) fail("no valid solution");
				else {if(!solutionStr[j].equals(solutionExpectedStr[j])) fail("no valid solution");}
			}
		}
	}

}

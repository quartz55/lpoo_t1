package maze.Test;

import java.util.ArrayList;

import maze.Game;
import maze.GameObjects.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTester {
	
	
	@Test
	public void testMove(){

		int[][] testMaze = {
				{1,1,1,1},
				{1,0,0,2},
				{1,1,1,1}
		};
		String[] testInput = {"d"};
		Hero testHero = new Hero(1,1);
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), new Shield(-1,-1), new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();
		
		assertEquals(testHero.getX(), 2);
	}

	@Test
	public void testNotMove(){

		int[][] testMaze = {
				{1,1,1,1},
				{1,0,0,2},
				{1,1,1,1}
		};
		Hero testHero = new Hero(1,1);
		String[] testInput = {"a"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), new Shield(-1,-1), new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();
		
		assertEquals(testHero.getX(), 1);
	}

	@Test
	public void testSword(){

		int[][] testMaze = {
				{1,1,1,1},
				{1,0,0,2},
				{1,1,1,1}
		};
		Hero testHero = new Hero(1,1);
		Sword testSword = new Sword(2,1);
		String[] testInput = {"d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, testSword, new Shield(-1,-1), new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();
		
		assertTrue(testHero.hasSword());
		assertTrue(testSword.isPickedUp());
	}

	@Test
	public void testDead(){

		int[][] testMaze = {
				{1,1,1,1,1},
				{1,0,0,0,2},
				{1,1,1,1,1}
		};

		Hero testHero = new Hero(1,1);
		ArrayList<Dragon> testDragons = new ArrayList<Dragon>();
		testDragons.add(new Dragon(3,1,0));

		String[] testInput = {"d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), new Shield(-1,-1), testDragons, new ArrayList<Dart>());
		testGame.loop();
		
		assertTrue(testHero.isDead());
	}

	@Test
	public void testKillDragon(){

		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,1,1,1,1,1}
		};

		Hero testHero = new Hero(1,1);
		ArrayList<Dragon> testDragons = new ArrayList<Dragon>();
		testDragons.add(new Dragon(4,1,0));

		String[] testInput = {"d","d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(2,1), new Shield(-1,-1), testDragons, new ArrayList<Dart>());
		testGame.loop();
		
		assertFalse(testHero.isDead());
		assertTrue(testDragons.isEmpty());
	}

	@Test
	public void testWin(){

		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,1,1,1,1,1}
		};

		Hero testHero = new Hero(1,1);
		ArrayList<Dragon> testDragons = new ArrayList<Dragon>();
		testDragons.add(new Dragon(4,1,0));

		String[] testInput = {"d","d","d","d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(2,1), new Shield(-1,-1), testDragons, new ArrayList<Dart>());
		testGame.loop();
		
		assertTrue(testHero.hasSword());
		assertTrue(testDragons.isEmpty());
		assertTrue(testGame.getWin());
	}

	@Test
	public void testNotWin(){

		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,0,0,0,0,1},
				{1,0,0,0,0,1},
				{1,1,1,1,1,1}
		};

		Hero testHero = new Hero(1,1);
		ArrayList<Dragon> testDragons = new ArrayList<Dragon>();
		testDragons.add(new Dragon(1,3,0));

		String[] testInput = {"d","d","d","d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(1,2), new Shield(-1,-1), testDragons, new ArrayList<Dart>());
		testGame.loop();

		assertFalse(testHero.hasSword());
		assertFalse(testDragons.isEmpty());
		assertFalse(testGame.getWin());
		assertEquals(testHero.getX(), 4);
		assertEquals(testHero.getY(), 1);
	}
}

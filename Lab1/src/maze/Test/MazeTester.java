package maze.Test;

import java.util.ArrayList;

import maze.logic.Game;
import maze.logic.GameObjects.*;
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

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
	}


	@Test
	public void testShield(){

		int[][] testMaze = {
				{1,1,1,1},
				{1,0,0,2},
				{1,1,1,1}
		};
		Hero testHero = new Hero(1,1);
		Shield testShield = new Shield(2,1);
		String[] testInput = {"d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), testShield, new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();
		
		assertTrue(testHero.hasShield());
		assertTrue(testShield.isPickedUp());

		testGame.getGameGraphics().close();
	}

	@Test
	public void testDart(){

		int[][] testMaze = {
				{1,1,1,1},
				{1,0,0,2},
				{1,1,1,1}
		};
		Hero testHero = new Hero(1,1);
		ArrayList<Dart> testDarts = new ArrayList<Dart>();
		testDarts.add(new Dart(2,1));
		String[] testInput = {"d"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), new Shield(-1,-1), new ArrayList<Dragon>(), testDarts);
		testGame.loop();
		
		assertTrue(testHero.getDarts() > 0);
		assertTrue(testDarts.isEmpty());

		testGame.getGameGraphics().close();
	}
	
	@Test
	public void testFire(){
		int[][] testMaze = {
				{1,1,1,1,1,1,1},
				{1,0,0,0,0,0,2},
				{1,0,0,0,0,0,1},
				{1,0,0,0,0,0,1},
				{1,0,0,0,0,0,1},
				{1,0,0,0,0,0,1},
				{1,0,0,0,0,0,1},
				{1,1,1,1,1,1,1}
		};
		Hero testHero = new Hero(3,3);
		ArrayList<Dart> testDarts = new ArrayList<Dart>();
		testDarts.add(new Dart(3,4));
		testDarts.add(new Dart(3,4));
		testDarts.add(new Dart(3,4));
		testDarts.add(new Dart(3,4));
		ArrayList<Dragon> testDragons = new ArrayList<Dragon>();
		testDragons.add(new Dragon(3,1,0));
		testDragons.add(new Dragon(3,6,0));
		testDragons.add(new Dragon(1,3,0));
		testDragons.add(new Dragon(5,3,0));
		String[] testInput = {"s","w","s","w","s","w","s","w","f","a","f","w","f","d","f","s"};
		
		Game testGame = new Game(testMaze, testInput, testHero, new Sword(-1,-1), new Shield(-1,-1), testDragons, testDarts);
		testGame.loop();
		
		assertTrue(testHero.getDarts() == 0);
		assertTrue(testDarts.isEmpty());
		assertTrue(testDragons.isEmpty());

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
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

		testGame.getGameGraphics().close();
	}
	
	@Test
	public void testRandomMaze(){
		Maze testMaze1 = new Maze();
		testMaze1.generateRandomMaze(11, 11);
		Maze testMaze2 = new Maze();
		testMaze2.generateRandomMaze(11, 11);
		assertTrue(testMaze1.getMaze() != testMaze2.getMaze());
	}
	
	@Test
	public void testDragonMove(){
		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,0,0,0,0,1},
				{1,0,0,0,0,1},
				{1,1,1,1,1,1}
		};
		int xi = 2;
		int yi = 2;
		Dragon testDragon = new Dragon(xi,yi,1);
		
		while(testDragon.getX() == xi){
			testDragon.update(new Maze(testMaze));
		}
		assertTrue(testDragon.getX() != xi);

		while(testDragon.getY() == yi){
			testDragon.update(new Maze(testMaze));
		}
		assertTrue(testDragon.getY() != yi);
	}

	@Test
	public void testDragonSleep(){
		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,0,0,0,0,1},
				{1,0,0,0,0,1},
				{1,1,1,1,1,1}
		};
		Dragon testDragon = new Dragon(2,2,2);
		
		while(!testDragon.isSleeping()){
			testDragon.update(new Maze(testMaze));
		}
		assertTrue(testDragon.isSleeping());

		while(testDragon.isSleeping()){
			testDragon.update(new Maze(testMaze));
		}
		assertTrue(!testDragon.isSleeping());
	}

	@Test
	public void testDragonFire(){
		int[][] testMaze = {
				{1,1,1,1,1,1},
				{1,0,0,0,0,2},
				{1,0,0,0,0,1},
				{1,0,0,0,0,1},
				{1,1,1,1,1,1}
		};
		Dragon testDragon = new Dragon(2,2,2);
		
		while(!testDragon.isBreathingFire()){
			testDragon.update(new Maze(testMaze));
		}
		assertTrue(testDragon.isBreathingFire());
	}
	
	@Test
	public void testRandomPositions(){
		Maze builder = new Maze();
		builder.generateRandomMaze(11, 11);

		String[] testInput = {"new game"};
		
		Game testGame = new Game(builder.getMaze(), testInput, new Hero(-1,-1), new Sword(1,2), new Shield(-1,-1), new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();
		
		testGame.getGameGraphics().close();
	}
	
	
	@Test
	public void testLoadSave(){
		Maze builder = new Maze();
		builder.generateRandomMaze(11, 11);

		String[] testInput = {"new game", "save game", "load game"};
		
		Game testGame = new Game(builder.getMaze(), testInput, new Hero(-1,-1), new Sword(1,2), new Shield(-1,-1), new ArrayList<Dragon>(), new ArrayList<Dart>());
		testGame.loop();

		testGame.getGameGraphics().close();
	}
}

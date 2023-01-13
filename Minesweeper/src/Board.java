import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

/**

	Represents a Game Of Life grid.

	Coded by: Alex R Zhang
	Modified on: 11/9/21

*/

public class Board {

	// Add a 2D array field to represent the Game Of Life grid.
	
	Cell[][] grid;
	
	
	/**
	 * Initialized the Game of Life grid to an empty 20x20 grid.
	 */
	public Board() {
		grid = new Cell[20][20];
		init();
	}

	
	
	/**
	 * Initializes the Game of Life grid to a 20x20 grid and fills it with data from the file given.
	 * 
	 * @param filename The path to the text file.
	 */
	public Board(String filename) {
		grid = new Cell[20][20];
//		this.readData(filename, grid);
	}

	public void init() {
		
		int[] x = new int[400];
		int[] mines = new int[100];
		for(int q = 0; q<20; q++) {
			for (int w = 0; w<20; w++) {
				grid[q][w] = new Cell(new Point(q,w),this);
			}
		}
		for (int i = 0; i<400; i++)	{
			x[i] = i;
		}
		for (int j = 0; j<100; j++) {
			int r = (int)(Math.random()*(400-j));
			mines[j] = x[r];
			for (int a = r; a<400-j-1; a++) {
				x[a] = x[a+1];
			}
		}
		
		
		
		for (int b = 0; b<100; b++) {
			grid[mines[b]/20][mines[b]%20].setVal(-1);
		}
		int[][] vals = this.neighbors();
		for (int q = 0; q<20; q++) {
			for (int w = 0; w<20; w++) {
				if (grid[q][w].getVal()!=-1) {
					grid[q][w].setVal(vals[q][w]);
				}
			}
		}
	}
	
	/**
	 * Runs a single step within the Game of Life by applying the Game of Life rules on
	 * the grid.
	 */
	public void step() {
		
		
//		int[][] neighbors = neighbors();
//		for (int j = 0; j<grid.length; j++) {
//			for (int i = 0; i<grid[j].length; i++) {
//				if (((neighbors[j][i]>3 || neighbors[j][i]<2)&& grid[j][i]) || 
//						((neighbors[j][i]==3 || neighbors[j][i]==3) && !grid[j][i])) {
//					toggleCell(j,i);
//				}
//			}
//		}
	}
	
	
	/**
	 * Runs n steps within the Game of Life.
	 * @param n The number of steps to run.
	 */
	public void step(int n) {
		for (int x = 0; x<n; x++) {
			step();
		}
	}
	
	
	
	/**
	 * Formats this Life grid as a String to be printed (one call to this method returns the whole multi-line grid)
	 * 
	 * @return The grid formatted as a String.
	 */
	public String toString() {
		
		String out = "[";
		
		for (int i = 0; i<grid.length; i++) {
			out+="[";
			for (int j = 0; j<grid[i].length; j++) {
				
				out+=(grid[i][j].getVal()+", ");
			}
			out+="], \n";
		}
		out+="]";
		
		return out;
	}
	
	
	
	/**
	 * (Graphical UI)
	 * Draws the grid on a PApplet.
	 * The specific way the grid is depicted is up to the coder.
	 * 
	 * @param marker The PApplet used for drawing.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		
		
		
		float rw = width / grid[0].length;
		float rh = height / grid.length;
		
		for (int i = 0; i<grid.length; i++) {
			for (int j = 0; j<grid.length; j++) {
				
				float rx = x+j*rw;
				float ry = y+i*rh;
				
				if (grid[i][j].flipped) {
					marker.fill(200);
				}
				else {
					marker.fill(255);
				}
				grid[i][j].draw(marker,rx, ry, rw,rh);
			}
		}
		
	}
	
	
	
	/**
	 * (Graphical UI)
	 * Determines which element of the grid matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
				
		float rw = width / grid[0].length;
		float rh = height / grid.length;
		
		for (int i = 0; i<grid.length; i++) {
			for (int j = 0; j<grid.length; j++) {
				
				float rx = x+j*rw;
				float ry = y+i*rh;
				
				if (p.x>rx && p.x<rx+rw && p.y>ry && p.y<ry+rh) {
					return new Point (i,j);
				}
			}
		} 
		return null;
	}
	
	/**
	 * (Graphical UI)
	 * Toggles a cell in the game of life grid between alive and dead.
	 * This allows the user to modify the grid.
	 * 
	 * @param i The x coordinate of the cell in the grid.
	 * @param j The y coordinate of the cell in the grid.
	 */
//	public void toggleCell(int i, int j) {
//		grid[i][j] = !grid[i][j];
//	}

	

	// Reads in array data from a simple text file containing asterisks (*)
	public void readData (String filename, boolean[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for(int i = 0; i < line.length(); i++)
						if (count < gameData.length && i < gameData[count].length && line.charAt(i)=='*')
							gameData[count][i] = true;

					count++;
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
	
	public Cell[][] getGrid(){
		return grid;
	}
	//loop
	//quadruple fors
	// start1, start2, start3, start4
	// don't check the middle cell
	private int[][] neighbors() {
		int[][] num = new int[grid.length][grid[0].length];
		for (int x = 0; x<grid.length; x++) {
			for (int y = 0; y<grid[x].length; y++) {
				num[x][y] = grid[x][y].numNeighbors();
			}
		}
		
		return num;
	}



	public void endGame() {
		for (int i = 0; i<20; i++) {
			for (int j = 0; j<20; j++) {
				if (grid[i][j].text == "*" && grid[i][j].getVal()!=-1) {
					grid[i][j].color = 50;
				}
				else {
					grid[i][j].flip();
				}
				
				if (grid[i][j].getVal() == -1) {
					grid[i][j].color = 100;
					grid[i][j].mark();
				}
				
			}
		}
		
	}
	


	
	
}
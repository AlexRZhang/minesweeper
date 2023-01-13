import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
public class Cell {
	
	Point p;
	Board b;
	int v = -2;
	boolean flipped = false;
	String text = "";
	int color = 255;
	public Cell(Point point, Board board) {
		p = point;
		b = board;
	}
	
	public void draw (PApplet marker, float x, float y, float width, float height) {
		marker.fill(color);
		marker.rect(x, y, width, height);
		marker.fill(0);
		marker.text(text, x+width/3,y+height/2);
	}
	public void resize(Point np) {
		p = np;
	}
	
	public int getVal() {
		return v;
	}
	
	public void setVal(int x) {
		v = x;
		
	}

	public void flip() {
		flipped = true;
		text=""+v;
		if (v==0) {
//			ArrayList <Cell> r = neighbors();
//			System.out.println(r.indexOf(0));
			for (Cell c: neighbors()) {
				if(!c.flipped) {
					c.flip();
				}
			}
		}
		if (v == -1) {
			text ="*";
		}
		
		
	}
	
	public void mark() {
		
		if (!flipped && text=="*") {
			text = "";
		}
		else if (!flipped) {
			text = "*";
		}
		
	}
	public ArrayList <Cell> neighbors() {
		Cell[][] grid = b.getGrid();
		ArrayList <Cell> r = new ArrayList <Cell>(0);
		int x = p.x;
		int y = p.y;
		if(x>0) {
			if (grid[x-1][y].getVal() != -1) {
				r.add(grid[x-1][y]);
			}
			if (y>0) {
				if (grid[x-1][y-1].getVal()!= -1) {
					r.add(grid[x-1][y-1]);
				}
			}
			if (y<grid[x].length-1) {
				if (grid[x-1][y+1].getVal() != -1) {
					r.add(grid[x-1][y+1]);
				}
			}
		}
		if(x<grid.length-1) {
			if (grid[x+1][y].getVal() != -1) {
				r.add(grid[x+1][y]);
			}
			if (y>0) {
				if (grid[x+1][y-1].getVal() != -1) {
					r.add(grid[x+1][y-1]);
				}
			}
			if (y<grid[x].length-1) {
				if (grid[x+1][y+1].getVal() != -1) {
					r.add(grid[x+1][y+1]);
				}
			}
		}
		if (y>0) {
			if (grid[x][y-1].getVal() != -1) {
				r.add(grid[x][y-1]);
			}
		}
		if (y<grid[x].length-1) {
			if (grid[x][y+1].getVal() != -1) {
				r.add(grid[x][y+1]);
			
			}
				
			
				
			}
			
			return r;
		}
	
	public int numNeighbors() {
		
		Cell[][] grid = b.getGrid();
		int num = 0;
		int x = p.x;
		int y = p.y;
		if(x>0) {
			if (grid[x-1][y].getVal() == -1) {
				num++;
			}
			if (y>0) {
				if (grid[x-1][y-1].getVal() == -1) {
					num++;
				}
			}
			if (y<grid[x].length-1) {
				if (grid[x-1][y+1].getVal() == -1) {
					num++;
				}
			}
		}
		if(x<grid.length-1) {
			if (grid[x+1][y].getVal() == -1) {
				num++;
			}
			if (y>0) {
				if (grid[x+1][y-1].getVal() == -1) {
					num++;
				}
			}
			if (y<grid[x].length-1) {
				if (grid[x+1][y+1].getVal() == -1) {
					num++;
				}
			}
		}
		if (y>0) {
			if (grid[x][y-1].getVal() == -1) {
				num++;
			}
		}
		if (y<grid[x].length-1) {
			if (grid[x][y+1].getVal() == -1) {
				num++;
			
			}
				
			
				
			}
		
		return num;

		
	}
			
		
	
		
	
}

package de.dennisr.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import de.dennisr.core.GameCore;
import de.dennisr.gui.Font;

public class SudokuModel {

	private BufferedImage sudokuTile;
	private SudokuSections[][] sudokuSections;
	private int posX, posY, level, size, sizePerSection, tileSize;
	private Random random;
	
	private int[][][] preData = {
								 {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
								 {{7, 8, 9}, {1, 2, 3}, {4, 5, 6}},
								 {{4, 5, 6}, {7, 8, 9}, {1, 2, 3}},
								 
								 {{2, 3, 4}, {5, 6, 7}, {8, 9, 1}},
								 {{8, 9, 1}, {2, 3, 4}, {5, 6, 7}},
								 {{5, 6, 7}, {8, 9, 1}, {2, 3, 4}},
								 
								 {{3, 4, 5}, {6, 7, 8}, {9, 1, 2}},
								 {{9, 1, 2}, {3, 4, 5}, {6, 7, 8}},
								 {{6, 7, 8}, {9, 1, 2}, {3, 4, 5}}
								};
	
	public SudokuModel(int posX, int posY, int level, int size, int sizePerSection, int tileSize){
		this.posX = posX;
		this.posY = posY;
		this.level = level;
		this.size = size;
		this.sizePerSection = sizePerSection;
		this.tileSize = tileSize;
		this.random = new Random();
	
		this.sudokuSections = new SudokuSections[size][size];
		
		sudokuTile = GameCore.getInstance().getImageFromRes("/gfx/sudokuTile.jpg");
		
		this.generate(level);
	}

	private void generate(int level) {
		randPreData(random.nextInt(20) + 10);
		
		for(int y = 0; y < size; y++){
			for(int x = 0; x < size; x++){
				this.sudokuSections[y][x] = new SudokuSections(sizePerSection);
				this.sudokuSections[y][x].setData(this.preData[y * this.size + x]);
				
				
				this.sudokuSections[y][x].setSolved(generateSolvePattern(level));
			}
		}
		
	}
	
	private boolean[][] generateSolvePattern(int level) {
		
		boolean[][] res = new boolean[sizePerSection][sizePerSection]; 
		
		for(int y = 0; y < sizePerSection; y++){
			for(int x = 0; x < sizePerSection; x++){
				res[x][y] = (random.nextInt(15) > level);
			}
		}
		
		return res;
	}

	private void randPreData(int seedLevel) {
		
		for(int i = 0; i < seedLevel; i++){

			int section = random.nextInt(this.size);

			int innerSection1 = random.nextInt(this.sizePerSection);
			int innerSection2 = random.nextInt(this.sizePerSection);
			
			int[][] row1 = preData[section * this.size + innerSection1];
			int[][] row2 = preData[section * this.size + innerSection2];
			
			this.preData[section * this.size + innerSection1] = row2;
			this.preData[section * this.size + innerSection2] = row1;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		for(int y = 0; y < this.size; y++){
			for(int x = 0; x < this.size; x++){
				SudokuSections ss = this.sudokuSections[x][y];
				
				for(int sy = 0; sy < this.sizePerSection; sy++){
					for(int sx = 0; sx < this.sizePerSection; sx++){
						
						g.drawImage(this.sudokuTile, (x*size*tileSize + (tileSize/3)*x) + sx*tileSize + posX, (y*size*tileSize + (tileSize/3)*y) + sy*tileSize + posY, tileSize, tileSize, null);
						
						if(ss.isSolvedAt(sx, sy)){
							Font f = ss.getFont(sx, sy);
							f.render(g, (x*size*tileSize + (tileSize/3)*x) + sx*tileSize + posX + f.getCenter()[0] + 5, (y*size*tileSize + (tileSize/3)*y) + sy*tileSize + posY + f.getCenter()[1] + 5);
						}else{
							if(ss.getEnteredDataAt(sx, sy) != 0){
								Font f = ss.getEntredFont(sx, sy);
								f.render(g, (x*size*tileSize + (tileSize/3)*x) + sx*tileSize + posX + f.getCenter()[0] + 5, (y*size*tileSize + (tileSize/3)*y) + sy*tileSize + posY + f.getCenter()[1] + 5);
							}
						}
						
					}
				}
			}
		}
		
	}
	
	public void setNumberAt(int sectionX, int sectionY, int posX, int posY, int number){
		if(!this.sudokuSections[sectionX][sectionY].isSolvedAt(posX, posY)){
			this.sudokuSections[sectionX][sectionY].setEnteredDataAt(posX, posY, number);
			if(checkWin()){
				GameCore.getInstance().getLobby().setWon(true);
			}
		}
	}

	private boolean checkWin() {
		for(int y = 0; y < this.size; y++){
			for(int x = 0; x < this.size; x++){
				SudokuSections ss = this.sudokuSections[x][y];
				if(!ss.isCompleted()){
					return false;
				}
			}
		}
		
		return true;
	}

	public SudokuSections[][] getSudokuSections() {
		return sudokuSections;
	}

	public void setSudokuSections(SudokuSections[][] sudokuSections) {
		this.sudokuSections = sudokuSections;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSizePerSection() {
		return sizePerSection;
	}

	public void setSizePerSection(int sizePerSection) {
		this.sizePerSection = sizePerSection;
	}

	public BufferedImage getSudokuTile() {
		return sudokuTile;
	}

	public void setSudokuTile(BufferedImage sudokuTile) {
		this.sudokuTile = sudokuTile;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}
	
	
	
}

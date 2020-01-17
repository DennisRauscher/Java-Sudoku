package de.dennisr.game;

import de.dennisr.gui.Font;

public class SudokuSections {

	private int[][] data;
	private int[][] enteredData;
	private boolean[][] solved;
	private Font[][] solutionTxt, enteredTxt;
	
	private int size;
	
	public SudokuSections(int size){
		this.size = size;
		this.data = new int[size][size];
		this.solved = new boolean[size][size];
		this.solutionTxt = new Font[size][size];
		this.enteredData = new int[size][size];
		this.enteredTxt = new Font[size][size];
	}
	
	public int getDataAt(int x, int y){
		return this.data[x][y];
	}
	
	public boolean isSolvedAt(int x, int y){
		return this.solved[x][y];
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
		regenerateFonts();
	}
	
	public void regenerateFonts(){
		for(int y = 0; y < this.size; y++){
			for(int x = 0; x< this.size; x++){
				solutionTxt[x][y] = new Font(data[x][y] + "");
				solutionTxt[x][y].setScale(2);
			}
		}
	}

	public Font getFont(int x, int y){
		return this.solutionTxt[x][y];
	}
	
	public boolean[][] getSolved() {
		return solved;
	}

	public void setSolved(boolean[][] solved) {
		this.solved = solved;
	}

	public boolean contains(int number) {
		for(int[] d : this.data){
			for(int dx : d){
				if(dx == number){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containedInLineX(int lineX, int number) {
		for(int y = 0; y < this.size; y++){
			if(this.data[lineX][y] == number){
				return true;
			}
		}
		return false;
	}
	
	public boolean containedInLineY(int lineY, int number) {
		for(int x = 0; x < this.size; x++){
			if(this.data[x][lineY] == number){
				return true;
			}
		}
		return false;
	}
	
	public void setDataAt(int x, int y, int data){
		this.data[x][y] = data;
	}
	
	public void resetData(){
		this.data = new int[this.size][this.size];
	}
	
	public void setEnteredDataAt(int x, int y, int data){
		this.enteredData[x][y] = data;
		this.enteredTxt[x][y] = new Font();		
		this.enteredTxt[x][y].setCurrentMap(this.enteredTxt[x][y].mapRed);
		this.enteredTxt[x][y].setText(data + "");
		this.enteredTxt[x][y].setScale(2);
	}

	public int getEnteredDataAt(int x, int y){
		return this.enteredData[x][y];
	}
	
	public Font getEntredFont(int x, int y){
		return this.enteredTxt[x][y];
	}

	public boolean isCompleted() {

		for(int y = 0; y < this.size; y++){
			for(int x = 0; x < this.size; x++){
				if(!this.solved[x][y]){
					if(this.enteredData[x][y] != this.data[x][y]){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
}

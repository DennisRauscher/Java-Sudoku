package de.dennisr.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.dennisr.core.GameCore;
import de.dennisr.engine.Drawable;
import de.dennisr.engine.Updateable;
import de.dennisr.gui.Font;

public class Lobby implements Drawable, Updateable{
	
	private BufferedImage background, fadeOut;
	
	private SudokuModel sm;
	private Selector selector;
	private boolean won;
	private Font wonText, addvice1Txt;
	
	private int level = 1;
	
	public Lobby(){
		this.background = GameCore.getInstance().getImageFromRes("/gfx/ingameBG.jpg");
		this.fadeOut    = GameCore.getInstance().getImageFromRes("/gfx/fadeOut.png");
		won = false;
		
		wonText = new Font();
		wonText.setCurrentMap(wonText.mapGreen);
		wonText.setText("You solved this Sudoku correctly!");
		
		addvice1Txt = new Font();
		addvice1Txt.setText("Press [SPACE] to go back to menu!");
		
	}
	
	public void preInit(){
		sm = new SudokuModel(GameCore.WIN_WIDTH/2 - (3*3 * 45 + 2*(45/3))/2, GameCore.WIN_HEIGHT/2 - (3*3 * 45 + 2*(45/3))/2, level, 3, 3, 45);
		selector = new Selector(sm);
	}

	@Override
	public void Update() {
		if(!won){
			this.selector.Update();
		}else{
			if(GameCore.getInstance().getInputManager().space.isJustPressed()){
				GameCore.getInstance().restartGame();
			}
		}
	}

	@Override
	public void Draw(Graphics2D g) {
		g.drawImage(this.background, 0, 0, GameCore.WIN_WIDTH, GameCore.WIN_HEIGHT, null);
		
		sm.draw(g);
		if(!won){
			selector.Draw(g);
		}else{
			g.drawImage(this.fadeOut, 0, GameCore.WIN_HEIGHT/2-(GameCore.WIN_HEIGHT/6), GameCore.WIN_WIDTH, GameCore.WIN_HEIGHT/3, null);
			g.drawImage(this.wonText.img, GameCore.WIN_WIDTH/2-this.wonText.getCenter()[0], GameCore.WIN_HEIGHT/2-this.wonText.getCenter()[1] -20, this.wonText.getWidth(), this.wonText.getHeight(), null);
			g.drawImage(this.addvice1Txt.img, GameCore.WIN_WIDTH/2-this.wonText.getCenter()[0], GameCore.WIN_HEIGHT/2-this.wonText.getCenter()[1] + 40, this.wonText.getWidth(), this.wonText.getHeight(), null);
		}
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
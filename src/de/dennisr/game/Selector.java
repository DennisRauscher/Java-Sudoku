package de.dennisr.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.dennisr.core.GameCore;
import de.dennisr.engine.Drawable;
import de.dennisr.engine.Updateable;

public class Selector implements Drawable, Updateable{

	private BufferedImage selectorSprite;
	private int posSx = 0, posSy = 0, posx = 0, posy = 0;
	private int sectionOffset = 5, innerOffset = 3;
	
	public enum SelectorMode { SECTIONS, TILES };
	private SelectorMode currentSelectorMode;
	
	private SudokuModel sm;
	
	public Selector(SudokuModel sm){
		this.sm = sm;
		selectorSprite = GameCore.getInstance().getImageFromRes("/gfx/ingameSelector.png");
		this.currentSelectorMode = SelectorMode.SECTIONS;
	}
	
	@Override
	public void Update() {
		if(GameCore.getInstance().getInputManager().enter.isJustPressed()){
			this.nextMode();
		}else if(GameCore.getInstance().getInputManager().esc.isJustPressed()){
			this.lastMode();
		}
		
		if(this.currentSelectorMode == SelectorMode.TILES){
			if(GameCore.getInstance().getInputManager().input_1.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 1);
			}else if(GameCore.getInstance().getInputManager().input_2.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 2);
			}else if(GameCore.getInstance().getInputManager().input_3.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 3);
			}else if(GameCore.getInstance().getInputManager().input_4.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 4);
			}else if(GameCore.getInstance().getInputManager().input_5.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 5);
			}else if(GameCore.getInstance().getInputManager().input_6.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 6);
			}else if(GameCore.getInstance().getInputManager().input_7.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 7);
			}else if(GameCore.getInstance().getInputManager().input_8.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 8);
			}else if(GameCore.getInstance().getInputManager().input_9.isJustPressed()){
				sm.setNumberAt(posSx, posSy, posx, posy, 9);
			}
		}
		
		if(GameCore.getInstance().getInputManager().left.isJustPressed()){
			if(this.currentSelectorMode == SelectorMode.SECTIONS){
				this.posSx -= 1;
				if(this.posSx < 0){
					this.posSx = 0;
				}
			}else if(this.currentSelectorMode == SelectorMode.TILES){
				this.posx -= 1;
				if(this.posx < 0){
					this.posx = 0;
				}
			}
		}else if(GameCore.getInstance().getInputManager().right.isJustPressed()){
			if(this.currentSelectorMode == SelectorMode.SECTIONS){
				this.posSx += 1;
				if(this.posSx > this.sm.getSize()-1){
					this.posSx = this.sm.getSize()-1;
				}
			}else if(this.currentSelectorMode == SelectorMode.TILES){
				this.posx += 1;
				if(this.posx > this.sm.getSizePerSection()-1){
					this.posx = this.sm.getSizePerSection()-1;
				}
			}
		}else if(GameCore.getInstance().getInputManager().up.isJustPressed()){
			if(this.currentSelectorMode == SelectorMode.SECTIONS){
				this.posSy -= 1;
				if(this.posSy < 0){
					this.posSy = 0;
				}
			}else if(this.currentSelectorMode == SelectorMode.TILES){
				this.posy -= 1;
				if(this.posy < 0){
					this.posy = 0;
				}
			}
		}else if(GameCore.getInstance().getInputManager().down.isJustPressed()){
			if(this.currentSelectorMode == SelectorMode.SECTIONS){
				this.posSy += 1;
				if(this.posSy > this.sm.getSize()-1){
					this.posSy = this.sm.getSize()-1;
				}
			}else if(this.currentSelectorMode == SelectorMode.TILES){
				this.posy += 1;
				if(this.posy > this.sm.getSizePerSection()-1){
					this.posy = this.sm.getSizePerSection()-1;
				}
			}
		}
	}

	private void lastMode() {
		if(this.currentSelectorMode == SelectorMode.TILES){
			this.currentSelectorMode = SelectorMode.SECTIONS;
		}
	}

	private void nextMode() {
		if(this.currentSelectorMode == SelectorMode.SECTIONS){
			this.currentSelectorMode = SelectorMode.TILES;
		}
	}

	@Override
	public void Draw(Graphics2D g) {
		if(this.currentSelectorMode == SelectorMode.SECTIONS){
			g.drawImage(selectorSprite, this.posSx * (sm.getTileSize()*sm.getSizePerSection()) + this.posSx*(sm.getTileSize()/3) + sm.getPosX() - sectionOffset, this.posSy * (sm.getTileSize()*sm.getSizePerSection()) + this.posSy*(sm.getTileSize()/3) + sm.getPosY() - sectionOffset, sm.getTileSize()*sm.getSizePerSection() + sectionOffset*2, sm.getTileSize()*sm.getSizePerSection() + sectionOffset*2, null);
		}else if(this.currentSelectorMode == SelectorMode.TILES){
			g.drawImage(selectorSprite, this.posSx * (sm.getTileSize()*sm.getSizePerSection()) + this.posSx*(sm.getTileSize()/3) + sm.getPosX() + sm.getTileSize()*this.posx - this.innerOffset, 
										this.posSy * (sm.getTileSize()*sm.getSizePerSection()) + this.posSy*(sm.getTileSize()/3) + sm.getPosY() + sm.getTileSize()*this.posy - this.innerOffset, sm.getTileSize() + innerOffset*2, sm.getTileSize() + innerOffset*2, null);
		}
	}

	public int getPosSx() {
		return posSx;
	}

	public void setPosSx(int posSx) {
		this.posSx = posSx;
	}

	public int getPosSy() {
		return posSy;
	}

	public void setPosSy(int posSy) {
		this.posSy = posSy;
	}

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	public SelectorMode getCurrentSelectorMode() {
		return currentSelectorMode;
	}

	public void setCurrentSelectorMode(SelectorMode currentSelectorMode) {
		this.currentSelectorMode = currentSelectorMode;
	}
	
	

}

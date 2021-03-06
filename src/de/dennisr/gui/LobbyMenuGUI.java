package de.dennisr.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.dennisr.core.GameCore;

public class LobbyMenuGUI extends GUI{

	private BufferedImage splash, selectiorLeft;
	private Font font, multiplayerText, startGameText;
	
	private float textScale = .99F;
	
	private int[] optionPosition = {100, 200};
	private int selectorOptionPosition = 0, level = 1;
	
	public LobbyMenuGUI() {
		this.selectiorLeft = GameCore.getInstance().getImageFromRes("/gfx/selectorLeft.png");
		this.splash = GameCore.getInstance().getImageFromRes("/gfx/splashBG.jpg");
		this.font = new Font("Choose game options!");
		this.font.setScale(2);
		
		this.multiplayerText = new Font("Level [" + level + "]");
		this.multiplayerText.setScale(2);
		
		this.startGameText = new Font("Start Match!");
		this.startGameText.setScale(2);
		
	}
	
	@Override
	public void onEnter() {
		switch(this.selectorOptionPosition){
		case 0:
			nextLevel();
			break;
		
		case 1:
			GameCore.getInstance().getLobby().setLevel(this.level);
			GameCore.getInstance().getLobby().preInit();
			GameCore.getInstance().setInGame(true);
			break;
		}
	}

	private void nextLevel() {
		this.level += 1;
		if(level > 9){
			level = 1;
		}
		
		this.multiplayerText = new Font("Level [" + level + "]");
		this.multiplayerText.setScale(2);
	}

	@Override
	public void onUp() {
		this.lastOption();
	}

	@Override
	public void onDown() {
		this.nextOption();
	}
	
	@Override
	public void onUpdate() {
		if(this.font.getWidth() <= 100)this.textScale = 1.01F;
		if(this.font.getWidth() >= 200)this.textScale = .99F;
		this.font.setWidth((int)(this.font.getWidth() * textScale));
	}
	
	@Override
	public void Draw(Graphics2D g) {
		g.drawImage(splash, 0, 0, GameCore.WIN_WIDTH, GameCore.WIN_HEIGHT, null);
		g.drawImage(selectiorLeft, GameCore.WIN_WIDTH/2 - 200, optionPosition[selectorOptionPosition], 25, 25, null);
		font.render(g, GameCore.WIN_WIDTH/2 - font.getCenter()[0], 10);
		multiplayerText.render(g, GameCore.WIN_WIDTH/2 - multiplayerText.getCenter()[0], 100);
		startGameText.render(g, GameCore.WIN_WIDTH/2 - startGameText.getCenter()[0], 200);
	}
	
	private void lastOption(){
		this.selectorOptionPosition -= 1;
		if(this.selectorOptionPosition < 0){
			this.selectorOptionPosition = optionPosition.length-1;
		}
	}
	
	private void nextOption(){
		this.selectorOptionPosition += 1;
		if(this.selectorOptionPosition > optionPosition.length-1){
			this.selectorOptionPosition = 0;
		}
	}

}

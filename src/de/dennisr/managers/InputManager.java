package de.dennisr.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import de.dennisr.core.GameCore;
import de.dennisr.core.Window;

public class InputManager implements KeyListener{

	public InputManager(Window w){
		w.addKeyListener(this);
	}
	
	public class Key{
		private boolean pressed = false, justPressed = false;
		
		public boolean isPressed(){
			return this.pressed;
		}
		
		public boolean isJustPressed(){
			boolean ret = this.justPressed;
			this.justPressed = false;
			return ret;
		}
	
		public void toogle(boolean isPressed){
			if(pressed == false && isPressed == true){
				this.justPressed = true;
			}
			
			this.pressed = isPressed;
		}
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up  = new Key();
	public Key down = new Key();
	public Key left  = new Key();
	public Key right = new Key();
	public Key enter = new Key();
	public Key space = new Key();
	public Key esc = new Key();
	
	public Key input_1 = new Key();
	public Key input_2 = new Key();
	public Key input_3 = new Key();
	public Key input_4 = new Key();
	public Key input_5 = new Key();
	public Key input_6 = new Key();
	public Key input_7 = new Key();
	public Key input_8 = new Key();
	public Key input_9 = new Key();

	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void toggleKey(int keyCode, boolean isPressed){
		
		if(keyCode == KeyEvent.VK_W){
			up.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_S){
			down.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_A){
			left.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_D){
			right.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_ENTER){
			enter.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_SPACE){
			space.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_ESCAPE){
			esc.toogle(isPressed);
		}
		
		
		if(keyCode == KeyEvent.VK_1){
			input_1.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_2){
			input_2.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_3){
			input_3.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_4){
			input_4.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_5){
			input_5.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_6){
			input_6.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_7){
			input_7.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_8){
			input_8.toogle(isPressed);
		}
		
		if(keyCode == KeyEvent.VK_9){
			input_9.toogle(isPressed);
		}
		
	}
	
	
}

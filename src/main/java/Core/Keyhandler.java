package Core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyhandler implements KeyListener{
	public boolean moveUp, moveDown, moveLeft, moveRight;
	public boolean debug = false;
	public float playerSpeed = 1f;
	public int Mashab;
	
	
	public Keyhandler() {}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W)moveUp = true;
		if(code == KeyEvent.VK_S)moveDown = true;
		if(code == KeyEvent.VK_A)moveLeft = true;
		if(code == KeyEvent.VK_D)moveRight = true;
		if(code == KeyEvent.VK_NUMPAD0)debug = !debug;
		if(code == KeyEvent.VK_NUMPAD9)playerSpeed++;
		if(code == KeyEvent.VK_NUMPAD6)playerSpeed--;
		if(code == KeyEvent.VK_NUMPAD5)Mashab++;
		if(code == KeyEvent.VK_NUMPAD2)Mashab--;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W)moveUp = false;
		if(code == KeyEvent.VK_S)moveDown = false;
		if(code == KeyEvent.VK_A)moveLeft = false;
		if(code == KeyEvent.VK_D)moveRight = false;
	}

}

package jumpingJack;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * This screen just waits for a mouse click, whereupon it is removed from the JFrame and replaced with the game.
 */

public class Menu extends JPanel{
	private static final long serialVersionUID = 1L;
	int highscore;
	

	static BufferedImage img = null;{
		try {
			img = ImageIO.read(new File("G:\\Users\\Aastha\\workspace\\JumpingJack\\src\\jumpingJack\\start.png"));
		} catch (IOException e) {
			System.out.println("WRONG MENU");
		}}
	
	boolean startGame = false;						//the boolean toggle that starts the game over in ExecuteMe
	
	
	public Menu(){
		setFocusable(true);							//waits for a mouse click, then toggles startGame
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				startGame = true;
			}

			});
	}
	
	public void paint (Graphics g){
		super.paint(g);
	
		g.drawImage(img, 0, 0, null);				//paints background
	
		
	}
}
	

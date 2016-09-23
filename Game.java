package jumpingJack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel{
	
	static int HEIGHT = 800;						//height of the window
	static int WIDTH = 600;							//width of the window
	Jumper jumpery = new Jumper();					//makes a new jumper 
	Wall wall = new Wall(WIDTH);					//makes the first wall you see
	Wall wall2 = new Wall(WIDTH + (WIDTH / 2));		//makes the second wall you see
	static int score = 0;							//the score (how many walls you've passed)
	int scrollX = 0;								//scrolls the background
	static boolean dead = false;					//used to reset the walls
	static String deathMessage = "" ; 				// "you died,try again!";
	
	
	BufferedImage img = null;{
	try {
		img = ImageIO.read(new File("G:\\Users\\Aastha\\workspace\\JumpingJack\\src\\jumpingJack\\back.png"));
	} catch (IOException e) {
		System.out.println("WRONG BACKGROUND");		//prints "WRONG BACKGROUND" if there is an issue obtaining the background
	}}
	
	
	public Game(){
		
		//this mouseAdapter just listens for clicks, whereupon it then tells the jumper to jump 
		this.addMouseListener(new MouseAdapter(){

			public void mousePressed(MouseEvent arg0) {
				jumpery.jump();
			}
		
		});	
		
	}

	@SuppressWarnings("static-access")
	public void paint(Graphics g){
		super.paint(g);
		
		g.drawImage(img, scrollX, 0, null);					//there are two backgrounds so you get that smooth transition, this is the first			
		g.drawImage(img, scrollX + 1800, 0, null);			//number 2, exactly one background length away (1800 pixels)
		
		wall.paint(g);			//paints the first wall
		wall2.paint(g);			//the second wall
 		jumpery.paint(g);			//the jumper
 	
 		g.setFont(new Font("comicsans", Font.BOLD, 40));
 		g.setColor(Color.WHITE);
 		g.drawString("" + score, WIDTH / 2 - 20, 700);
 		g.drawString(deathMessage, 200, 200);				//paints "" if the player has not just died, paints "you died, try again" if the user just died
	}
	
	@SuppressWarnings("static-access")
	public void move(){

		wall.move();			//moves the first wall
		wall2.move();			//moves the second wall
		jumpery.move();			//moves the jumper
	
		scrollX += Wall.speed;	//scrolls the background
		
		if (scrollX == -1800)	//this loops the background around after it's done
			scrollX = 0;
		
		if (dead){				//this block essentially pushes the walls back 600 pixels on jumper's death
			wall.x = 600;
			wall2.x = 600 + (WIDTH / 2);
			dead = false;
		}
		
		if ( (wall.x == Jumper.X) || (wall2.x == Jumper.X) ) 	//Increments the score when the player passes a wall
			score();
	}
	
	public static void score(){
		score += 1;
	}
	
}
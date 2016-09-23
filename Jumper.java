package jumpingJack;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;


public class Jumper {

	static int DIAMETER = 25;									//Diameter of the jumper
	static int X = ( Game.WIDTH / 2 ) - ( DIAMETER / 2 );		//The x position of the jumper. Does not change at any time. Should be exactly centered  
	static int y =  Game.HEIGHT / 2;							//The STARTING y position of the jumper. Will change constantly
	static int acceleration = 1;								//Used in the gravity simulation below
	static int speed = 2;										//The speed at which the jumper will fall (constantly increased by acceleration (1) )
	
	
	static BufferedImage img = null;{
		try {
			img = ImageIO.read(new File("G:\\Users\\Aastha\\workspace\\JumpingJack\\src\\jumpingJack\\jumper.png"));
		} catch (IOException e) {
			System.out.println("WRONG JUMPER");	//Prints "WRONG JUMPER" if there is an error retrieving the image
		}}
	
	public Jumper(){
		//just the constructor, nothing to see here
	}
	
	//This is called when the jumper jumps (on mouse click). It just temporarily sets the speed to -17 (arbitrary number), then is slowly taken back down because 
	//of "gravity"
	public void jump(){
		speed = - 17;			
	}
	
	//all movement stuff is here 
	public static void move(){
	
		//only moves if the jumper is between the top and bottom of the window
		if ( ( y > 0 ) && ( y < Game.HEIGHT )) {
			speed += acceleration;								//Here's the gravity I was talking about the speed is just increased by 1 all the time, even after a jump
			y += speed;											//The actual movement, y location equals (where it was) + (how far it should go)
		}
		//or else the game resets (jumper is dead!)
		else {
			reset();											//rests jumper's position, actual method below
			Game.dead = true;									//jumper is dead! This is used in the Main method to reset the walls after a death
		}
		
	}
	
	public static void reset(){									//called after the jumper dies
		y = Game.HEIGHT / 2;									//resets position, speed, etc.
		speed = 2;
		Game.score = 0;
		
		Game.deathMessage = "You died,try again!";				//also shows this message
		
		//This timer just makes the message disappear after 3000 milliseconds
		Timer deathTimer = new Timer(3000, new ActionListener(){
			  public void actionPerformed(ActionEvent event){
				Game.deathMessage = "";
			 };
		});
		
		deathTimer.start();
	}
	
	public static void paint(Graphics g){	
		g.drawImage(img, X, y, null);							//paints the jumper's icon
	}
	
	public static Rectangle getBounds(){
		 return new Rectangle(X, y, DIAMETER, DIAMETER);		//Gives a rectangle used to detect collisions in the Wall class
		}

	
}

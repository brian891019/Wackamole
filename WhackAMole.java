/**
 *  Description: Play Whack-a-Mole game
 * 	File: WhackaMole.java
 */

import java.awt.*;
import java.awt.event.MouseEvent;
import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.util.*;

public class WhackAMole extends GraphicsProgram {
	// class constants
	private static final int WIN_SIZE = 1000;
	private static final int NUM_MOLES = 5;
   private static final int NUM_RABBIT = 3;


	// class variable
	private static RandomGenerator gen;

	// instance variables
	private ArrayList <Block> creatures;
   private ArrayList <Block> creatures2;

	private GLabel status;
   private GLabel status2;
	private int deadMoleCount;
   private int deadRabbitCount;

	// game setup
	public void init() {
		this.setSize(WIN_SIZE, WIN_SIZE);
		this.getGCanvas().setBackground(Color.GREEN.darker());
		this.gen = new RandomGenerator();
		this.creatures = new ArrayList <Block>();
		this.deadMoleCount = 0;
      this.deadRabbitCount = 0;
		this.status = new GLabel("", 10, WIN_SIZE - 900);
      this.status2 = new GLabel("", 10, WIN_SIZE - 900);

		this.status.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.status.setColor(Color.BLUE);
		this.add(status);
      
      this.status2.setFont(new Font("Tahoma", Font.BOLD, 20));
		this.status2.setColor(Color.BLUE);
		this.add(status2);

		updateStatus();
		loadingScreen();
		drawCreatures();
		updateStatus();
		this.addMouseListeners(); // this gives the program "ears"
	}

	public void run() {
		// the game runs while there are moles to be caught
		while (countMoles() > 0) {
			this.pause(gen.nextInt(1000) + 500);
			moveCreatures();
			updateStatus();
		}
      
      while (countRabbit() > 0) {
			this.pause(gen.nextInt(1000) + 500);
			moveCreatures2();
			updateStatus2();
		}

      
      
		status.setLabel("GAME OVER! You caught " + deadMoleCount + " moles!");
      status2.setLabel("GAME OVER! You caught " + deadRabbitCount + " rabbits!");

	}

	public void loadingScreen() {
		GLabel instructions = new GLabel("CATCH THE MOLES!\nclick to begin...", 15, 200);
		instructions.setColor(Color.BLACK);
		instructions.setFont(new Font("Tahoma", Font.BOLD, 25));
		this.add(instructions);
		waitForClick();
		this.remove(instructions);
	}

	public void updateStatus() {
		this.status.setLabel("Moles:   CAUGHT - " + deadMoleCount + "  ALIVE - " + countMoles());
	}
 
   public void updateStatus2() {
		this.status.setLabel("Moles:   CAUGHT - " + deadRabbitCount + "  ALIVE - " + countRabbit());
	}
 
   
   public int countMoles() {
      int count = 0;
      for (Block b: creatures) {
         if(b instanceof Mole) {
            count++;
         }
      }
      return count;
   }
   
    public int countRabbit() {
      int count2 = 0;
      for (Block b: creatures) {
         if(b instanceof Rabbit) {
            count2++;
         }
      }
      return count2;
   }

   

	public void drawCreatures() {
		for (int i = 0; i < NUM_MOLES; i++) {
			generateMole();
		}
	}
   
   public void drawCreatures2() {
		for (int i = 0; i < NUM_RABBIT; i++) {
			generateRabbit();
		}
	}


	public void generateMole() {
		Mole mole = new Mole(gen.nextInt(WIN_SIZE - Mole.MOLE_WIDTH), gen.nextInt(WIN_SIZE - Mole.MOLE_WIDTH));
		this.creatures.add(mole);
		this.add(mole);
	}
   
   public void generateRabbit() {
		Rabbit rabbit = new Rabbit(gen.nextInt(WIN_SIZE - Rabbit.RABBIT_WIDTH), gen.nextInt(WIN_SIZE - Rabbit.RABBIT_WIDTH));
		this.creatures.add(rabbit);
		this.add(rabbit);
	}


	public void moveCreatures() {
		for (Block b: creatures) {
         if(b instanceof Mole) {
			   b.setLocation(gen.nextInt(WIN_SIZE - Mole.MOLE_WIDTH), gen.nextInt(WIN_SIZE - Mole.MOLE_WIDTH));
		   }
         
        
      }
	}
   
   public void moveCreatures2() {
		for (Block a: creatures2) {
         if(a instanceof Rabbit) {
			   a.setLocation(gen.nextInt(WIN_SIZE - Rabbit.RABBIT_WIDTH), gen.nextInt(WIN_SIZE - Rabbit.RABBIT_WIDTH));
		   }
         }
         }
        


	public void mousePressed(MouseEvent e) {
		// Grab x and y coordinates of mouse press
		int x = e.getX();
		int y = e.getY();
		System.out.println("clicked at " + x + ", " + y);

		// Get the thing that was clicked on
		GObject obj = getElementAt(x, y);
		System.out.println("  ===> " + obj);

		if (obj instanceof Mole) {
			this.creatures.remove(obj);
			this.remove(obj);
			this.deadMoleCount++;
		} 
      if (obj instanceof Rabbit) {
			this.creatures.remove(obj);
			this.remove(obj);
			this.deadRabbitCount++;
		}else {
			generateMole();
         generateRabbit();

		}
      
    	}
      
    


	public static void main(String[] args) {
		new WhackAMole().start(args);
	}
}
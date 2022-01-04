import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.io.*;

public class Rabbit extends Block {

   public static final int RABBIT_WIDTH = 50;

   private static final Image RABBIT_IMG = Toolkit.getDefaultToolkit().getImage("rabbit.png");
   
      public Rabbit(int x, int y) {
      super(x,y,RABBIT_WIDTH);
   }
   

   public void paint(Graphics g) {
      g.drawImage(RABBIT_IMG, (int)getX(), (int)getY(), getComponent());
      
   }

}
package sokoban;

import java.awt.*;

public class Game extends Canvas {
    //
    // Game objects
    //
    public class Ground implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
           Color ground_brown = new Color(73, 50, 4);
           Color nugget_yellow = new Color(118,87,25);
           Color grass_green= new Color (31,82,15);
            ctx.setColor(ground_brown);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT );
            
            ctx.setColor(nugget_yellow);
            ctx.fillRect(x, y, 12, 9);
            ctx.fillRect(x+32, y+12, 12, 9);
            ctx.fillRect(x+48, y+48, 12, 9);
            ctx.fillRect(x+3, y+24, 12, 9);
            ctx.fillRect(x+22, y+40, 12, 9);
            
            ctx.setColor(grass_green);
            ctx.fillRect(x+48, y+30, 12, 9);

           
        }
    }

    public class Brick implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
        	Color gray = new Color (115,113,110);
        	Color nugget_gray = new Color(88,87,83);
            ctx.setColor(gray);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT );
            ctx.setColor(nugget_gray);
            ctx.fillRect(x, y, 12, 9);
            ctx.fillRect(x+24, y, 12, 9);
            ctx.fillRect(x+48, y, 12, 9);  
            
            ctx.fillRect(x+12, y+12, 12, 9);
            ctx.fillRect(x+36, y+12, 12, 9);
            
            ctx.fillRect(x, y+24, 12, 9);
            ctx.fillRect(x+24, y+24, 12, 9);
            ctx.fillRect(x+48, y+24, 12, 9);
            
            ctx.fillRect(x+12, y+36, 12, 9);
            ctx.fillRect(x+36, y+36, 12, 9);
            
            ctx.fillRect(x, y+48, 12, 9);
            ctx.fillRect(x+24, y+48, 12, 9);
            ctx.fillRect(x+48, y+48, 12, 9);
           
        }
    }

    //
    // Game
    //
//    Обьявляю тут Массив, 
    Interfaces.IBlock[][] blocks = new Interfaces.IBlock[][] {
    	{new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick ()},
    	{new Brick (), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Brick(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Brick(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Brick(), new Brick(), new Brick(), new Brick(), new Brick(), new Brick(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick ()},
    	{new Brick (), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Ground(), new Brick ()},
    	{new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick (), new Brick ()},
    } ;
    
    public void Init() {
    	System.out.println("Initialazing game");
    }

    public boolean Process() {
        return false;
    }

    public void Render(Graphics ctx) {
        ctx.setColor(Color.RED);
        ctx.drawString("Sokoban soon will be playable in this window...", 40, 40);
        
        int o = 10;
        int b = 10;
        
      for (int j = 0; j< 10; j++ ) {
    		   for (int i = 0; i<10; i++) {
    			   System.out.printf("i=%d j=%d\n", j, i);
    		   		blocks[j][i].Draw(ctx, b , o );
    		   		b= b+Config.BLOCK_WIDTH;
    		   }
    	b= 10;	   
    	o= o+Config.BLOCK_WIDTH;
      }
       
    }

    //
    // java.awt.Canvas
    //
    @Override
    public void paint(Graphics ctx) {
        System.out.println("Redrawing screen");
        Render(ctx);
    }
}

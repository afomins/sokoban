package sokoban;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Game {
    //
    // Game objects
    //
    public class Ground implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
           Color ground_brown = new Color(73, 50, 4);
           Color nugget_yellow = new Color(118,87,25);
           Color grass_green = new Color(31,82,15);
            ctx.setColor(ground_brown);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);

            ctx.setColor(nugget_yellow);
            ctx.fillRect(x, y, 12, 9);
            ctx.fillRect(x + 32, y + 12, 12, 9);
            ctx.fillRect(x + 48, y + 48, 12, 9);
            ctx.fillRect(x + 3, y + 24, 12, 9);
            ctx.fillRect(x + 22, y + 40, 12, 9);

            ctx.setColor(grass_green);
            ctx.fillRect(x + 48, y + 30, 12, 9);
        }
    }

    public class Brick implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
        Color gray = new Color(115,113,110);
        Color nugget_gray = new Color(88,87,83);
            ctx.setColor(gray);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT);
            ctx.setColor(nugget_gray);
            ctx.fillRect(x, y, 12, 9);
            ctx.fillRect(x + 24, y, 12, 9);
            ctx.fillRect(x + 48, y, 12, 9);  

            ctx.fillRect(x + 12, y + 12, 12, 9);
            ctx.fillRect(x + 36, y + 12, 12, 9);

            ctx.fillRect(x, y + 24, 12, 9);
            ctx.fillRect(x + 24, y + 24, 12, 9);
            ctx.fillRect(x + 48, y + 24, 12, 9);

            ctx.fillRect(x + 12, y + 36, 12, 9);
            ctx.fillRect(x + 36, y + 36, 12, 9);

            ctx.fillRect(x, y + 48, 12, 9);
            ctx.fillRect(x + 24, y + 48, 12, 9);
            ctx.fillRect(x + 48, y + 48, 12, 9);
        }
    }
    
    public class Hero extends Ground {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
            super.Draw(ctx, x, y);
            
//            Hero Colors
            Color jumpsuit = new Color (82, 59, 255);
            Color skin = new Color (238, 206, 179);
            
             ctx.setColor(jumpsuit);
             ctx.fillRect(x+20,y+16, 24, 26);
             ctx.fillRect(x+20,y+42,10,18);
             ctx.fillRect(x+34, y+42, 10, 18);
             
             ctx.setColor(skin);
             ctx.fillRect(x+12, y+16, 8, 18);
             ctx.fillRect(x+44, y+16, 8, 18);
             ctx.fillRect(x+26, y+4, 12, 12);
             
        }
    }
    
    Interfaces.IBlock[][] blocks; 
    int lvl_height;
    int lvl_width;
    private Hero hero;
    private int hero_x;
    private int hero_y;

    public boolean Init() {
         String level1[] = {
            "XXXXXXXXXXXXXXXXXXXX",
            "X..@.....X.........X",
            "X.XX..XX...........X",
            "X........X.........X",
            "X..XXXX..X.........X",
            "X..................X",
            "XXXXXXXXXXXXXXXXXXXX"
        };
        lvl_height = level1.length;
        lvl_width = level1[0].length();

        if (lvl_height < 1) {
            return false;
        }

        for (int i = 0; i < lvl_height; i++) {
            if (level1[i].length() != lvl_width) {
                return false;
            } 
        }
//        Duplicates check
        int Hero_Dublicate = 0;
        for (int j = 0; j < lvl_height; j++) {
            for(int i = 0; i < lvl_width; i++) {
                char a = level1[j].charAt(i);
                
                if (a =='@') {
                    Hero_Dublicate++;
                }
            }
        }
        if(Hero_Dublicate != 1) {
            return false;
        }
    
        blocks = new Interfaces.IBlock[lvl_width][lvl_height];
        for (int j = 0; j < lvl_height; j++) {
            for(int i = 0; i < lvl_width; i++) {
                char a = level1[j].charAt(i);
                
                switch (a) {
                     case 'X':
                        blocks[i][j] = new Brick();
                        break;
                    case '.':
                        blocks[i][j] = new Ground();
                        break;
                    case '@':
                        blocks[i][j] = new Ground();
                        hero = new Hero();
                        hero_x = i;
                        hero_y = j;
                        break;
                    }
                
            }
        }
        return true;
    }

    public boolean Process(Set<Integer> key_pressed) {
        boolean redraw = false;
        if (key_pressed.contains(KeyEvent.VK_RIGHT)) {
            if (hero_x + 1 < lvl_width && blocks[hero_x + 1][hero_y] instanceof Ground) { 
                hero_x++;
                redraw = true;
            }
        }
        if (key_pressed.contains(KeyEvent.VK_UP)) {
            if(hero_y - 1 >= 0  && blocks[hero_x][hero_y - 1] instanceof Ground) {
                hero_y--;
                redraw = true;
            }
      }
        if (key_pressed.contains(KeyEvent.VK_DOWN)) {
            if(hero_y + 1 < lvl_height  && blocks[hero_x][hero_y + 1] instanceof Ground) {
                hero_y++;
                redraw = true;
            }
        }
        if (key_pressed.contains(KeyEvent.VK_LEFT)) {
            if(hero_x - 1 >= 0  && blocks[hero_x - 1][hero_y] instanceof Ground) {
                hero_x--;
                redraw = true;
            }
        }
        return redraw;
    }

    public void Render(Graphics ctx) {
        
        if (blocks==null) {
            return;
        }
        
        System.out.println("x=" + hero_x + "y=" + hero_y);
        ctx.setColor(Color.RED);
        ctx.drawString("Press SPACE button...", 800, 40);

        ctx.setColor(Color.CYAN);
        ctx.fillRect(140, 80, 128, 128);

        int o = 10;
        int b = 10;

        for (int j = 0; j < lvl_height; j++) {
            for (int i = 0; i < lvl_width; i++) {
                blocks[i][j].Draw(ctx, b , o);
                b = b + Config.BLOCK_WIDTH;
            }
            o = o + Config.BLOCK_WIDTH;
            b = 10;
        }
        
        hero.Draw(ctx, 10+hero_x*Config.BLOCK_WIDTH, 10+hero_y*Config.BLOCK_HEIGHT);
        
        Brick brick = new Brick();
        Ground ground = new Ground();
    }
}

package sokoban;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Game {
    //
    // Game objects
    //
    public class FlagLV implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
            Color lv_red = new Color(140, 0, 0);
            ctx.setColor(lv_red);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT / 3);

            ctx.setColor(Color.WHITE);
            ctx.fillRect(x, y + Config.BLOCK_HEIGHT / 3 * 1, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT / 3);

            ctx.setColor(lv_red);
            ctx.fillRect(x, y + Config.BLOCK_HEIGHT / 3 * 2, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT / 3);
        }
    }

    public class FlagUA implements Interfaces.IBlock {
        @Override
        public void Draw(Graphics ctx, int x, int y) {
            ctx.setColor(Color.BLUE);
            ctx.fillRect(x, y, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT / 2);

            ctx.setColor(Color.YELLOW);
            ctx.fillRect(x, y + Config.BLOCK_HEIGHT / 2, Config.BLOCK_WIDTH, Config.BLOCK_HEIGHT / 2);
        }
    }

    //
    // Game
    //
    private int key_press_num = 0;

    public boolean Process(Set<Integer> key_pressed) {
        boolean redraw = false;
        if (key_pressed.contains(KeyEvent.VK_SPACE)) {
            Utils.Log("Space key press has been detected :: key_press_num=" + key_press_num);
            key_press_num++;
            redraw = true;
        }
        return redraw;
    }

    public void Render(Graphics ctx) {
        ctx.setColor(Color.CYAN);
        ctx.fillRect(140, 80, 128, 128);

        ctx.setColor(Color.ORANGE);
        ctx.fillRect(140 + 30, 80 + 30, 128, 128);

        FlagLV flag1 = new FlagLV();
        for (int i = 0; i < key_press_num + 1; i++) {
            flag1.Draw(ctx, 400 + i * 25, 200 + i * 25);
        }

        FlagUA flag2 = new FlagUA();
        flag2.Draw(ctx, 400 + 100, 200);
    }
}

package sokoban;

import java.awt.*;
import java.awt.event.KeyEvent;

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
    private Utils.KeyboardManager.KeyState key_space = Utils.KeyboardManager.KeyState.NONE;
    private Utils.KeyboardManager.KeyState key_space_prev = Utils.KeyboardManager.KeyState.NONE;

    public boolean Process(Utils.KeyboardManager kbd_man) {
        boolean redraw = false;
        key_space = kbd_man.GetState(KeyEvent.VK_SPACE);
        if (key_space_prev != key_space) {
            Utils.Log("SPACE :: " + key_space_prev + " -> " + key_space);
            key_space_prev = key_space;
            redraw = true;
        }
        return redraw;
    }

    public void Render(Graphics ctx) {
        ctx.setColor(Color.RED);
        ctx.drawString("State of the [SPACE] is " + key_space, 40, 40);

        ctx.setColor(Color.CYAN);
        ctx.fillRect(140, 80, 128, 128);

        ctx.setColor(Color.ORANGE);
        ctx.fillRect(140 + 30, 80 + 30, 128, 128);

        FlagLV flag1 = new FlagLV();
        flag1.Draw(ctx, 400, 200);

        FlagUA flag2 = new FlagUA();
        flag2.Draw(ctx, 400 + 100, 200);
    }
}

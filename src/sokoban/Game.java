package sokoban;

import java.awt.*;

public class Game extends Canvas {
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
    public boolean Process() {
        return false;
    }

    public void Render(Graphics ctx) {
        ctx.setColor(Color.RED);
        ctx.drawString("Sokoban will soon be playable in this window...", 40, 40);

        ctx.setColor(Color.CYAN);
        ctx.fillRect(140, 80, 128, 128);

        ctx.setColor(Color.ORANGE);
        ctx.fillRect(140 + 30, 80 + 30, 128, 128);

        FlagLV flag1 = new FlagLV();
        flag1.Draw(ctx, 400, 200);

        FlagUA flag2 = new FlagUA();
        flag2.Draw(ctx, 400 + 100, 200);
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

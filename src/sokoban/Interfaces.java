package sokoban;

import java.awt.Graphics;

public class Interfaces {
    //
    // All game interfaces will be defined here
    //
    interface IBlock {
        public void Draw(Graphics ctx, int x, int y);
    }
}

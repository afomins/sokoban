package sokoban;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JFrame;

public class Sokoban {
    //
    // Main
    //
    public static void main(String[] args) {
        // Create instance of the game
        Game game = new Game();

        // Create canvas where to draw
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics ctx) {
                Utils.Log("Redering frame");
                game.Render(ctx);
            }
        };

        // Create game window and attach canvas
        JFrame frame = new JFrame();
        frame.add(canvas);
        frame.setTitle(Config.NAME + " " + Config.VERSION);
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setVisible(true);

        // Register window listener to handle window closing event
        final AtomicInteger quit = new AtomicInteger(0);
        WindowListener win_listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit.set(1); 
            }
        };
        frame.addWindowListener(win_listener);

        // Register key listener to handle keyboard events
        Utils.KeyboardManager kbd_man = new Utils.KeyboardManager();
        KeyListener key_listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                kbd_man.Pressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                kbd_man.Released(e.getKeyCode());
            }
        };
        canvas.addKeyListener(key_listener);

        // Run game loop
        Utils.Log("Starting game loop");
        while (quit.get() == 0) {
            // Process game
            boolean redraw = game.Process(kbd_man);

            // Render frame if necessary
            if (redraw) {
                canvas.repaint();
            }

            // Process keyboard manager 
            kbd_man.Process();
        }

        // Do cleanup
        Utils.Log("Stopping game loop");
        frame.dispose();
    }
}

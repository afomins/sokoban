package sokoban;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
        Set<Integer> key_pressed = new HashSet<Integer>();
        ReentrantLock key_pressed_mutex = new ReentrantLock();
        KeyListener key_listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                key_pressed_mutex.lock();
                key_pressed.add(e.getKeyCode());
                key_pressed_mutex.unlock();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        canvas.addKeyListener(key_listener);

        // Focus of the canvas to receive keyboard input
        canvas.requestFocus();

        // Initialize game
        Utils.Log("Starting game loop");
        if (!game.Init()) {
            System.out.println("Initialization has failed");

        // Run game loop upon successful initialization
        } else {
            while (quit.get() == 0) {
                // Begin frame
                key_pressed_mutex.lock();

                // Process game
                boolean redraw = game.Process(key_pressed);

                // Render frame if necessary
                if (redraw) {
                    canvas.repaint();
                }

                // Clear key presses 
                key_pressed.clear();

                // End frame
                key_pressed_mutex.unlock();
            }
        }

        // Do cleanup
        Utils.Log("Stopping game loop");
        frame.dispose();
    }
}

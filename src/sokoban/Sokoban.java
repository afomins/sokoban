package sokoban;

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

        // Create game window
        JFrame frame = new JFrame();
        frame.add(game);
        frame.setTitle(Config.NAME + " " + Config.VERSION);
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setVisible(true);

        // Register window listener to handle window closing event
        final AtomicInteger win_is_closing = new AtomicInteger(0);
        WindowListener win_listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                win_is_closing.set(1); // quit game 
            }
        };
        frame.addWindowListener(win_listener);
        game.Init();

        if (game.Init() == true){
            System.out.println("Starting game loop");
            while (win_is_closing.get() == 0) {
                // Process game logic and redraw the screen when game state changes
                if (game.Process()) {
                    frame.repaint();
                }
            }
        } else {
            System.out.println("Initialization has failed");
        }

        // Do cleanup
        System.out.println("Stopping game loop");
        frame.dispose();
    }
}

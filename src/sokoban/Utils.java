package sokoban;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Utils {
    //
    // Simple keyboard manager
    //
    public static class KeyboardManager {
        public enum KeyState {
            PRESSED,
            HOLD,
            RELEASED,
            NONE
        };

        class KeyEvent {
            public int key;
            public KeyState state;

            KeyEvent(int key, KeyState state) {
                this.key = key;
                this.state = state;
            }
        };

        private HashMap<Integer, KeyState> key_map = new HashMap<Integer, KeyState>();
        private List<KeyEvent> event_queue = new LinkedList<KeyEvent>();

        synchronized public void Pressed(int key) {
            // Enqueue PRESSED
            event_queue.add(new KeyEvent(key, KeyState.PRESSED));
        }

        synchronized public void Released(int key) {
            // Enqueue RELEASED
            event_queue.add(new KeyEvent(key, KeyState.RELEASED));
        }

        synchronized public KeyState GetState(int key) {
            return key_map.containsKey(key) ? key_map.get(key) : KeyState.NONE;
        }

        synchronized public void PutState(int key, KeyState status) {
            KeyState old_status = GetState(key);
            if (old_status == status) {
                return;
            }
            key_map.put(key, status);
        }

        synchronized public void Process() {
            // Process existing states
            for (Map.Entry<Integer, KeyState> e: key_map.entrySet()) {
                KeyState old_state = e.getValue();
                KeyState new_state =
                    old_state == KeyState.PRESSED  ? KeyState.HOLD : // PRESSED -> HOLD
                    old_state == KeyState.RELEASED ? KeyState.NONE : // RELEASED -> NONE
                    old_state;                                       // unchanged
                PutState(e.getKey(), new_state);
            }

            // Apply new states from queue
            for (KeyEvent e : event_queue) {
                KeyState old_state = GetState(e.key);
                KeyState new_state = e.state;
                if (new_state == KeyState.PRESSED && old_state != KeyState.NONE) {
                    // Ignore duplicaing PRESSED events originating by KeyListener
                    continue;
                }
                PutState(e.key, e.state);
            }
            event_queue.clear();
        }
    }

    //
    // Utils
    //
    public static void Log(String str) {
        System.out.println(str);
    }

}

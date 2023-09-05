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
            event_queue.add(new KeyEvent(key, KeyState.PRESSED));
        }

        synchronized public void Released(int key) {
            event_queue.add(new KeyEvent(key, KeyState.RELEASED));
        }

        synchronized public KeyState GetStatus(int key) {
            return key_map.containsKey(key) ? key_map.get(key) : KeyState.NONE;
        }

        synchronized public void PutStatus(int key, KeyState status) {
            KeyState old_status = GetStatus(key);
            if (old_status == status) {
                return;
            }
            key_map.put(key, status);
        }

        synchronized public void Process() {
            // Process existing states
            for (Map.Entry<Integer, KeyState> e: key_map.entrySet()) {
                KeyState old_status = e.getValue();
                KeyState new_status =
                    old_status == KeyState.PRESSED  ? KeyState.HOLD : // pressed -> hold
                    old_status == KeyState.RELEASED ? KeyState.NONE : // released -> none
                    old_status;                                       // unchanged
                PutStatus(e.getKey(), new_status);
            }

            // Apply new states from queue
            for (KeyEvent e : event_queue) {
                PutStatus(e.key, e.state);
            }
            event_queue.clear();
        }
    }
}

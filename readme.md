<h1>Task #1 - "Hello world"</h1>

1. Create sub-class `Game.Ground` with following properties:
    * The `Game.Ground` should implement the `Interfaces.IBlock` interface
    * The `Game.Ground.Draw()` method should draw a 64x64 `BROWN` rectangle with some `BLACK` dots inside

2. Create sub-class `Game.Wall` with following properties:
    * The `Game.Wall` should implement the `Interfaces.IBlock` interface
    * The `Game.Wall.Draw()` method should draw a 64x64 `DARK-GRAY` rectangle with some `YELLOW` dots inside

3. The `Game.Render()` should draw following pattern in the game window here `X` represents wall and `.` represents ground:
```
XXXXXXXXXX
X........X
X.XX..XX.X
X........X
X..XXXX..X
X........X
XXXXXXXXXX
```

<h1>Task #2 - "Hero movement"</h1>
1. Create sub-class `Game.Hero` with following properties:
    * The `Game.Hero` should implement the `Interfaces.IBlock` interface
    * The `Game.Ground.Draw()` method should draw a 64x64 char as it is seen when looking top-to-bottom

2. Update the level loading method in such a way that it would instantiate `Game.Hero` object when detecting `@` char in level description and same it in provate member `hero`

3. Save coordinates of the `hero` instance in `hero_x` and `hero_y` variables

4. Make sure that only one `Game.Hero` istance can be spawned

5. Update `Game.Render()` to draw hero **on top of the level** in `hero_x`/`hero_y` coordinates

6. Update the `Game.Process()` to increment the `hero_x`/`hero_y` coordinateswhen pressing arrow keys:
```
   if (kbd_man.GetState(KeyEvent.VK_LEFT) == Utils.KeyboardManager.KeyState.PRESSED) {
       hero_x += 1;
   }
```

8. Make sure that `hero` does not fall beyond the boundaries of the level

9. Make sure that `hero` can walk only on the ground
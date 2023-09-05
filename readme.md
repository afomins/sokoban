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

<h1>Task #2 - "Choose your hero"</h1>

1. Create sub-class `Game.Hero` with following properties:
    * The `Game.Hero` should implement the `Interfaces.IBlock` interface
    * The `Game.Hero.Draw()` method should draw a 64x64 block that would look like person when looking at him from above

2. Update the level loading method in such a way that it would instantiate `Game.Hero` object when detecting `@` char in level description and store it in private variable `hero`

3. Save coordinates of the `hero` instance in private variables `hero_x`/`hero_y`

4. Make sure only one instance `Game.Hero` is created

5. Update the `Game.Render()` method to draw hero **on top of the level** in `hero_x`/`hero_y` coordinates

6. Update the `Game.Process()` method to update the `hero_x`/`hero_y` coordinates when pressing arrow keys and redraw the frame:
```
   if (key_pressed.contains(KeyEvent.VK_RIGHT)) {
       hero_x += 1;
   }
```

8. Make sure that coordinates of the `hero` does not go past the boundaries of the level

9. Make sure that `hero` can walk only on the ground

<h1>Task 1</h1>

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




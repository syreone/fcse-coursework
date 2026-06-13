class Robot:
    DIRECTIONS = ['up', 'right', 'down', 'left']
    DELTAS = {'up': (-1, 0), 'right': (0, 1), 'down': (1, 0), 'left': (0, -1)}

    def __init__(self, row, col, direction):
        self.row = row
        self.col = col
        self.direction = direction

    def move(self, grid):
        dr, dc = self.DELTAS[self.direction]
        while True:
            nr, nc = self.row + dr, self.col + dc
            if nr < 0 or nr >= len(grid) or nc < 0 or nc >= len(grid[0]):
                break
            if grid[nr][nc] == '#':
                break
            self.row, self.col = nr, nc
            grid[self.row][self.col] = 'X'

    def turn(self):
        idx = self.DIRECTIONS.index(self.direction)
        self.direction = self.DIRECTIONS[(idx + 1) % 4]


class Game:
    def __init__(self, grid, robot1, robot2):
        self.grid = grid
        self.robot1 = robot1
        self.robot2 = robot2

    def run(self):
        for _ in range(15):
            self.robot1.move(self.grid)
            self.robot1.turn()
            self.robot2.move(self.grid)
            self.robot2.turn()

    def count_cleaned(self):
        return sum(cell == 'X' for row in self.grid for cell in row)


grid = [
    ['#', '.', '.', '#', '.', '.'],
    ['.', '#', '.', '.', '.', '.'],
    ['#', '.', '.', '.', '.', '.'],
    ['.', '#', '.', '.', '.', '.'],
    ['.', '.', '.', '#', '.', '.'],
]

r1, c1, d1 = input().split()
r2, c2, d2 = input().split()

robot1 = Robot(int(r1), int(c1), d1)
robot2 = Robot(int(r2), int(c2), d2)

game = Game(grid, robot1, robot2)
if grid[robot1.row][robot1.col] == '.':
    grid[robot1.row][robot1.col] = 'X'
if grid[robot2.row][robot2.col] == '.':
    grid[robot2.row][robot2.col] = 'X'
game.run()
print(game.count_cleaned())
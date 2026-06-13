from searching_framework import *

class Maze(Problem):
    def __init__(self, n, walls, initial_pos, house_pos):
        super().__init__(initial_pos)
        self.n = n
        self.walls = walls
        self.house_pos = house_pos

    def successor(self, state):
        successors = {}
        x, y = state

        directions = ['Up', 'Down', 'Left']
        actions = [(0,1), (0,-1), (-1, 0)]
        for name, (dx, dy) in zip(directions, actions):
            nx, ny = x + dx, y + dy
            if 0 <= nx < self.n and 0 <= ny < self.n and (nx, ny) not in self.walls:
                successors[name] = (nx, ny)

        if x + 2 < self.n:
            if all((x + i, y) not in self.walls for i in range(1, 3)):
                successors['Right 2'] = (x + 2, y)

        if x + 3 < self.n:
            if all((x + i, y) not in self.walls for i in range(1, 4)):
                successors['Right 3'] = (x + 3, y)

        return successors

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        return state == self.house_pos

    def h(self, node):
        x, y = node.state
        gx, gy = self.house_pos
        return ((abs(gx - x) + 2) // 3) + abs(gy - y)


if __name__ == "__main__":
    n = int(input())
    num_walls = int(input())
    walls = set()
    for _ in range(num_walls):
        walls.add(tuple(map(int, input().split(','))))
    start_pos = tuple(map(int, input().split(',')))
    house_pos = tuple(map(int, input().split(',')))

    problem = Maze(n, walls, start_pos, house_pos)
    solution = astar_search(problem)

    if solution:
        print(solution.solution())
    else:
        print("No Solution!")
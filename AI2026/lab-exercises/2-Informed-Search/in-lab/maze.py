from searching_framework import Problem, astar_search
#covecheto od prvata kolona x=0 mozhe da se teleportira vo poslednata kolona x=n-1 vo istiot red vo koj se naogja, ovaa akcija imenuvajte ja Teleportiraj
class MazeProblem(Problem):
    def __init__(self, initial, goal, walls, n):
        super().__init__(initial, goal)
        self.walls = walls
        self.n = n

    def successor(self, state):
        row, col = state
        moves = []

        if col - 1 >= 0 and (row, col - 1) not in self.walls:
            moves.append(("Gore", (row, col - 1)))

        if col + 1 < self.n and (row, col + 1) not in self.walls:
            moves.append(("Dolu", (row, col + 1)))

        if row - 1 >= 0 and (row - 1, col) not in self.walls:
            moves.append(("Levo", (row - 1, col)))

        for steps in [2, 3]:
            valid = True
            for s in range(1, steps + 1):
                if row - s < 0 or (row - s, col) in self.walls:
                    valid = False
                    break
            if valid:
                moves.append((f"Desno {steps}", (row - steps, col)))

        if row == 0:
            if (self.n - 1, col) not in self.walls:
                moves.append(("Teleportiraj", (self.n - 1, col)))

        return moves

    def actions(self, state):
        return [action for action, _ in self.successor(state)]

    def result(self, state, action):
        for a, s in self.successor(state):
            if a == action:
                return s

    def goal_test(self, state):
        return state == self.goal

    def h(self, node):
        row, col = node.state
        goal_row, goal_col = self.goal
        return abs(row - goal_row) + abs(col - goal_col)

if __name__ == '__main__':
    n = int(input())
    num_walls = int(input())

    walls = set()
    for _ in range(num_walls):
        wr, wc = map(int, input().split(','))
        walls.add((wr, wc))

    hr, hc = map(int, input().split(','))
    pr, pc = map(int, input().split(','))

    problem = MazeProblem((pr, pc), (hr, hc), walls, n)
    solution = astar_search(problem)

    if solution:
        print(solution.solution())
    else:
        print("No solution found")
import sys
from searching_framework import Problem, astar_search
# ako baterijata na robotot e pomala ili ednakva na 2 robotot smee da se dvizhi samo vo nasoka kojashto doblizhuva do nekoja stanica
class RobotProblem(Problem):
    def __init__(self, initial, goal, battery_cap, chargers, allowed):
        initial_state = (initial[0], initial[1], battery_cap)
        super().__init__(initial_state, goal)
        self.battery_cap = battery_cap
        self.chargers = set(chargers)
        self.allowed = set(allowed)

    def _min_dist_to_charger(self, x, y):
        if not self.chargers:
            return float('inf')
        return min(abs(x - cx) + abs(y - cy) for cx, cy in self.chargers)

    def actions(self, state):
        x, y, battery = state
        if battery <= 0:
            return []
        directions = {"Up": (0, +1), "Down": (0, -1), "Left": (-1, 0), "Right": (+1, 0)}
        valid = []
        for action, (dx, dy) in directions.items():
            nx, ny = x + dx, y + dy
            if (nx, ny) not in self.allowed:
                continue
            if battery <= 2:
                current_dist = self._min_dist_to_charger(x, y)
                new_dist = self._min_dist_to_charger(nx, ny)
                if new_dist < current_dist:
                    valid.append(action)
            else:
                valid.append(action)
        return valid

    def result(self, state, action):
        x, y, battery = state
        directions = {"Up": (0, +1), "Down": (0, -1), "Left": (-1, 0), "Right": (+1, 0)}
        dx, dy = directions[action]
        nx, ny = x + dx, y + dy
        new_battery = battery - 1
        if (nx, ny) in self.chargers:
            new_battery = self.battery_cap
        return (nx, ny, new_battery)

    def goal_test(self, state):
        x, y, _ = state
        return (x, y) == self.goal

    def h(self, node):
        x, y, _ = node.state
        gx, gy = self.goal
        return abs(x - gx) + abs(y - gy)

if __name__ == '__main__':
    walls = [(4, 0), (5, 0), (7, 5), (8, 5), (9, 5),
             (1, 6), (1, 7), (0, 6), (0, 8), (0, 9),
             (1, 9), (2, 9), (3, 9)]
    allowed = {(x, y) for x in range(10) for y in range(10)} - set(walls)

    data = sys.stdin.read().strip().split('\n')
    sx, sy = map(int, data[0].split(','))
    gx, gy = map(int, data[1].split(','))
    B = int(data[2])
    n = int(data[3])
    chargers = [tuple(map(int, data[4+i].split(','))) for i in range(n)]

    problem = RobotProblem(
        initial=(sx, sy),
        goal=(gx, gy),
        battery_cap=B,
        chargers=chargers,
        allowed=allowed,
    )

    solution = astar_search(problem)
    if solution is None:
        print("No Solution!")
    else:
        actions = solution.solution()
        if not actions:
            print("No Solution!")
        else:
            print(actions)
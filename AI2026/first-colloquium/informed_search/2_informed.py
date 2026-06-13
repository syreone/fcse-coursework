from searching_framework import *


class House(Problem):
    def __init__(self, initial, allowed):
        super().__init__(initial)
        self.allowed = allowed

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        pos, goal, _ = state
        return pos == goal

    def successor(self, state):
        successors = dict()
        pos, goal, house_dir = state

        moves = {
            "Up 1": (0, 1),
            "Up-right 1": (1, 1),
            "Up-left 1": (-1, 1),
            "Up 2": (0, 2),
            "Up-right 2": (2, 2),
            "Up-left 2": (-2, 2),
            "Wait": (0, 0)
        }

        new_hx = goal[0] + house_dir
        new_dir = house_dir

        if new_hx < 0:
            new_hx = 1
            new_dir = 1
        elif new_hx > 4:
            new_hx = 3
            new_dir = -1

        new_goal = (new_hx, goal[1])

        for action, (dx, dy) in moves.items():
            new_px = pos[0] + dx
            new_py = pos[1] + dy
            new_pos = (new_px, new_py)

            if 0 <= new_px < 5 and 0 <= new_py < 9:
                if new_py == 8:
                    if new_pos == new_goal:
                        successors[action] = (new_pos, new_goal, new_dir)
                elif new_pos in self.allowed:
                    successors[action] = (new_pos, new_goal, new_dir)

        return successors

    def h(self, node):
        pos, _, _ = node.state
        return (8 - pos[1]) / 2


if __name__ == "__main__":
    p_in = input().split(",")
    h_in = input().split(",")
    h_dir_str = input().strip()

    p_pos = (int(p_in[0]), int(p_in[1]))
    h_pos = (int(h_in[0]), int(h_in[1]))
    initial_dir = 1 if h_dir_str == "right" else -1

    allowed = [(1, 0), (2, 0), (3, 0), (1, 1), (2, 1), (0, 2), (2, 2), (4, 2), (1, 3), (3, 3), (4, 3), (0, 4), (2, 4), (2, 5), (3, 5), (0, 6), (2, 6), (1, 7), (3, 7)]

    problem = House((p_pos, h_pos, initial_dir), allowed)
    solution = astar_search(problem)

    if solution:
        print(solution.solution())
    else:
        print("No Solution!")

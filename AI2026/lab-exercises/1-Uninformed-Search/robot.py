from searching_framework import *

class Robot(Problem):
    def __init__(self, initial, M1_pos, M1_steps, M2_pos, M2_steps,
                 to_collect_M1, to_collect_M2, walls):
        super().__init__(initial)
        self.M1_pos = M1_pos
        self.M1_steps = M1_steps
        self.M2_pos = M2_pos
        self.M2_steps = M2_steps
        self.to_collect_M1 = to_collect_M1
        self.to_collect_M2 = to_collect_M2
        self.walls = set(walls)

    def actions(self, state):
        return list(self.successor(state).keys())

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        x, y, collected_M1, collected_M2, M1_repaired, M2_repaired, repair_progress = state
        return M1_repaired and M2_repaired

    def successor(self, state):
        x, y, collected_M1, collected_M2, M1_repaired, M2_repaired, repair_progress = state
        successors = {}

        dir = {"Up": (0, +1), "Down": (0, -1), "Left": (-1, 0), "Right": (+1, 0)}

        for action, (dx, dy) in dir.items():
            nx, ny = x + dx, y + dy
            if 0 <= nx <= 9 and 0 <= ny <= 9 and (nx, ny) not in self.walls:
                new_collected_M1 = collected_M1
                new_collected_M2 = collected_M2

                if not M1_repaired and (nx, ny) in self.to_collect_M1:
                    idx = self.to_collect_M1.index((nx, ny))
                    if idx not in collected_M1:
                        new_collected_M1 = tuple(sorted(collected_M1 + (idx,)))

                if M1_repaired and not M2_repaired and (nx, ny) in self.to_collect_M2:
                    idx = self.to_collect_M2.index((nx, ny))
                    if idx not in collected_M2:
                        new_collected_M2 = tuple(sorted(collected_M2 + (idx,)))

                new_state = (nx, ny, new_collected_M1, new_collected_M2,
                             M1_repaired, M2_repaired, 0)
                successors[action] = new_state

        can_repair_M1 = (
            not M1_repaired and
            (x, y) == self.M1_pos and
            len(collected_M1) == len(self.to_collect_M1)
        )
        can_repair_M2 = (
            M1_repaired and
            not M2_repaired and
            (x, y) == self.M2_pos and
            len(collected_M2) == len(self.to_collect_M2)
        )

        if can_repair_M1:
            new_progress = repair_progress + 1
            if new_progress >= self.M1_steps:
                new_state = (x, y, collected_M1, collected_M2, True, M2_repaired, 0)
            else:
                new_state = (x, y, collected_M1, collected_M2, M1_repaired, M2_repaired, new_progress)
            successors["Repair"] = new_state

        elif can_repair_M2:
            new_progress = repair_progress + 1
            if new_progress >= self.M2_steps:
                new_state = (x, y, collected_M1, collected_M2, M1_repaired, True, 0)
            else:
                new_state = (x, y, collected_M1, collected_M2, M1_repaired, M2_repaired, new_progress)
            successors["Repair"] = new_state

        return successors


if __name__ == '__main__':
    robot_start_pos = tuple(map(int, input().split(',')))
    M1_pos = tuple(map(int, input().split(',')))
    M1_steps = int(input())
    M2_pos = tuple(map(int, input().split(',')))
    M2_steps = int(input())
    parts_M1 = int(input())
    to_collect_M1 = tuple([tuple(map(int, input().split(','))) for _ in range(parts_M1)])
    parts_M2 = int(input())
    to_collect_M2 = tuple([tuple(map(int, input().split(','))) for _ in range(parts_M2)])

    walls = [(4,0),(5,0),(7,5),(8,5),(9,5),(1,6),(1,7),(0,6),(0,8),(0,9),(1,9),(2,9),(3,9)]

    initial_state = (
        robot_start_pos[0],
        robot_start_pos[1],
        tuple(),
        tuple(),
        False,
        False,
        0
    )

    problem = Robot(
        initial_state,
        M1_pos, M1_steps,
        M2_pos, M2_steps,
        to_collect_M1, to_collect_M2,
        walls
    )

    result = breadth_first_graph_search(problem)

    if result:
        print(result.solution())
    else:
        print("No Solution!")
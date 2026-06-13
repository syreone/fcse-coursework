from searching_framework import *

class Robot(Problem):
    def __init__(self, initial, goal, m1_pos, m2_pos, s1, s2, parts_m1, parts_m2, walls):
        super().__init__(initial, goal)
        self.m1_pos = m1_pos
        self.m2_pos = m2_pos
        self.s1 = s1
        self.s2 = s2
        self.parts_m1 = parts_m1
        self.parts_m2 = parts_m2
        self.walls = walls

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        return state[4] and state[5]

    def successor(self, state):
        rx, ry, collected_m1, collected_m2, m1_repaired, m2_repaired, repair_count, can_teleport = state
        successors = {}
        directions = {"Up": (0, 1), "Down": (0, -1), "Left": (-1, 0), "Right": (1, 0)}

        for name, (dx, dy) in directions.items():
            nx, ny = rx + dx, ry + dy
            if 0 <= nx <= 9 and 0 <= ny <= 9 and (nx, ny) not in self.walls:
                new_cm1 = collected_m1
                new_cm2 = collected_m2
                if not m1_repaired:
                    for i, p in enumerate(self.parts_m1):
                        if (nx, ny) == p and not collected_m1[i]:
                            new_cm1 = list(collected_m1)
                            new_cm1[i] = True
                            new_cm1 = tuple(new_cm1)
                if m1_repaired and not m2_repaired:
                    for i, p in enumerate(self.parts_m2):
                        if (nx, ny) == p and not collected_m2[i]:
                            new_cm2 = list(collected_m2)
                            new_cm2[i] = True
                            new_cm2 = tuple(new_cm2)
                successors[name] = (nx, ny, new_cm1, new_cm2, m1_repaired, m2_repaired, 0, can_teleport)

        if can_teleport:
            if rx == 0 or rx == 9:
                tx = 9 if rx == 0 else 0
                ty = ry
                if (tx, ty) not in self.walls:
                    new_cm1 = collected_m1
                    new_cm2 = collected_m2
                    if not m1_repaired:
                        for i, p in enumerate(self.parts_m1):
                            if (tx, ty) == p and not collected_m1[i]:
                                new_cm1 = list(collected_m1)
                                new_cm1[i] = True
                                new_cm1 = tuple(new_cm1)
                    if m1_repaired and not m2_repaired:
                        for i, p in enumerate(self.parts_m2):
                            if (tx, ty) == p and not collected_m2[i]:
                                new_cm2 = list(collected_m2)
                                new_cm2[i] = True
                                new_cm2 = tuple(new_cm2)
                    successors["Teleport"] = (tx, ty, new_cm1, new_cm2, m1_repaired, m2_repaired, 0, False)


        if not m1_repaired and (rx, ry) == self.m1_pos and all(collected_m1):
            new_rc = repair_count + 1
            if new_rc >= self.s1:
                successors["Repair"] = (rx, ry, collected_m1, collected_m2, True, False, 0, can_teleport)
            else:
                successors["Repair"] = (rx, ry, collected_m1, collected_m2, False, False, new_rc, can_teleport)
        elif m1_repaired and not m2_repaired and (rx, ry) == self.m2_pos and all(collected_m2):
            new_rc = repair_count + 1
            if new_rc >= self.s2:
                successors["Repair"] = (rx, ry, collected_m1, collected_m2, True, True, 0, can_teleport)
            else:
                successors["Repair"] = (rx, ry, collected_m1, collected_m2, True, False, new_rc, can_teleport)

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

    initial = (robot_start_pos[0], robot_start_pos[1],
               tuple([False] * parts_M1),
               tuple([False] * parts_M2),
               False, False, 0, True)

    problem = Robot(initial, None, M1_pos, M2_pos, M1_steps, M2_steps,
                    to_collect_M1, to_collect_M2, walls)
    result = breadth_first_graph_search(problem)

    if result is None:
        print("No Solution!")
    else:
        print(result.solution())
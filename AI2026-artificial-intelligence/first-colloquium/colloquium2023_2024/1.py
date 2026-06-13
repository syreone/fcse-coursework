from searching_framework import *


# from utils import *
# from uninformed_search import *
# from informed_search import *

class Boxes(Problem):
    def __init__(self, initial, boxes, n, goal=None):
        super().__init__(initial, goal)
        self.boxes = boxes
        self.n = n

    def successor(self, state):
        successor = {}
        man_pos, filled_boxes = state
        mx, my = man_pos

        actions = ['Gore', 'Desno']
        directions = [(0,1), (1,0)]

        for action, (dx, dy) in zip(actions, directions):
            nmx = mx + dx
            nmy = my + dy

            if not (0 <= nmx < self.n and 0 <= nmy < self.n):
               continue
            if (nmx, nmy) in self.boxes:
                continue

            nfilled = list(filled_boxes)

            for (bx, by) in self.boxes:
                if (bx, by) not in nfilled:
                    if abs(nmx - bx) <= 1 and abs(nmy - by) <= 1:
                        nfilled.append((bx, by))

            successor[action] = ((nmx, nmy), tuple(sorted(nfilled)))

        return successor


    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        man_pos, filled_boxes = state
        return len(filled_boxes) == len(self.boxes)

if __name__ == '__main__':
    n = int(input())
    num_boxes = int(input())
    boxes = list()
    man_pos = (0, 0)

    for _ in range(num_boxes):
        boxes.append(tuple(map(int, input().split(','))))

    initial_state = (man_pos, tuple())
    problem = Boxes(initial_state, boxes, n)
    solution = breadth_first_graph_search(problem)

    if solution is None:
        print("No Solution!")
    else:
        print(solution.solution())
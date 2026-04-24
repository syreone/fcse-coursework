from searching_framework import Problem, breadth_first_graph_search

ROWS = 6
COLS = 8
GOAL_COL = 7
GOAL_ROWS = {2, 3}

OPPONENTS = [(5, 4), (3, 3)]
OPPONENT_CELLS = set(OPPONENTS)

def compute_ball_forbidden():
    forbidden = set()
    for (oc, or_) in OPPONENTS:
        for dc in [-1, 0, 1]:
            for dr in [-1, 0, 1]:
                nc, nr = oc + dc, or_ + dr
                if 0 <= nc < COLS and 0 <= nr < ROWS:
                    forbidden.add((nc, nr))
    return forbidden

BALL_FORBIDDEN = compute_ball_forbidden()

DIRECTIONS = {
    "up":         (0, +1),
    "down":       (0, -1),
    "right":      (+1, 0),
    "up-right":   (+1, +1),
    "down-right": (+1, -1),
}

def in_bounds(c, r):
    return 0 <= c < COLS and 0 <= r < ROWS

def is_goal(c, r):
    return c == GOAL_COL and r in GOAL_ROWS

def check_valid(man_pos, ball_pos):
    if man_pos == ball_pos:
        return False
    if man_pos in OPPONENT_CELLS:
        return False
    if ball_pos in BALL_FORBIDDEN:
        return False
    return True


class Football(Problem):
    def __init__(self, initial):
        super().__init__(initial)

    def goal_test(self, state):
        _, ball_pos = state
        return is_goal(ball_pos[0], ball_pos[1])

    def successor(self, state):
        man_pos, ball_pos = state
        mc, mr = man_pos
        bc, br = ball_pos
        successors = {}

        for dir_name, (dc, dr) in DIRECTIONS.items():
            new_mc, new_mr = mc + dc, mr + dr
            new_man = (new_mc, new_mr)

            if not in_bounds(new_mc, new_mr):
                continue
            if new_man in OPPONENT_CELLS:
                continue

            if new_man == ball_pos:
                new_bc, new_br = bc + dc, br + dr
                new_ball = (new_bc, new_br)
                if not in_bounds(new_bc, new_br):
                    continue
                goal = is_goal(new_bc, new_br)
                if not goal and new_ball in BALL_FORBIDDEN:
                    continue
                if not goal and not check_valid(new_man, new_ball):
                    continue
                successors[f"Push ball {dir_name}"] = (new_man, new_ball)
            else:
                if check_valid(new_man, ball_pos):
                    successors[f"Move man {dir_name}"] = (new_man, ball_pos)

        return successors

    def actions(self, state):
        return list(self.successor(state).keys())

    def result(self, state, action):
        return self.successor(state)[action]


if __name__ == '__main__':
    man_col, man_row = map(int, input().split(','))
    ball_col, ball_row = map(int, input().split(','))

    initial_state = ((man_col, man_row), (ball_col, ball_row))

    problem = Football(initial_state)
    result = breadth_first_graph_search(problem)

    if result:
        print(result.solution())
    else:
        print("No Solution!")
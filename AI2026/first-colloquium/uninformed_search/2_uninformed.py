from searching_framework import Problem, breadth_first_graph_search


class Football(Problem):
    def __init__(self, initial, defenders, goalpost):
        super().__init__(initial)
        self.defenders = defenders
        self.goalpost = goalpost

    def is_adjacent(self, pos):
        px, py = pos
        for dx, dy in self.defenders:
            if abs(px - dx) <= 1 and abs(py - dy) <= 1:
                return True
        return False

    def check_valid(self, state):
        player, ball, defenders, goalpost = state
        px, py = player

        if px < 0 or px > 7 or py < 0 or py > 5:
            return False

        if player == ball or player in defenders:
            return False
        return True

    def successor(self, state):
        successors = {}
        player, ball, defenders, goalpost = state
        px, py = player

        actions = ['Move man up', 'Move man down', 'Move man right',
                   'Move man up-right', 'Move man down-right']
        directions = [(0, 1), (0, -1), (1, 0), (1, 1), (1, -1)]

        for action, (dx, dy) in zip(actions, directions):
            npx, npy = px + dx, py + dy
            new_player = (npx, npy)

            if not (0 <= npx <= 7 and 0 <= npy <= 5):
                continue

            if new_player in defenders:
                continue

            if new_player != ball:
                successors[action] = (new_player, ball, defenders, goalpost)
            else:
                nbx, nby = ball[0] + dx, ball[1] + dy
                new_ball = (nbx, nby)
                if 0 <= nbx <= 7 and 0 <= nby <= 5 and new_ball not in defenders:
                    if not self.is_adjacent(new_ball):
                        push_action = action.replace('Move man', 'Push ball')
                        successors[push_action] = (new_player, new_ball, defenders, goalpost)
        return successors

    def actions(self, state):
        self._successors = self.successor(state)
        return self._successors.keys()

    def result(self, state, action):
        return self._successors[action]

    def goal_test(self, state):
        player, ball, defenders, goalpost = state
        return ball in goalpost


if __name__ == '__main__':
    player = tuple(map(int, input().split(',')))
    ball = tuple(map(int, input().split(',')))

    goalpost = ((7, 2), (7, 3))
    defenders = ((3, 3), (4, 5))

    initial_state = (player, ball, defenders, goalpost)

    problem = Football(initial_state, defenders, goalpost)
    solution = breadth_first_graph_search(problem)

    if solution is None:
        print('No Solution!')
    else:
        print(solution.solution())
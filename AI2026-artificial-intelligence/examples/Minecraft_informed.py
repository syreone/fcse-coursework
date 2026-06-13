from searching_framework import Problem, breadth_first_graph_search
from searching_framework import astar_search

def manhattan_distance(coords1, coords2):
    x1, y1 = coords1
    x2, y2 = coords2
    return abs(x1 - x2) + abs(y1 - y2)


def chebyshev_distance(coords1, coords2):
    x1, y1 = coords1
    x2, y2 = coords2
    return max(abs(x1 - x2), abs(y1 - y2))

class Minecraft(Problem):
    def __init__(self, initial, chest, crafting_table, quicksand):
        super().__init__(initial)
        self.chest = chest
        self.crafting_table = crafting_table
        self.quicksand = quicksand

    def check_valid(self, state):
        player, mushrooms, num_mushrooms, trees, num_trees, has_axe, stuck = state
        px, py = player

        if px < 0 or px > 9 or py < 0 or py > 6:
            return False

        if 3 <= px <= 6 and 4 <= py <= 6:
            return False
        if player in trees or player in mushrooms:
            return False

        if stuck and player not in self.quicksand:
            return False

        return True

    def successor(self, state):
        successors = {}
        player, mushrooms, num_mushrooms, trees, num_trees, has_axe, stuck  = state
        px, py = player

        actions = ['Move right', 'Move up-right', 'Move up', 'Move up-left',
                   'Move left', 'Move down-left', 'Move down', 'Move down-right']

        directions = [(1, 0 ), (1, 1), (0, 1), (-1, 1),
                      (-1,0), (-1, -1), (0,-1), (1, -1)]


        for action, (dx, dy) in zip(actions, directions):
            npx, npy = px + dx, py + dy
            nmushrooms = mushrooms
            nnum_mushrooms = num_mushrooms
            ntrees = trees
            nnum_trees = num_trees
            nhas_axe = has_axe
            nstuck = stuck

            if (npx, npy) == tuple(self.chest):
                nhas_axe = True
            elif (npx, npy) in mushrooms:
                nnum_mushrooms += 1
                nmushrooms = tuple(mushroom for mushroom in mushrooms if mushroom != (npx, npy))
            elif (npx, npy) in trees and has_axe:
                nnum_trees += 1
                ntrees = tuple(tree for tree in trees if tree!=(npx, npy))
            elif (npx, npy) in self.quicksand:
                nstuck = True

            new_state = ((npx, npy), nmushrooms, nnum_mushrooms, ntrees, nnum_trees, nhas_axe, nstuck)

            if self.check_valid(new_state):
                successors[action] = new_state

        if stuck and (px, py) in self.quicksand:
            successors['Unstuck'] = (player, mushrooms, num_mushrooms, trees, num_trees, has_axe, False)

        return successors

    def actions(self, state):
        return self.successor(state).keys()

    def result(self, state, action):
        return self.successor(state)[action]

    def goal_test(self, state):
        player, mushrooms, num_mushrooms, trees, num_trees, has_axe, stuck = state

        return num_mushrooms >=2 and num_trees>=3 and player == self.crafting_table

    def h(self, node):
        player, mushrooms, num_mushrooms, trees, num_trees, has_axe, stuck = node.state
        crafting_table = self.crafting_table

        return max((3-num_trees) + max(2-num_mushrooms, 0), chebyshev_distance(player, crafting_table))


if __name__ == '__main__':
    player = list(map(int, input().split(',')))
    chest  = list(map(int, input().split(',')))

    mushrooms = ((7, 4), (7, 6), (9, 5))
    trees = ((0, 6), (1, 6), (2, 6))
    crafting_table = (5,1)
    quicksand = ((0, 4), (1, 4), (2, 4))

    initial_state = (tuple(player), mushrooms, 0, trees, 0, False, False)
    #state = (player, mushrooms, num_mushrooms, trees, num_trees, has_axe, stuck)

    problem = Minecraft(initial_state, chest, crafting_table, quicksand)
    solution = astar_search(problem)

    if solution is not None:
        print(solution.solution())
    else:
        print('No Solution!')
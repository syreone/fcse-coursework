from constraint import *

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    n = 6
    num_trees = int(input())
    trees = []
    for _ in range(num_trees):
        row, col = map(int, input().split())
        trees.append((row, col))

    tree_set = set(trees)

    for i, (tr, tc) in enumerate(trees):
        neighbors = []
        for dr, dc in [(-1,0),(1,0),(0,-1),(0,1)]:
            nr, nc = tr + dr, tc + dc
            if 0 <= nr < n and 0 <= nc < n:
                if (nr, nc) not in tree_set:
                    neighbors.append((nr, nc))
        problem.addVariable(i, neighbors)

    problem.addConstraint(AllDifferentConstraint())

    def not_adjacent(pos1, pos2):
        r1, c1 = pos1
        r2, c2 = pos2
        return abs(r1 - r2) > 1 or abs(c1 - c2) > 1

    for i in range(num_trees):
        for j in range(i + 1, num_trees):
            problem.addConstraint(not_adjacent, (i, j))

    solutions = problem.getSolutions()

    if not solutions:
        print("No solution!")
    else:
        best = None
        best_sol = None

        for sol in solutions:
            ordered = tuple(sol[i] for i in range(num_trees))
            if best is None or ordered < best:
                best = ordered
                best_sol = sol

        for i in range(num_trees):
            r, c = best_sol[i]
            print(r, c)
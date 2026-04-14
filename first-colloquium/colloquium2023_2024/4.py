from constraint import *

def column_constraint(*tent_variables):
    counts = [0 for _ in range(n)]
    for tent in tent_variables:
        column = tent[0]
        counts[column] = counts[column] + 1
    for col in range(n):
        if counts[col] != col_counts[col]:
            return False
    return True

def not_a_tree(*tent_variables):
    for tent in tent_variables:
        if tent in tree_set:
            return False
    return True

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    n = 6
    num_trees = int(input())
    trees = []
    for _ in range(num_trees):
        row, col = map(int, input().split())
        trees.append((row, col))
    tree_set = set(trees)
    col_counts = list(map(int, input().split()))
    for i, (tr, tc) in enumerate(trees):
        neighbors = []
        for i2 in [-1, 1]:
            new_r = tr + i2
            new_c = tc + i2
            if 0 <= new_r < n:
                neighbors.append((new_r, tc))
            if 0 <= new_c < n:
                neighbors.append((tr, new_c))
        problem.addVariable(i, neighbors)
    problem.addConstraint(AllDifferentConstraint())
    problem.addConstraint(not_a_tree, list(range(num_trees)))
    problem.addConstraint(column_constraint, list(range(num_trees)))
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
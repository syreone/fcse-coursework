from constraint import Problem, BacktrackingSolver, AllDifferentConstraint, MaxSumConstraint, ExactSumConstraint


if __name__ == '__main__':
    K = int(input())
    grid = [list(map(int, input().split())) for _ in range(K)]
    N = max(map(max, grid))  # Number of regions

    problem = Problem(solver=BacktrackingSolver())



    # Dodadete gi promenlivite i domenite tuka.
    # Add the variables and domains here.
    #-----------------------------------------------------------------

    for r in range(K):
        for c in range(K):
            problem.addVariable(f'x_{r}_{c}', [0, 1])

    region_variables = {reg: [] for reg in range(1, N+1)}
    for r in range(K):
        for c in range(K):
            region_variables[grid[r][c]].append(f'x_{r}_{c}')

    all_variables = [f'x_{r}_{c}' for r in range(K) for c in range(K)]

    #-------------------------------------------------------------------
    # Dodadete gi ogranichuvanjata tuka.
    # Add the constraints here.

    problem.addConstraint(ExactSumConstraint(N), all_variables)

    for reg in range(1, N+1):
        problem.addConstraint(MaxSumConstraint(2), region_variables[reg])

    for r in range(K):
        for c1 in range(K):
            for c2 in range(c1 + 1, K):
                if grid[r][c1] != grid[r][c2]:
                    problem.addConstraint(MaxSumConstraint(1), [f'x_{r}_{c1}', f'x_{r}_{c2}'])

    for c in range(K):
        for r1 in range(K):
            for r2 in range(r1 + 1, K):
                if grid[r1][c] != grid[r2][c]:
                    problem.addConstraint(MaxSumConstraint(1), [f'x_{r1}_{c}', f'x_{r2}_{c}'])

    for r in range(K):
        for c in range(K):
            for dr, dc in [(0, 1), (1, 0)]:
                nr, nc = r + dr, c + dc
                if 0 <= nr < K and 0 <= nc < K and grid[r][c] == grid[nr][nc]:
                    problem.addConstraint(MaxSumConstraint(1), [f'x_{r}_{c}', f'x_{nr}_{nc}'])

    result = problem.getSolution()

    #------------------------------------------------------------------
    # Ispechatete go reshenieto vo baraniot format.
    # Print the solution in the required format.
    if result is None:
        print('No Solution!')
    else:
        for r in range(K):
            print(' '.join(
                '*' if result[f'x_{r}_{c}'] == 1 else str(grid[r][c])
                for c in range(K)
            ))



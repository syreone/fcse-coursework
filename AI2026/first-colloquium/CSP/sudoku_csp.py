from constraint import *

if __name__ == "__main__":
    solver_name = input().strip()

    if solver_name == "BacktrackingSolver":
        solver = BacktrackingSolver()
    elif solver_name == "RecursiveBacktrackingSolver":
        solver = RecursiveBacktrackingSolver()
    else:
        solver = MinConflictsSolver()

    problem = Problem(solver)
    problem.addVariables(range(81), range(1, 10))

    for r in range(9):
        problem.addConstraint(AllDifferentConstraint(), [r * 9 + c for c in range(9)])

    for c in range(9):
        problem.addConstraint(AllDifferentConstraint(), [r * 9 + c for r in range(9)])

    for br in range(3):
        for bc in range(3):
            block = [(br * 3 + r) * 9 + (bc * 3 + c)
                     for r in range(3) for c in range(3)]
            problem.addConstraint(AllDifferentConstraint(), block)

    solution = problem.getSolution()
    print(solution)

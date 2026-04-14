from constraint import *

def non_attacking(pos1, pos2):
    r1, c1 = pos1
    r2, c2 = pos2
    return r1 != r2 and c1 != c2 and abs(r1-r2) != abs(c1-c2)

if __name__ == '__main__':
    n = int(input())

    problem = Problem(BacktrackingSolver())
    positions = [(r, c) for r in range(n) for c in range(n)]
    problem.addVariables(range(1, n+1), positions)

    for i in range(1, n+1):
        for j in range(i+1, n+1):
            problem.addConstraint(non_attacking, [i,j])

    if n<=6:
        solutions = problem.getSolutions()
        print(len(solutions))
    else:
        solution = problem.getSolution()
        print(solution)


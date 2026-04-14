from constraint import *

def check_line(values, variables):
    blocks = []
    continuous  = False
    temp = 0
    for variable in variables:
        if variable == 1:
            continuous = True
            temp += 1
        if variable == 0 and continuous:
            continuous = False
            blocks.append(temp)
            temp = 0
        if temp > 0:
            blocks.append(temp)
        if not blocks:
            blocks.append(0)

        return values == tuple(blocks)

if __name__ == '__main__':
    n = int(input())
    rows = [tuple(map(int, input().split(','))) for _ in range(n)]
    cols = [tuple(map(int, input().split(','))) for _ in range(n)]

    variables = [(i, j) for i in range(n) for j in range(n)]
    domain = [0, 1]

    problem = Problem(BacktrackingSolver())
    problem.addVariable(variables, domain)

    for i in range(n):
        problem.addConstraint(lambda *x, col=i: check_line(cols[col], x), [(i, j) for j in range(n)])
        problem.addConstraint(lambda *x, row=i: check_line(rows[row], x), [(j, i) for j in range(n)])

    solutions = problem.getSolutions()
    print(solutions)

    solution = solutions[0]
    for key, value in solution.items():
        print(key, value)



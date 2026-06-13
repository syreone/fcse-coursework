from constraint import *

def edge_value_constraint(value1, value2, edge):
    return edge == abs(value1 - value2)

if __name__ == '__main__':
    n = int(input())
    edges = [input() for _ in range(n-1)]

    problem = Problem(BacktrackingSolver())

    vertices = [f'{i+1}' for i in range(n)]
    DV = [i+1 for i in range(n)]
    DE = [i+1 for i in range(n-1)]

    problem.addVariables(vertices, DV)
    problem.addVariables(edges, DE)

    problem.addConstraint(AllDifferentConstraint(), vertices)
    problem.addConstraint(AllDifferentConstraint(), edges)

    for edge in edges:
        vertex1, vertex2 = edge.split(',')
        problem.addConstraint(edge_value_constraint, [vertex1, vertex2, edge])

    print(problem.getSolutions())


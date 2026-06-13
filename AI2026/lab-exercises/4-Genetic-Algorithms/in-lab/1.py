import pygad
import math
# 1va zadaca so chadori, baranjeto bese da se modificira takashto mora sekoj chador da bide upotreben, ne smee da ima neiskoristeni chadori
N, M, R = map(float, input().split())
N = int(N)
M = int(M)
points = [tuple(map(float, input().split())) for _ in range(N)]

def decode(solution):
    umbrellas = []
    for i in range(0, len(solution), 2):
        x, y = solution[i], solution[i + 1]
        umbrellas.append((x, y))
    return umbrellas[:M]

def fitness_func(ga, solution, idx):
    umbrellas = decode(solution)
    num_umbrellas = len(umbrellas)
    uncovered = 0
    for px, py in points:
        if not any(math.sqrt((px - ux) ** 2 + (py - uy) ** 2) <= R for ux, uy in umbrellas):
            uncovered += 1
    large_overlap = 0
    small_overlap = 0
    for i in range(num_umbrellas):
        for j in range(i + 1, num_umbrellas):
            d = math.sqrt((umbrellas[i][0] - umbrellas[j][0]) ** 2 +
                          (umbrellas[i][1] - umbrellas[j][1]) ** 2)
            if d < 1.6 * R:
                large_overlap += 1
            elif d < 2 * R:
                small_overlap += 1
    score = -(uncovered * 1000000)
    score -= (large_overlap * 1000)
    score -= (small_overlap * 100)
    score -= (num_umbrellas * 1)
    return score

gene_space = []
for _ in range(M):
    gene_space.extend([{'low': R, 'high': 10 - R}, {'low': R, 'high': 10 - R}])

params = {
    'num_generations': 500,
    'sol_per_pop': 100,
    'num_parents_mating': 50,
    'num_genes': 2 * M,
    'gene_space': gene_space,
    'fitness_func': fitness_func,
    'mutation_num_genes': 1,
    'save_best_solutions': True
}

ga = pygad.GA(**params)
ga.run()

solution, _, _ = ga.best_solution()
fitness = fitness_func(None, solution, 0)
best_solutions = ga.best_solutions

print(solution)
print(fitness)

chromosomes = [
    [9, 9, 9, 8, 8, 9],
    [1, 1, 9, 9, 8, 8],
    [1, 1, 5, 5, 1.1, 1.1],
    [1, 1, 5, 5, 2.8, 1],
    [1.05, 1, 5, 5, 9, 9]
]

best_solutions_sorted = sorted(
    best_solutions,
    key=lambda sol: fitness_func(None, sol, 0),
    reverse=False
)
ss = sorted(
    chromosomes,
    key=lambda sol: fitness_func(None, sol, 0),
    reverse=False
)
submit_data(fitness_func, decode, ss, best_solutions_sorted)
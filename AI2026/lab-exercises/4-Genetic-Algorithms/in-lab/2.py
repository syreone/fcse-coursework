import pygad
# baranjeto beshe deka dvajcata prijateli se dvizhat so razlichni brzini, ima modifikatori sho se chitaat on
# input T1 I T2 shto oznachuvaat skaliranje vremeto na patuvanje (mnozhiteli) i po sekoj tret pominat grad mora da se napravi zadolzhitelna pauza od 10 vremenski ednici
# vtorata primer ako T1 e 1 a T2 e 2, T2 e duplo pobrz od prviot odnosno rutata bi ja pominal za pola vreme od prviot
N = int(input())
S, E = map(int, input().split())
T1, T2 = map(float, input().split())
dist = [list(map(float, input().split())) for _ in range(N)]

cities = [i for i in range(N) if i != S and i != E]
C = len(cities)


def decode(solution):
    f1, f2 = [], []
    for idx, city in enumerate(cities):
        friend = int(round(solution[2 * idx]))
        priority = solution[2 * idx + 1]
        if friend == 0:
            f1.append((priority, city))
        else:
            f2.append((priority, city))
    f1.sort()
    f2.sort()
    route1 = [S] + [c for _, c in f1] + [E]
    route2 = [S] + [c for _, c in f2] + [E]
    return route1, route2


def route_cost(route, speed_multiplier):
    cost = 0
    for i in range(len(route) - 1):
        cost += dist[route[i]][route[i + 1]]
    cost *= speed_multiplier

    num_cities = len(route)
    num_pauses = num_cities // 3
    cost += num_pauses * 10

    return cost


def fitness_func(ga, solution, idx):
    route1, route2 = decode(solution)
    t1 = route_cost(route1, T1)
    t2 = route_cost(route2, T2)
    makespan = max(t1, t2)
    penalty = 0

    if t1 > 0 and t2 > 0:
        ratio = max(t1, t2) / min(t1, t2)
        if ratio > 2:
            penalty += 10000

    size_diff = abs(len(route1) - len(route2))
    penalty += size_diff * 100

    return -(makespan + penalty)


gene_space = []
for _ in range(C):
    gene_space.append([0, 1])
    gene_space.append({'low': 0, 'high': 1})

params = {
    'num_generations': 500,
    'sol_per_pop': 100,
    'num_parents_mating': 50,
    'num_genes': 2 * C,
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

route1, route2 = decode(solution)
print("Friend 1 route:", route1)
print("Friend 2 route:", route2)
print("Fitness:", fitness)

best_solutions_sorted = sorted(
    best_solutions,
    key=lambda sol: fitness_func(None, sol, 0),
    reverse=False
)

submit_data(fitness_func, decode, best_solutions_sorted)
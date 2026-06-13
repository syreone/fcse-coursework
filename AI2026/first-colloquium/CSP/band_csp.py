from constraint import *

if __name__ == '__main__':

    bands = dict()

    band_info = input()
    while band_info != 'end':
        band, time, genre = band_info.split(' ')
        bands[band] = (time, genre)
        band_info = input()

    variables = list(bands.keys())
    domain = [f'S{i + 1}' for i in range(3)]

    problem = Problem(BacktrackingSolver())
    problem.addVariables(variables, domain)

    def capacity_constraint(*args):
        for stage in domain:
            count_120 = 0
            count_under_80 = 0
            for i, assigned_stage in enumerate(args):
                if assigned_stage == stage:
                    b_id = variables[i]
                    duration = int(bands[b_id][0])
                    if duration == 120:
                        count_120 += 1
                    if duration < 80:
                        count_under_80 += 1
            if count_120 > 1 or count_under_80 > 5:
                return False
        return True

    problem.addConstraint(capacity_constraint, variables)

    genre_groups = {}
    for b_id, (time, genre) in bands.items():
        if genre not in genre_groups:
            genre_groups[genre] = {'ids': [], 'total': 0}
        genre_groups[genre]['ids'].append(b_id)
        genre_groups[genre]['total'] += int(time)

    for group in genre_groups.values():
        if group['total'] <= 300:
            problem.addConstraint(AllEqualConstraint(), group['ids'])

    result = problem.getSolution()

    if result:
        for b_id in variables:
            print(f"{b_id} ({bands[b_id]}): {result[b_id]}")
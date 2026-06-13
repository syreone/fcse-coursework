from constraint import Problem, BacktrackingSolver


def read_input():
    num_families = int(input())
    families = {}
    for _ in range(num_families):
        name, size, reqs_string = input().split()
        reqs = reqs_string.split('-')
        families[name] = {'size': int(size), 'requirements': reqs}

    num_rooms = int(input())
    rooms = {}
    for _ in range(num_rooms):
        room_id, capacity, amenities_string = input().split()
        floor = room_id[0]
        amenities = amenities_string.split('-')
        rooms[int(room_id)] = {'floor': int(floor), 'capacity': int(capacity), 'amenities': amenities}

    return families, rooms


if __name__ == '__main__':
    problem = Problem(solver=BacktrackingSolver())
    families, rooms = read_input()

    valid_rooms = {}
    for fname, family in families.items():
        valid_rooms[fname] = [
            rid for rid, room in rooms.items()
            if room['capacity'] >= family['size']
            and all(req in room['amenities'] for req in family['requirements'])
        ]

    for fname in families:
        problem.addVariable(fname, valid_rooms[fname] + [None])

    family_names = list(families.keys())

    for i in range(len(family_names)):
        for j in range(i + 1, len(family_names)):
            f1, f2 = family_names[i], family_names[j]
            problem.addConstraint(
                lambda a, b: a is None or b is None or a != b,
                [f1, f2]
            )

    for fname in families:
        for r in valid_rooms[fname]:
            competitors = [g for g in families if g != fname and r in valid_rooms[g]]

            if not competitors:
                problem.addConstraint(
                    lambda val: val is not None,
                    [fname]
                )
            else:
                problem.addConstraint(
                    lambda f_val, *comp_vals, room=r:
                        f_val is not None or room in comp_vals,
                    [fname] + competitors
                )

    solutions = problem.getSolutions()

    if not solutions:
        print("No solution found")
    else:
        best = max(solutions, key=lambda sol: sum(
            families[fname]['size'] for fname, v in sol.items() if v is not None))

        print("Best assignment:")
        assignments = sorted(
            [(room_id, guest) for guest, room_id in best.items() if room_id is not None]
        )
        for room_id, guest in assignments:
            print(f"{guest}->{room_id}")
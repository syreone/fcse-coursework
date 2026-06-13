from constraint import *


def capacity_constraint(*slots):
    for slot in set(slots):
        if slots.count(slot) > 4:
            return False
    return True


if __name__ == '__main__':
    num = int(input())

    papers = dict()
    paper_info = input()
    while paper_info != 'end':
        title, topic = paper_info.split(' ')
        papers[title] = topic
        paper_info = input()

    variables = list(papers.keys())
    domain = [f'T{i + 1}' for i in range(num)]

    problem = Problem(BacktrackingSolver())
    problem.addVariables(variables, domain)

    problem.addConstraint(capacity_constraint, variables)

    area_groups = {}
    for p_id, area in papers.items():
        if area not in area_groups:
            area_groups[area] = []
        area_groups[area].append(p_id)

    for area, group_papers in area_groups.items():
        if len(group_papers) <= 4:
            problem.addConstraint(AllEqualConstraint(), group_papers)

    result = problem.getSolution()

    if result:
        for p_id in variables:
            print(f"{p_id} ({papers[p_id]}): {result[p_id]}")


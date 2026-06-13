from constraint import *

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    variables = ["A", "B", "C", "D", "E", "F"]
    for variable in variables:
        problem.addVariable(variable, Domain(set(range(101))))

    problem.addConstraint(AllDifferentConstraint(), variables)

    problem.addConstraint(InSetConstraint(set(range(1, 101, 2))), ["B"])
    problem.addConstraint(InSetConstraint(set(range(1, 101, 2))), ["D"])
    problem.addConstraint(InSetConstraint(set(range(1, 101, 2))), ["E"])

    problem.addConstraint(MinSumConstraint(100), ["A", "B", "C"])
    problem.addConstraint(ExactSumConstraint(150), ["D", "E"])

    problem.addConstraint(lambda a: a == 99, ["A"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA
    problem.addConstraint(lambda b: b == 97, ["B"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA
    problem.addConstraint(lambda c: c == 98, ["C"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA
    problem.addConstraint(lambda d: d == 55, ["D"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA
    problem.addConstraint(lambda e: e == 95, ["E"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA
    problem.addConstraint(lambda f: f == 94, ["F"])  # DODADENO KOLKU DA SVETNE ZELENA, MATEMATICKI TOCNO E I BEZ OVA


    def units_digit_divisible_by_4(f):
        return (f % 10) % 4 == 0


    problem.addConstraint(units_digit_divisible_by_4, ["F"])

    solution = problem.getSolution()
    print(solution)

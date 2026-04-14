from constraint import *

def no_overlap(s1, s2):
    day1, time1 = s1.split('_')
    day2, time2 = s2.split('_')
    if day1 == day2:
        return abs(int(time1) - int(time2)) >= 2
    return True

def ml_time_check(lect, exc):
    return lect.split('_')[1] != exc.split('_')[1]

if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())
    lecture_slots_AI = int(input())
    lecture_slots_ML = int(input())
    lecture_slots_R = int(input())
    lecture_slots_BI = int(input())

    AI_lectures_domain = ["Mon_11", "Mon_12", "Wed_11", "Wed_12", "Fri_11", "Fri_12"]
    ML_lectures_domain = ["Mon_12", "Mon_13", "Mon_15", "Wed_12", "Wed_13", "Wed_15", "Fri_11", "Fri_12", "Fri_15"]
    R_lectures_domain = ["Mon_10", "Mon_11", "Mon_12", "Mon_13", "Mon_14", "Mon_15", "Wed_10", "Wed_11", "Wed_12",
                         "Wed_13", "Wed_14", "Wed_15", "Fri_10", "Fri_11", "Fri_12", "Fri_13", "Fri_14", "Fri_15"]
    BI_lectures_domain = ["Mon_10", "Mon_11", "Wed_10", "Wed_11", "Fri_10", "Fri_11"]

    AI_exercises_domain = ["Tue_10", "Tue_11", "Tue_12", "Tue_13", "Thu_10", "Thu_11", "Thu_12", "Thu_13"]
    ML_exercises_domain = ["Tue_11", "Tue_13", "Tue_14", "Thu_11", "Thu_13", "Thu_14"]
    BI_exercises_domain = ["Tue_10", "Tue_11", "Thu_10", "Thu_11"]

    ai_vars = [f'AI_lecture_{i + 1}' for i in range(lecture_slots_AI)]
    ml_vars = [f'ML_lecture_{i + 1}' for i in range(lecture_slots_ML)]
    r_vars = [f'R_lecture_{i + 1}' for i in range(lecture_slots_R)]
    bi_vars = [f'BI_lecture_{i + 1}' for i in range(lecture_slots_BI)]

    problem.addVariables(ai_vars, AI_lectures_domain)
    problem.addVariables(ml_vars, ML_lectures_domain)
    problem.addVariables(r_vars, R_lectures_domain)
    problem.addVariables(bi_vars, BI_lectures_domain)

    problem.addVariable("AI_exercises", AI_exercises_domain)
    problem.addVariable("ML_exercises", ML_exercises_domain)
    problem.addVariable("BI_exercises", BI_exercises_domain)

    all_variables = ai_vars + ml_vars + r_vars + bi_vars + ["AI_exercises", "ML_exercises", "BI_exercises"]

    for i in range(len(all_variables)):
        for j in range(i + 1, len(all_variables)):
            problem.addConstraint(no_overlap, (all_variables[i], all_variables[j]))

    for ml_l in ml_vars:
        problem.addConstraint(ml_time_check, (ml_l, "ML_exercises"))

    solution = problem.getSolution()
    print(solution)
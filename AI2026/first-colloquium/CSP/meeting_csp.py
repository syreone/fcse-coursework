from constraint import *


def meeting_constraints(m_att, s_att, p_att, time):
    if s_att == 0:
        return False

    if m_att + p_att < 1:
        return False

    s = [13, 14, 16, 19]
    m = [14, 15, 18]
    p = [12, 13, 16, 17, 18, 19]

    if time not in s:
        return False

    if m_att == 1 and time not in m:
        return False

    if p_att == 1 and time not in p:
        return False

    if m_att == 1 and p_att == 1:
        if time not in m or time not in p:
            return False

    return True


if __name__ == '__main__':
    problem = Problem(BacktrackingSolver())

    problem.addVariable("Marija_attendance", [0, 1])
    problem.addVariable("Simona_attendance", [1])
    problem.addVariable("Petar_attendance", [0, 1])
    problem.addVariable("time_meeting", [13, 14, 16, 19])

    problem.addConstraint(meeting_constraints,["Marija_attendance", "Simona_attendance", "Petar_attendance", "time_meeting"])

    [print(solution) for solution in problem.getSolutions()]
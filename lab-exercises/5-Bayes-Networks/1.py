import warnings
warnings.filterwarnings(action='ignore', category=FutureWarning)

from pgmpy.models import DiscreteBayesianNetwork
from pgmpy.factors.discrete import TabularCPD
from pgmpy.inference import VariableElimination

model = DiscreteBayesianNetwork([
    ("B", "I"),
    ("M", "I"),
    ("I", "R"),
    ("I", "T"),
    ("R", "C"),
    ("T", "C"),
    ("D", "C")
])

cpd_B = TabularCPD(variable="B", variable_card=2, values=[[0.65], [0.35]])
cpd_M = TabularCPD(variable="M", variable_card=2, values=[[0.55], [0.45]])
cpd_D = TabularCPD(variable="D", variable_card=2, values=[[0.75], [0.25]])

cpd_I = TabularCPD(
    variable="I", variable_card=2,
    values=[
        [0.88, 0.35, 0.28, 0.07],
        # i = 0
        [0.12, 0.65, 0.72, 0.93]
        # i = 1
    ],
    evidence=["B", "M"], evidence_card=[2, 2]
)

cpd_R = TabularCPD(
    variable="R", variable_card=2,
    values=[[0.80, 0.20], [0.20, 0.80]],
    #         i = 0           i = 1
    evidence=["I"], evidence_card=[2]
)

cpd_T = TabularCPD(
    variable="T", variable_card=2,
    values=[[0.70, 0.15], [0.30, 0.85]],
    #         i = 0           i = 1
    evidence=["I"], evidence_card=[2]
)

cpd_C = TabularCPD(
    variable="C", variable_card=2,
    values=[
        [0.96, 0.68, 0.52, 0.25, 0.45, 0.22, 0.12, 0.03],
        # c = 0
        [0.04, 0.32, 0.48, 0.75, 0.55, 0.78, 0.88, 0.97]
        # c = 1
    ],
    evidence=["R", "T", "D"], evidence_card=[2, 2, 2]
)

model.add_cpds(cpd_B, cpd_M, cpd_D, cpd_I, cpd_R, cpd_T, cpd_C)
print("Model valid:", model.check_model())

infer = VariableElimination(model)

q1 = infer.query(variables=["I"], evidence={"B": 1, "M": 1})
print(q1)

q2 = infer.query(variables=["R"], evidence={"I": 1})
print(q2)

q3 = infer.query(variables=["C"], evidence={"T": 1})
print(q3)

q4 = infer.query(variables=["B"], evidence={"I": 1})
print(q4)

q5 = infer.query(variables=["M"], evidence={"I": 1})
print(q5)

q6 = infer.query(variables=["T"], evidence={"C": 1, "R": 0})
print(q6)
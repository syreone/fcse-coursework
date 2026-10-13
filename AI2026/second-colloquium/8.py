import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'

import warnings
from sklearn.exceptions import ConvergenceWarning
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from sklearn.neural_network import MLPClassifier

warnings.filterwarnings("ignore", category=ConvergenceWarning)
from dataset8 import data

if __name__ == '__main__':
    model_name = input()
    col = int(input())

    class_0 = [row for row in data if row[-1] == 0]
    class_1 = [row for row in data if row[-1] == 1]

    def quarter(rows, k):
        n = len(rows)
        return rows[n * k // 4: n * (k + 1) // 4]

    parts = [quarter(class_0, k) + quarter(class_1, k) for k in range(4)]

    def make_model():
        if model_name == 'NB':
            return GaussianNB()
        return MLPClassifier(hidden_layer_sizes=(50,), activation='relu',
                             learning_rate_init=0.001, random_state=0)

    def evaluate(train_set, test_set, remove_col=None):
        train_x = [row[:-1] for row in train_set]
        test_x = [row[:-1] for row in test_set]
        if remove_col is not None:
            train_x = [r[:remove_col] + r[remove_col + 1:] for r in train_x]
            test_x = [r[:remove_col] + r[remove_col + 1:] for r in test_x]
        train_y = [row[-1] for row in train_set]
        test_y = [row[-1] for row in test_set]
        clf = make_model()
        clf.fit(train_x, train_y)
        return accuracy_score(test_y, clf.predict(test_x))

    accuracies = []
    for i in range(4):
        test_set = parts[i]
        train_set = [row for j in range(4) if j != i for row in parts[j]]
        accuracies.append(evaluate(train_set, test_set))

    print("Prosechna tochnost:", sum(accuracies) / 4)

    best = accuracies.index(max(accuracies))
    test_set = parts[best]
    train_set = [row for j in range(4) if j != best for row in parts[j]]
    print("Tochnost so otstraneta kolona:", evaluate(train_set, test_set, col))
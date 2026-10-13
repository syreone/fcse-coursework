import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'

import warnings
from sklearn.exceptions import ConvergenceWarning
from sklearn.naive_bayes import CategoricalNB, GaussianNB
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.neural_network import MLPClassifier
from dataset7 import data


if __name__ == '__main__':
    warnings.filterwarnings('ignore', category=ConvergenceWarning)
    mode = input()
    split = int(input())

    if mode == 'balanced':
        class_0 = [row for row in data if row[-1] == 0]
        class_1 = [row for row in data if row[-1] == 1]
        s0 = int(len(class_0) * split/100)
        s1 = int(len(class_1) * split/100)
        train_set = class_0[:s0] + class_1[:s1]
        test_set = class_0[s0:] + class_1[s1:]
    else:
        s = int(len(data) * split / 100)
        train_set = data[:s]
        test_set = data[s:]

    train_x = [row[:-1] for row in train_set]
    train_y = [row[-1] for row in train_set]
    test_x = [row[:-1] for row in test_set]
    test_y = [row[-1] for row in test_set]

    models = [
        GaussianNB(),
        RandomForestClassifier(n_estimators=50, criterion='entropy', random_state=0),
        MLPClassifier(hidden_layer_sizes=(50,), activation='relu', learning_rate_init=0.001, random_state=0),
    ]
    names = ['prviot', 'vtoriot', 'tretiot']

    best_precision = -1
    best_accuracy = 0
    best_index = 0
    for i, model in enumerate(models):
        model.fit(train_x, train_y)
        predictions = model.predict(test_x)

        TP = FP = TN = FN = 0
        for real, pred in zip(test_y, predictions):
            if pred == 1 and real == 1:
                TP += 1
            elif pred == 1 and real == 0:
                FP += 1
            elif pred == 0 and real == 0:
                TN += 1
            else:
                FN += 1

        precision = TP / (TP + FP) if TP + FP != 0 else 0
        accuracy = (TP + TN) / (TP + FP + TN + FN)

        if precision > best_precision:
            best_precision = precision
            best_accuracy = accuracy
            best_index = i

    print("Najvisoka preciznost ima", names[best_index], "klasifikator")
    print("Negovata tochnost e:", best_accuracy)
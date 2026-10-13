import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'

import warnings
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import accuracy_score
from dataset5 import data

warnings.filterwarnings("ignore")

if __name__ == '__main__':
    learning_rate = float(input())
    epoch_num = int(input())

    class_0 = [row for row in data if row[-1] == 0]
    class_1 = [row for row in data if row[-1] == 1]

    split_0 = int(len(class_0) * 0.8)
    split_1 = int(len(class_1) * 0.8)

    train_set = class_0[:split_0] + class_1[:split_1]
    val_set = class_0[split_0:] + class_1[split_1:]

    train_x = [row[:-1] for row in train_set]
    train_y = [row[-1] for row in train_set]
    val_x = [row[:-1] for row in val_set]
    val_y = [row[-1] for row in val_set]

    clf = MLPClassifier(hidden_layer_sizes=(6,), activation='tanh',
                        learning_rate_init=learning_rate, max_iter=epoch_num,
                        random_state=0)
    clf.fit(train_x, train_y)

    train_acc = accuracy_score(train_y, clf.predict(train_x))
    val_acc = accuracy_score(val_y, clf.predict(val_x))

    if train_acc > val_acc * 1.15:
        print("Se sluchuva overfitting")
    else:
        print("Ne se sluchuva overfitting")
        print("Tochnost so trenirachko mnozhestvo:", train_acc)
        print("Tochnost so validacisko mnozhestvo:", val_acc)
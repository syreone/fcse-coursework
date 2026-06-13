import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.preprocessing import OrdinalEncoder
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
import numpy as np

if __name__ == '__main__':
    percent = int(input()) / 100
    criterion = input().strip()

    X = [list(row[:-1]) for row in dataset]
    y = [row[-1] for row in dataset]

    encoder = OrdinalEncoder()
    X_enc = encoder.fit_transform(X)

    split = int(len(X_enc) * (1 - percent))

    test_X, train_X = X_enc[:split], X_enc[split:]
    test_y, train_y = y[:split], y[split:]

    classifier = DecisionTreeClassifier(criterion=criterion, random_state=0)
    classifier.fit(train_X, train_y)

    preds = classifier.predict(test_X)
    acc = accuracy_score(test_y, preds)

    importances = classifier.feature_importances_

    print(f"Depth: {classifier.get_depth()}")
    print(f"Number of leaves: {classifier.get_n_leaves()}")
    print(f"Accuracy: {acc}")
    print(f"Most important feature: {np.argmax(importances)}")
    print(f"Least important feature: {np.argmin(importances)}")

    submit_train_data(train_X, train_y)
    submit_test_data(test_X, test_y)
    submit_classifier(classifier)
    submit_encoder(encoder)
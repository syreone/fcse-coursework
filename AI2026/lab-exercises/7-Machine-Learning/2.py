import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score

if __name__ == '__main__':
    col_index = int(input())
    n_trees = int(input())
    criterion = input().strip()
    entry = [float(x) for x in input().split()]

    X = [list(row[:-1]) for row in dataset]
    y = [row[-1] for row in dataset]

    X = [[float(x) for x in row[:col_index]] + [float(x) for x in row[col_index+1:]] for row in X]
    entry = entry[:col_index] + entry[col_index+1:]

    split = int(len(X) * 0.85)
    train_X, test_X = X[:split], X[split:]
    train_y, test_y = y[:split], y[split:]

    classifier = RandomForestClassifier(n_estimators=n_trees, criterion=criterion, random_state=0)
    classifier.fit(train_X, train_y)

    preds = classifier.predict(test_X)
    acc = accuracy_score(test_y, preds)
    print(f"Accuracy: {acc}")

    prediction = classifier.predict([entry])[0]
    print(prediction)

    probabilities = classifier.predict_proba([entry])[0]
    print(probabilities)

    submit_train_data(train_X, train_y)
    submit_test_data(test_X, test_y)
    submit_classifier(classifier)

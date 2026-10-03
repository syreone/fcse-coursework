import os

os.environ['OPENBLAS_NUM_THREADS'] = '1'
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
from dataset import dataset

if __name__ == "__main__":
    col_index = int(input())
    num_trees = int(input())
    criterion = input()

    data_x = [[float(x) for x in row[:-1]] for row in dataset]
    data_y = [row[-1] for row in dataset]
    data_x = [row[:col_index] + row[col_index + 1:] for row in data_x]

    split = int(len(dataset) * 0.85)
    train_set_x = data_x[:split]
    train_set_y = data_y[:split]
    test_set_x = data_x[split:]
    test_set_y = data_y[split:]

    clf = RandomForestClassifier(n_estimators=num_trees, criterion=criterion, random_state=0)
    clf.fit(train_set_x, train_set_y)

    accuracy = accuracy_score(test_set_y, clf.predict(test_set_x))
    print("Accuracy:", accuracy)

    newTestEntry = input().split(" ")
    newTestEntry = [float(x) for x in newTestEntry]
    del newTestEntry[col_index]
    newTestEntry = [newTestEntry]

    predictedClass = clf.predict(newTestEntry)[0]
    print(predictedClass)
    print(clf.predict_proba(newTestEntry))
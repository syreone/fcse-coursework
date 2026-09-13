import os

os.environ['OPENBLAS_NUM_THREADS'] = '1'
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
from dataset2 import dataset

if __name__ == "__main__":
    train_set = [dataset[i] for i in range (0, int(len(dataset) * 0.85))]
    test_set = [dataset[i] for i in range (int(len(dataset) * 0.85), len(dataset))]

    train_set_x = [[float(x) for x in train_set[i][0:-1]] for i in range(0, len(train_set))]
    train_set_y = [int(train_set[i][-1]) for i in range(0, len(train_set))]

    test_set_x = [[float(x) for x in test_set[i][0:-1]] for i in range(0, len(test_set))]
    test_set_y = [int(test_set[i][-1]) for i in range(0, len(test_set))]

    clf = GaussianNB()
    clf.fit(train_set_x, train_set_y)

    accuracy = accuracy_score(test_set_y, clf.predict(test_set_x))
    print(accuracy)

    newTestEntry = input().split(" ")
    newTestEntry = [[float(x) for x in newTestEntry]]
    predictedClass = clf.predict(newTestEntry)[0]
    print(predictedClass)
    print(clf.predict_proba(newTestEntry))
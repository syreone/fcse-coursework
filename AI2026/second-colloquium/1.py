import os

os.environ['OPENBLAS_NUM_THREADS'] = '1'
from sklearn.naive_bayes import CategoricalNB
from sklearn.preprocessing import OrdinalEncoder
from sklearn.metrics import accuracy_score
from dataset1 import dataset

if __name__ == "__main__":
    train_set = [dataset[i] for i in range(0, int(len(dataset) * 0.75))]
    test_set = [dataset[i] for i in range(int(len(dataset) * 0.75), len(dataset))]

    encoder = OrdinalEncoder()
    encoder.fit([dataset[i][0:-1] for i in range(0, len(dataset))])

    train_set_x = [train_set[i][0:-1] for i in range(0, len(train_set))]
    train_set_x = encoder.transform(train_set_x)
    train_set_y = [train_set[i][-1] for i in range(0, len(train_set))]

    test_set_x = [test_set[i][0:-1] for i in range(0, len(test_set))]
    test_set_x = encoder.transform(test_set_x)
    test_set_y = [test_set[i][-1] for i in range(0, len(test_set))]

    clf = CategoricalNB()
    clf.fit(train_set_x, train_set_y)

    accuracy = accuracy_score(test_set_y, clf.predict(test_set_x))
    print(accuracy)

    newTestEntry = input().split(" ")
    newTestEntry = encoder.transform([newTestEntry])
    predictedClass = clf.predict(newTestEntry)[0]
    print(predictedClass)
    print(clf.predict_proba(newTestEntry))
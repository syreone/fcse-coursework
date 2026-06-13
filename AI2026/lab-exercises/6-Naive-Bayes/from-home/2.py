import os

os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score

if __name__ == '__main__':
    entry = [float(x) for x in input().split()]

    data_0 = [row for row in dataset if int(row[-1]) == 0]
    data_1 = [row for row in dataset if int(row[-1]) == 1]

    split_0 = int(len(data_0) * 0.85)
    split_1 = int(len(data_1) * 0.85)

    train_set = data_0[:split_0] + data_1[:split_1]
    test_set = data_0[split_0:] + data_1[split_1:]

    train_X = [[float(x) for x in row[:-1]] for row in train_set]
    train_Y = [int(row[-1]) for row in train_set]

    test_X = [[float(x) for x in row[:-1]] for row in test_set]
    test_Y = [int(row[-1]) for row in test_set]

    classifier = GaussianNB()
    classifier.fit(train_X, train_Y)

    accuracy = accuracy_score(test_Y, classifier.predict(test_X))
    print(accuracy)

    prediction = classifier.predict([entry])[0]
    print(prediction)

    probabilities = classifier.predict_proba([entry])
    print(probabilities)

    submit_train_data(train_X, train_Y)
    submit_test_data(test_X, test_Y)
    submit_classifier(classifier)

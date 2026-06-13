import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score

#Treniranje na poslednite 75%, testiranje na prvite 25%
#Dodadena vtora linija na vlez: indeksi od test mnozestvoto
#Tochnost 1 = tocnost na celoto test mnozestvo
#Tochnost 2 = tocnost samo na izbranite indeksi

if __name__ == '__main__':
    entry = [float(x) for x in input().split()]
    indices = [int(x) for x in input().split()]

    dataset_list = list(dataset)
    split = len(dataset_list) // 4

    test_set = dataset_list[:split]
    train_set = dataset_list[split:]

    train_X = [[float(x) for x in row[:-1]] for row in train_set]
    train_Y = [int(float(row[-1])) for row in train_set]

    test_X = [[float(x) for x in row[:-1]] for row in test_set]
    test_Y = [int(float(row[-1])) for row in test_set]

    classifier = GaussianNB()
    classifier.fit(train_X, train_Y)

    accuracy1 = accuracy_score(test_Y, classifier.predict(test_X))
    print(f"Tochnost 1: {accuracy1}")

    prediction = classifier.predict([entry])[0]
    print(prediction)

    probabilities = classifier.predict_proba([entry])
    print(probabilities)

    selected_X = [test_X[i] for i in indices]
    selected_Y = [test_Y[i] for i in indices]
    accuracy2 = accuracy_score(selected_Y, classifier.predict(selected_X))
    print(f"Tochnost 2: {accuracy2}")

    submit_train_data(train_X, train_Y)
    submit_test_data(test_X, test_Y)
    submit_classifier(classifier)
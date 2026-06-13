import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.preprocessing import OrdinalEncoder
from sklearn.naive_bayes import CategoricalNB
from sklearn.metrics import accuracy_score

if __name__ == '__main__':
    percent = int(input()) / 100
    entry = input().split()

    X = [row[:-1] for row in dataset]
    Y = [row[-1] for row in dataset]

    encoder = OrdinalEncoder()
    X_encoded = encoder.fit_transform(X)

    split = int(len(dataset) * percent)
    train_X, train_Y = X_encoded[:split], Y[:split]
    test_X, test_Y = X_encoded[split:], Y[split:]

    classifier = CategoricalNB()
    classifier.fit(train_X, train_Y)

    accuracy = accuracy_score(test_Y, classifier.predict(test_X))
    print(accuracy)

    entry_enc = encoder.transform([entry])
    print(classifier.predict(entry_enc)[0])
    print(classifier.predict_proba(entry_enc))

    submit_train_data(train_X, train_Y)
    submit_test_data(test_X, test_Y)
    submit_classifier(classifier)
    submit_encoder(encoder)
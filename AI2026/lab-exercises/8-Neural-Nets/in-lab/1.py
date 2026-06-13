import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

dataset_sample = [[180.0, 23.6, 25.2, 27.9, 25.4, 14.0, 'Roach'],
                  [12.2, 11.5, 12.2, 13.4, 15.6, 10.4, 'Smelt'],
                  [135.0, 20.0, 22.0, 23.5, 25.0, 15.0, 'Perch'],
                  [1600.0, 56.0, 60.0, 64.0, 15.0, 9.6, 'Pike'],
                  [120.0, 20.0, 22.0, 23.5, 26.0, 14.5, 'Perch']]

if __name__ == '__main__':
    num_neurons = int(input())
    mod_ds = [row[:col_to_remove_idx] + row[col_to_remove_idx+1:] for row in dataset]

    X = [list(map(float, row[:-1])) for row in mod_ds]
    y = [row[-1] for row in mod_ds]

    train_X, test_X, train_y, test_y = train_test_split(X, y, train_size=0.8, shuffle=False)

    classifier = MLPClassifier((num_neurons,), max_iter=500, random_state=0)
    classifier.fit(train_X, train_y)

    pred_y = classifier.predict(test_X)
    acc = accuracy_score(test_y, pred_y)
    print(f'Tochnost: {acc}')

    new_sample = list(map(float, input().split()))
    new_sample = [new_sample[i] for i in range(len(new_sample)) if i % 2 == 0]
    # i % 2 == 0 go zamenuva col_to_remove_idx

    new_sample_pred = classifier.predict([new_sample])[0]
    print(f'Predvidena klasa: {new_sample_pred}')
    print(f'Verojatnosti: {classifier.predict_proba([new_sample])[0]}')

    submit_train_data(train_X, train_y)
    submit_test_data(test_X, test_y)
    submit_classifier(classifier)
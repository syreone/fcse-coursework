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

    col_to_remove_idx = int(input())
    mod_ds = [row[:col_to_remove_idx] + row[col_to_remove_idx + 1:] for row in dataset]

    X = [list(map(float, row[:-1])) for row in mod_ds]
    y = [row[-1] for row in mod_ds]

    train_X, temp_X, train_y, temp_y = train_test_split(X, y, train_size=0.7, shuffle=False)
    val_X, test_X, val_y, test_y = train_test_split(temp_X, temp_y, train_size=1/3, shuffle=False)

    clf1 = MLPClassifier(hidden_layer_sizes=(10,), max_iter=500, random_state=0)
    clf2 = MLPClassifier(hidden_layer_sizes=(20,), max_iter=500, random_state=0)

    clf1.fit(train_X, train_y)
    clf2.fit(train_X, train_y)

    acc1 = accuracy_score(val_y, clf1.predict(val_X))
    acc2 = accuracy_score(val_y, clf2.predict(val_X))

    print(f'Tochnost na validacisko mnozestvo so 10 nevroni: {acc1}')
    print(f'Tochnost na validacisko mnozestvo so 20 nevroni: {acc2}')

    better_clf = clf1 if acc1 >= acc2 else clf2
    test_acc = accuracy_score(test_y, better_clf.predict(test_X))

    print(f'Tochnost na testiracko mnozestvo: {test_acc}')

    submit_train_data(train_X, train_y)
    submit_test_data(test_X, test_y)
    submit_classifier(better_clf)
import os

os.environ['OPENBLAS_NUM_THREADS'] = '1'
from sklearn.tree import DecisionTreeClassifier
from sklearn.preprocessing import OrdinalEncoder
from sklearn.metrics import accuracy_score
from dataset3 import dataset

if __name__ == "__main__":
    X = int(input())
    criterion = input()

    n_test = int(len(dataset) * (100 - X) / 100)
    test_set = [dataset[i] for i in range (0, n_test)]
    train_set = [dataset[i] for i in range (n_test, len(dataset))]

    train_set_x = [row[0:-1] for row in train_set]
    train_set_y = [row[-1] for row in train_set]

    test_set_x = [row[0:-1] for row in test_set]
    test_set_y = [row[-1] for row in test_set]

    encoder = OrdinalEncoder()
    encoder.fit([row[0:-1] for row in dataset])
    train_set_x = encoder.transform(train_set_x)
    test_set_x = encoder.transform(test_set_x)

    clf = DecisionTreeClassifier(criterion=criterion, random_state=0)
    clf.fit(train_set_x, train_set_y)

    accuracy = accuracy_score(test_set_y, clf.predict(test_set_x))
    print("Depth:", clf.get_depth())
    print("Number of leaves:", clf.get_n_leaves())
    print("Accuracy:", accuracy)

    feature_importance = list(clf.feature_importances_)
    most_important = feature_importance.index(max(feature_importance))
    least_important = feature_importance.index(min(feature_importance))
    print("Most important feature:", most_important)
    print("Least important feature:", least_important)

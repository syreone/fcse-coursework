import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'

from sklearn.preprocessing import OrdinalEncoder
from sklearn.tree import DecisionTreeClassifier
from dataset6 import dataset

X = int(input())
criteria = 'gini'
classifier = DecisionTreeClassifier(criterion=criteria, random_state=0)

split = len(dataset) - X
train_set = dataset[:split]
test_set = dataset[split:]

train_x = [row[:-1] for row in train_set]
train_y = [row[-1] for row in train_set]
test_x = [row[:-1] for row in test_set]
test_y = [row[-1] for row in test_set]

encoder = OrdinalEncoder()
encoder.fit([row[:-1] for row in dataset])
train_x = encoder.transform(train_x)
test_x = encoder.transform(test_x)

classifier.fit(train_x, train_y)
predictions = classifier.predict(test_x)

TP = FP = TN = FN = 0
for real, pred in zip(test_y, predictions):
    if pred == '1' and real == '1':
        TP += 1
    elif pred == '1' and real == '0':
        FP += 1
    elif pred == '0' and real == '0':
        TN += 1
    else:
        FN += 1

accuracy = (TP + TN) / (TP + FP + TN + FN)
precision = TP / (TP + FP) if TP + FP != 0 else 0.0

print ("Accuracy:", accuracy)
print("Precision:", precision)
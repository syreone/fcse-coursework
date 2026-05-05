import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'
from submission_script import *
from dataset_script import dataset
from sklearn.preprocessing import OrdinalEncoder
from sklearn.naive_bayes import CategoricalNB
from sklearn.metrics import accuracy_score

# Treniranje na poslednite 90%, testiranje na prvite 10% (namesto X% od vlez)
# Dodadena vtora tocnost so threshold T (cita se od vlez namesto procentot)
# Ako max verojatnost e pogolemo od T ispechati klasa, inaku pechati UNKNOWN

if __name__ == '__main__':
    percent = float(input())
    entry = input().split()

    X = [row[:-1] for row in dataset]
    y = [row[-1] for row in dataset]

    encoder = OrdinalEncoder()
    X_enc = encoder.fit_transform(X)

    split = int(len(X_enc) * 0.1)
    test_X, train_X = X_enc[:split], X_enc[split:]
    test_y, train_y = y[:split], y[split:]

    model = CategoricalNB()
    model.fit(train_X, train_y)

    preds_y = model.predict(test_X)
    acc = accuracy_score(test_y, preds_y)
    print(f"Tochnost: {acc}")

    probas = model.predict_proba(test_X)
    threshold_preds = []
    for i, proba in enumerate(probas):
        if max(proba) > percent:
            threshold_preds.append(model.classes_[proba.argmax()])
        else:
            threshold_preds.append("UNKNOWN")

    threshold_correct = sum(1 for t, r in zip(threshold_preds, test_y) if t == r)
    threshold_acc = threshold_correct / len(test_y)
    print(f"Tochnost so threshold: {threshold_acc}")

    entry_enc = encoder.transform([entry])
    pred_y = model.predict(entry_enc)[0]
    print(f"Predvidena klasa: {pred_y}")

    entry_proba = model.predict_proba(entry_enc)[0]
    if max(entry_proba) > percent:
        print(f"Predvidena klasa so threshold: {pred_y}")
    else:
        print(f"Predvidena klasa so threshold: UNKNOWN")

    submit_train_data(train_X, train_y)
    submit_test_data(test_X, test_y)
    submit_classifier(model)
    submit_encoder(encoder)
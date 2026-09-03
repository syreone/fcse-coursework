import os
os.environ['OPENBLAS_NUM_THREADS'] = '1'

from sklearn.naive_bayes import CategoricalNB
from sklearn.preprocessing import OrdinalEncoder
from sklearn.metrics import accuracy_score
from zad1_dataset import dataset
import numpy as np

# ===== STEP 1: LOAD AND PREPARE DATA =====
# Convert dataset to numpy array for easier manipulation
data = np.array(dataset)

# Separate features (all columns except last) from labels (last column)
X = data[:, :-1]  # All rows, all columns except the last
y = data[:, -1]   # All rows, last column (the class label)

print(f"Total samples: {len(X)}")
print(f"Features per sample: {X.shape[1]}")
print(f"Unique classes: {np.unique(y)}")

# ===== STEP 2: ENCODE CATEGORICAL DATA =====
# OrdinalEncoder converts categorical values (letters, numbers) to integers 0, 1, 2, ...
encoder = OrdinalEncoder(handle_unknown='use_encoded_value', unknown_value=-1)
X_encoded = encoder.fit_transform(X)

# Also encode the labels (y values) for consistency
y_encoded = np.array([int(label) for label in y])

# ===== STEP 3: SPLIT DATA (75% train, 25% test) =====
split_idx = int(0.75 * len(X_encoded))

X_train = X_encoded[:split_idx]
y_train = y_encoded[:split_idx]

X_test = X_encoded[split_idx:]
y_test = y_encoded[split_idx:]

print(f"\nTraining samples: {len(X_train)}")
print(f"Testing samples: {len(X_test)}")

# ===== STEP 4: TRAIN NAIVE BAYES MODEL =====
# CategoricalNB is designed for categorical features
model = CategoricalNB()
model.fit(X_train, y_train)

# ===== STEP 5: EVALUATE ON TEST SET =====
y_pred = model.predict(X_test)
accuracy = accuracy_score(y_test, y_pred)

print(f"\n{'='*50}")
print(f"MODEL ACCURACY on test set: {accuracy:.4f} ({accuracy*100:.2f}%)")
print(f"{'='*50}")

# ===== STEP 6: PREDICT ON NEW RECORD FROM USER INPUT =====
print("\nEnter a new record to predict (comma-separated categorical values):")
print("Example: C,S,O,1,2,1,1,2,1,2")
user_input = input("Your record: ").strip()

# Parse and encode the user input
new_record = np.array([user_input.split(',')])
new_record_encoded = encoder.transform(new_record)

# Make prediction
predicted_class = model.predict(new_record_encoded)[0]

# Get prediction probabilities for all classes
probabilities = model.predict_proba(new_record_encoded)[0]
classes = model.classes_

# ===== STEP 7: DISPLAY RESULTS =====
print(f"\n{'='*50}")
print(f"PREDICTION RESULTS")
print(f"{'='*50}")
print(f"Input record: {user_input}")
print(f"Predicted class: {predicted_class}")
print(f"\nClass probabilities:")
for cls, prob in zip(classes, probabilities):
    print(f"  Class {cls}: {prob:.4f} ({prob*100:.2f}%)")
print(f"{'='*50}")

# Titanic Data Set
# https://www.kaggle.com/c/titanic/details/getting-started-with-python

import csv as csv 
import numpy as np
import pandas as pd
import os
import collections

"""
General Data Variables Structure:
[0]PassengerID [1]Survived [2]Pclass [3]Name [4]Sex [5]Age [6]SibSp
[7]Parch [8]Ticket [9]Fare [10]Cabin [11]Embarked
"""

trainfile = (os.getcwd() + '/train.csv').strip()
csv_file_object = csv.reader(open(trainfile, 'rb')) 
header = csv_file_object.next()

data=[]
for row in csv_file_object:
    data.append(row)
data = np.array(data)

number_passengers = np.size(data[0::,1].astype(np.float))
number_survived = np.sum(data[0::,1].astype(np.float))
proportion_survivors = number_survived / number_passengers

# Get an array of T/F for women and men.
women_only_stats = data[0::,4] == "female"
men_only_stats = data[0::,4] != "female"

women_onboard = data[women_only_stats,1].astype(np.float)
men_onboard = data[men_only_stats,1].astype(np.float)

proportion_women_survived = np.sum(women_onboard) / np.size(women_onboard)  
proportion_men_survived = np.sum(men_onboard) / np.size(men_onboard) 

print 'Proportion of women who survived is %s' % proportion_women_survived
print 'Proportion of men who survived is %s' % proportion_men_survived


#Continue Here ...
# Reading the test data and writing the gender model as a csv
# https://www.kaggle.com/c/titanic/details/getting-started-with-python

# Reader
testfile= os.getcwd() + '/test.csv'
test_file = open(testfile.strip(), 'rb')
test_file_object = csv.reader(test_file)
header = test_file_object.next()
print header

# Writer
prediction_file = open("genderbasedmodel.csv", "wb")
prediction_file_object = csv.writer(prediction_file)

prediction_file_object.writerow(["PassengerId","Survived"])
for row in test_file_object:
    if row[3] is 'female':
        prediction_file_object.writerow([row[0],'1'])
    else:
        prediction_file_object.writerow([row[0],'0'])
test_file.close()
prediction_file.close();

#train = pd.read_csv('/Users/bmc/Desktop/Kaggle/titanic_dataset/train.csv')
#test = pd.read_csv('/Users/bmc/Desktop/Kaggle/titanic_dataset/test.csv')

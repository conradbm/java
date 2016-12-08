# Kaggle Titanic Challenge ---
# What key factors determined survival from this data set?
# Build a classification model. 
"
This tutorial has given me examples in:
1. Pulling in train/test data.
2. Filling in missing columns on data for test from train.
3. Reordering columns in a data.frame object.
4. Appending rows from one data.frame to another (joining datasets).
5. Understanding of r data types: factors, ints, characters, ect..
6. Understanding of ggplot and plotting layers from categorical variables.
7. Using the stringr library to grep through strings and create new variables in the data to see if it's predictive.
8. Feature Engineering for predictivity. Ex) family size
   
"
setwd("/Users/bmc/Desktop/Kaggle/titanic_dataset/")

# Bring in raw data
train_data <- read.csv("train.csv", header = TRUE)
test_data <- read.csv("test.csv", header = TRUE)

# Add a new empty column named "Survived"
test_data.survived <- data.frame(Survived= rep("None", nrow(test_data)),test_data)
head(test_data.survived)

# Swap Columns
test_data.survived <- test_data.survived[,c(2,1,3,4,5,6,7,8,9,10,11,12,13)]

#Make sure our columns match up
head(test_data.survived)
head(train_data)

# Append rows of two data.frame's -- arg2 to arg1
data.combined <- rbind(train_data, test_data.survived)
head(data.combined)

#Observe the data types -- note: Factors are like enums.
str(data.combined)

#Change strings and ints to categorical variables --> factors
data.combined$Survived <- as.factor(data.combined$Survived)
data.combined$Pclass <- as.factor(data.combined$Pclass)

# Take a look at gross survival rates
table(data.combined$Survived) #catgorize based on the factor
table(data.combined$Pclass) #categorize based on factor

library(ggplot2)

str(train_data)

train_data$Pclass <- as.factor(train_data$Pclass)
train_data$Survived <- as.factor(train_data$Survived)

str(train_data)

# Read in english ..
# Make a ggplot ...(you add layers with this plot)
   # from train_data,
   # on the xaxis we want PClass, filled by survived categorical var
   # add another layer on those specs
   # a proportion bar chart based on our categorical var
   # label our xasis,
   # label our yaxis,
   # label our legend
ggplot(train_data, aes(x = Pclass, fill = Survived)) +
  geom_bar() +
  xlab("Pclass") +
  ylab("Total Count") +
  labs(fill = "Survived") 
       
# Examine the first few names of our training set
head(as.character(train_data$Name))

# How many unique names are there across both train & test?
length(unique(as.character(data.combined$Name)))

# duplicated returns a logical vector indicating which elements(rows) are duplicates

#Get TRUE/FALSE indices representing duplicates
dup_name_list <- duplicated(data.combined$Name)

#Show which indices are TRUE
dup_name_indices = which(duplicateNames)

#Pull the correct observations with duplicate indices in the Names column
dup.names <- data.combined[dup_name_indices, "Name"]

#dup.names <- as.character(data.combined[which(duplicated(as.character(data.combined$Name))), "Name"])

# Find which observations of Name are in(==) our duplicate observations
data.combined[which(data.combined$Name %in% dup.names), ]

# What is up with the 'Miss.' and 'Mr.' thing?
library(stringr)

# Any correlation with other variables (e.g., sibsp)?
misses <- data.combined[which(str_detect(data.combined$Name, "Miss.")),]
misses[1:5,]

# Hypothesis - Name titles correlate with age
mrses <- data.combined[which(str_detect(data.combined$Name, "Mrs.")), ]
mrses[1:5,]

# Check out males to see if pattern continues
males <- data.combined[which(train_data$Sex == "male"), ]
males[1:5,]

# Make a quick visualization of males vs. females that survived
ggplot(train_data, aes(x = Sex, fill = Survived)) +
  geom_bar() +
  xlab("Sex") +
  ylab("Total Count") +
  labs(fill = "Survived") 

# Expand upon the realtionship between `Survived` and `Pclass` by adding the new `Title` variable to the
# data set and then explore a potential 3-dimensional relationship.

# Create a utility function to help with title extraction
# NOTE - Using the grep function here, but could have used the str_detect function as well.
extractTitle <- function(name) {
  name <- as.character(name)
  
  if (length(grep("Miss.", name)) > 0) {
    return ("Miss.")
  } else if (length(grep("Master.", name)) > 0) {
    return ("Master.")
  } else if (length(grep("Mrs.", name)) > 0) {
    return ("Mrs.")
  } else if (length(grep("Mr.", name)) > 0) {
    return ("Mr.")
  } else {
    return ("Other")
  }
}


# NOTE - The code below uses a for loop which is not a very R way of
#        doing things
titles <- NULL
for (i in 1:nrow(data.combined)) {
  #titles <- c(titles, extractTitle(data.combined[i,"Name"]))
  titles[i] <- extractTitle(data.combined[i, "Name"])
 
}
data.combined$Title <- as.factor(titles)
head(data.combined)

#Read in english...
# for the first 891 observations,
# Plot the Title filled by category survived +
#  ..with a statcount width of 0.5 + 
#  ..for each category of Pclass +
#  ..fill each by the survived categorical variable
ggplot(data.combined[1:891,], aes(x = Title, fill = Survived)) +
  stat_count(width = 0.5) +
  facet_wrap(~Pclass) + 
  ggtitle("Pclass") +
  xlab("Title") +
  ylab("Total Count") +
  labs(fill = "Survived")

# Treat the parch vaiable as a factor and visualize
data.combined$parch <- as.factor(data.combined$parch)

ggplot(data.combined[1:891,], aes(x = Parch, fill = Survived)) +
  stat_count(width = 1) +
  facet_wrap(~Pclass + Title) + 
  ggtitle("Pclass, Title") +
  xlab("ParCh") +
  ylab("Total Count") +
  labs(fill = "Survived")

# Let's try some feature engineering. What about creating a family size feature?
temp.sibsp <- c(train_data$SibSp, test_data$SibSp)
temp.parch <- c(train_data$Parch, test_data$Parch)
data.combined$family.size <- as.factor(temp.sibsp + temp.parch + 1)

#Generally for ggplot you stack layers, by width and facet, then label accordingly..
# Visualize it to see if it is predictive
ggplot(data.combined[1:891,], aes(x = family.size, fill = Survived)) +
  stat_count(width = 1) +
  facet_wrap(~Pclass + Title) + 
  ggtitle("Pclass, Title") +
  xlab("family.size") +
  ylab("Total Count") +
  ylim(0,300) +
  labs(fill = "Survived")

# Take a look at the ticket variable
str(data.combined$Ticket)
data.combined[1:10, "Ticket"]

# Based on the huge number of levels ticket really isn't a factor variable it is a string. 
# Convert it and display first 20
data.combined$Ticket <- as.character(data.combined$Ticket)
data.combined$Ticket[1:20]

# There's no immediately apparent structure in the data, let's see if we can find some.
# We'll start with taking a look at just the first char for each
ticket.first.char <- ifelse(data.combined$Ticket == "", 
                            " ", 
                            substr(data.combined$Ticket, 1, 1))
unique(ticket.first.char)

# OK, we can make a factor for analysis purposes and visualize
data.combined$ticket.first.char <- as.factor(ticket.first.char)

# First, a high-level plot of the data
ggplot(data.combined[1:891,], aes(x = ticket.first.char, fill = Survived)) +
  geom_bar() +
  ggtitle("Survivability by ticket.first.char") +
  xlab("ticket.first.char") +
  ylab("Total Count") +
  ylim(0,350) +
  labs(fill = "Survived")

# Ticket seems like it might be predictive, drill down a bit
ggplot(data.combined[1:891,], aes(x = ticket.first.char, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass) + 
  ggtitle("Pclass") +
  xlab("ticket.first.char") +
  ylab("Total Count") +
  ylim(0,150) +
  labs(fill = "Survived")

# Lastly, see if we get a pattern when using combination of pclass & title
ggplot(data.combined[1:891,], aes(x = ticket.first.char, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass + Title) + 
  ggtitle("Pclass, Title") +
  xlab("ticket.first.char") +
  ylab("Total Count") +
  ylim(0,200) +
  labs(fill = "Survived")


# Next up - the fares Titanic passengers paid
summary(data.combined$Fare)
length(unique(data.combined$Fare))


# Can't make fare a factor, treat as numeric & visualize with histogram
ggplot(data.combined, aes(x = Fare)) +
  stat_count(width = 5) +
  ggtitle("Combined Fare Distribution") +
  xlab("Fare") +
  ylab("Total Count") +
  ylim(0,200)


# Let's check to see if fare has predictive power
ggplot(data.combined[1:891,], aes(x = Fare, fill = Survived)) +
  stat_count(width = 5) +
  facet_wrap(~Pclass + Title) + 
  ggtitle("Pclass, Title") +
  xlab("Fare") +
  ylab("Total Count") +
  ylim(0,50) + 
  labs(fill = "Survived")




# Analysis of the cabin variable
str(data.combined$Cabin)


# Cabin really isn't a factor, make a string and the display first 100
data.combined$Cabin <- as.character(data.combined$Cabin)
data.combined$Cabin[1:100]


# Replace empty cabins with a "U"
data.combined[which(data.combined$Cabin == ""), "Cabin"] <- "U"
data.combined$Cabin[1:100]


# Take a look at just the first char as a factor
cabin.first.char <- as.factor(substr(data.combined$Cabin, 1, 1))
str(cabin.first.char)
levels(cabin.first.char)


# Add to combined data set and plot 
data.combined$cabin.first.char <- cabin.first.char

# High level plot
ggplot(data.combined[1:891,], aes(x = cabin.first.char, fill = Survived)) +
  geom_bar() +
  ggtitle("Survivability by cabin.first.char") +
  xlab("cabin.first.char") +
  ylab("Total Count") +
  ylim(0,750) +
  labs(fill = "Survived")

# Could have some predictive power, drill in
ggplot(data.combined[1:891,], aes(x = cabin.first.char, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass) +
  ggtitle("Survivability by cabin.first.char") +
  xlab("Pclass") +
  ylab("Total Count") +
  ylim(0,500) +
  labs(fill = "Survived")

# Does this feature improve upon pclass + title?
ggplot(data.combined[1:891,], aes(x = cabin.first.char, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass + Title) +
  ggtitle("Pclass, Title") +
  xlab("cabin.first.char") +
  ylab("Total Count") +
  ylim(0,500) +
  labs(fill = "Survived")


# What about folks with multiple cabins?
data.combined$cabin.multiple <- as.factor(ifelse(str_detect(data.combined$Cabin, " "), "Y", "N"))

ggplot(data.combined[1:891,], aes(x = cabin.multiple, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass + Title) +
  ggtitle("Pclass, Title") +
  xlab("cabin.multiple") +
  ylab("Total Count") +
  ylim(0,350) +
  labs(fill = "Survived")




# Does survivability depend on where you got onboard the Titanic?
str(data.combined$Embarked)
levels(data.combined$Embarked)


# Plot data for analysis
ggplot(data.combined[1:891,], aes(x = Embarked, fill = Survived)) +
  geom_bar() +
  facet_wrap(~Pclass + Title) +
  ggtitle("Pclass, Title") +
  xlab("Embarked") +
  ylab("Total Count") +
  ylim(0,300) +
  labs(fill = "Survived")

#****** UP TO THIS POINT WE HAVE DONE SEVERAL KEY THINGS... WE HAVE FORMATTED OUR DATA, WE HAVE DONE FEATURE ENGINEERING TO SEE IF CERTAIN FEATURES ARE PREDICTIVE, AND WE HAVE PLOTTED OUR DATA SEVERAL TIMES TO GET AN IDEA OF WHAT VARIABLES CORRELATE TOGETHER... THIS SHOULD HAVE GIVEN US A VERY GOOD IDEA OF THE DATA SET WE ARE WORKING WITH AND HOW WE OUGHT TO INTERACT WITH IT VIA A MODEL OF CHOICE. ****#


#==============================================================================
#
# Video #4 - Exploratory Modeling
#
#==============================================================================

# Random Forest Algorithm:
#                1. Given a data.frame (spreadsheet) object
#                2. With replacement, randomly samples the data set and build a forest of 1000 trees
#                3. From the words not in the 'bag' sampled, use those to test against the forest
#                4. This gives us an error rate of how good our forest is at predicting our classifier

library(randomForest)

rf.train.1 <- data.combined[1:891, c("Pclass", "Title")]
rf.label <- as.factor(train_data$Survived)

set.seed(1234)
rf.1 <- randomForest(x = rf.train.1, 
                     y = rf.label, 
                     importance = TRUE, 
                     ntree = 1000)
rf.1
varImpPlot(rf.1)
# RESULTS: error rate: 20.76% --> ~80% accuracy w/ two variables, NOT BAD.


# Train an RF from train_data to predict rf.label == as.factor(train_data$Survived)
rf.train.2 <- data.combined[1:891, c("Pclass", "Title", "SibSp")]

set.seed(1234)
rf.2 <- randomForest(x = rf.train.2, y = rf.label, importance = TRUE, ntree = 1000) #500-1000 trees is standard...
rf.2
varImpPlot(rf.2)
#RESULTS: error rate: 19.3%


# Train a Random Forest using pclass, title, & parch
rf.train.3 <- data.combined[1:891, c("Pclass", "Title", "Parch")]

set.seed(1234)
rf.3 <- randomForest(x = rf.train.3, y = rf.label, importance = TRUE, ntree = 1000)
rf.3
varImpPlot(rf.3)
#RESULTS: error rate: 20.09%

# Train a Random Forest using pclass, title, sibsp, parch
rf.train.4 <- data.combined[1:891, c("Pclass", "Title", "SibSp", "Parch")]

set.seed(1234)
rf.4 <- randomForest(x = rf.train.4, y = rf.label, importance = TRUE, ntree = 1000)
rf.4
varImpPlot(rf.4)
#RESULTS: error rate: 18.41%

# Train a Random Forest using pclass, title, & family.size
rf.train.5 <- data.combined[1:891, c("Pclass", "Title", "family.size")]

set.seed(1234)
rf.5 <- randomForest(x = rf.train.5, y = rf.label, importance = TRUE, ntree = 1000)
rf.5
varImpPlot(rf.5)
#RESULTS: error rate: 18.41%


# Train a Random Forest using pclass, title, sibsp, & family.size
rf.train.6 <- data.combined[1:891, c("Pclass", "Title", "SibSp", "family.size")]

set.seed(1234)
rf.6 <- randomForest(x = rf.train.6, y = rf.label, importance = TRUE, ntree = 1000)
rf.6
varImpPlot(rf.6)
#RESULTS: error rate: 19.08%


# Train a Random Forest using pclass, title, parch, & family.size
rf.train.7 <- data.combined[1:891, c("Pclass", "Title", "Parch", "family.size")]

set.seed(1234)
rf.7 <- randomForest(x = rf.train.7, y = rf.label, importance = TRUE, ntree = 1000)
rf.7
varImpPlot(rf.7)
#RESULTS: error rate: 19.42%

#==============================================================================
#
# Video #5
#
#==============================================================================


# Before we jump into features engineering we need to establish a methodology
# for estimating our error rate on the test set (i.e., unseen data). This is
# critical, for without this we are more likely to overfit. Let's start with a 
# submission of rf.5 to Kaggle to see if our OOB error estimate is accurate.

# Subset our test records and features
test.submit.df <- data.combined[892:1309, c("Pclass", "Title", "family.size")]

# Make predictions
rf.5.preds <- predict(rf.5, test.submit.df)
rf.5.preds[1:10]
table(rf.5.preds)
table(rf.5.preds)["1"] / table(rf.5.preds)["0"] # ~62% survived...

# Write out a CSV file for submission to Kaggle
submit.df <- data.frame(PassengerId = rep(892:1309), Survived = rf.5.preds)

write.csv(submit.df, file = "RF_SUB_20160215_1.csv", row.names = FALSE)

# Our submission scores 0.79426, but the OOB predicts that we should score 0.8159.
# Let's look into cross-validation using the caret package to see if we can get
# more accurate estimates
library(caret)
library(doSNOW)


# Research has shown that 10-fold CV repeated 10 times is the best place to start,
# however there are no hard and fast rules - this is where the experience of the 
# Data Scientist (i.e., the "art") comes into play. We'll start with 10-fold CV,
# repeated 10 times and see how it goes.


# Leverage caret to create 100 total folds, but ensure that the ratio of those
# that survived and perished in each fold matches the overall training set. This
# is known as stratified cross validation and generally provides better results.
set.seed(2348)
cv.10.folds <- createMultiFolds(rf.label, k = 10, times = 10)

# Check stratification
table(rf.label)
342 / 549

table(rf.label[cv.10.folds[[33]]])
308 / 494


# Set up caret's trainControl object per above.
ctrl.1 <- trainControl(method = "repeatedcv", number = 10, repeats = 10,
                       index = cv.10.folds)


# Set up doSNOW package for multi-core training. This is helpful as we're going
# to be training a lot of trees.
# NOTE - This works on Windows and Mac, unlike doMC
cl <- makeCluster(6, type = "SOCK")
registerDoSNOW(cl)


# Set seed for reproducibility and train
set.seed(34324)
rf.5.cv.1 <- train(x = rf.train.5, y = rf.label, method = "rf", tuneLength = 3,
                   ntree = 1000, trControl = ctrl.1)

#Shutdown cluster
stopCluster(cl)

# Check out results
rf.5.cv.1


# The above is only slightly more pessimistic than the rf.5 OOB prediction, but 
# not pessimistic enough. Let's try 5-fold CV repeated 10 times.
set.seed(5983)
cv.5.folds <- createMultiFolds(rf.label, k = 5, times = 10)

ctrl.2 <- trainControl(method = "repeatedcv", number = 5, repeats = 10,
                       index = cv.5.folds)

cl <- makeCluster(6, type = "SOCK")
registerDoSNOW(cl)

set.seed(89472)
rf.5.cv.2 <- train(x = rf.train.5, y = rf.label, method = "rf", tuneLength = 3,
                   ntree = 1000, trControl = ctrl.2)

#Shutdown cluster
stopCluster(cl)

# Check out results
rf.5.cv.2


# 5-fold CV isn't better. Move to 3-fold CV repeated 10 times. 
set.seed(37596)
cv.3.folds <- createMultiFolds(rf.label, k = 3, times = 10)

ctrl.3 <- trainControl(method = "repeatedcv", number = 3, repeats = 10,
                       index = cv.3.folds)

cl <- makeCluster(6, type = "SOCK")
registerDoSNOW(cl)

set.seed(94622)
rf.5.cv.3 <- train(x = rf.train.5, y = rf.label, method = "rf", tuneLength = 3,
                   ntree = 64, trControl = ctrl.3)

#Shutdown cluster
stopCluster(cl)

# Check out results
rf.5.cv.3
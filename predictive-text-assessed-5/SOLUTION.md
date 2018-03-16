# Solution approach

## A brief explanation as to how my system works.
Firstly, my system asserts(pre-conditions) the procedures invovled with building the Dictionarytree via (insert(word), insert(word, popularity) by not accept empty strings as inputs for buidldng the Dictionarytree and after asserts(Post-conditions) that the word was actually inserted into the tree. 

In addition, these same assertions(which can either be pre or post-conditions) are applied to a few more procedures(contains(word), predict(prefix), predict(prefix, numberOfPredictions), remove(word)) which require such assertions to be validated before the the procedure can succsesfully begin operations.   

Furthermore, just to highlight on few procedures that require clarification. One of which include my remove(word) procedure. my remove(word) does not initially remove the word but assigns popularity of the DictionaryTree to which the last character points, Optional(Value) to empty(). Thereafter, In prune()(procedure) it calls accumulate and place the results of accumulate into a List of popular word after validate if the current DictionaryTree Optional(value) is is present(), if it is, it adds it to a list of characters to be removed. Then it finally loops through the list of characters to be removed and removes it from the tree. 

For insert(word, popularity), from the word given my systems insert a the word into a tree if the word cannot be found in the tree. In addition to that it assigns the last Dictionary tree to which the last charater inserted points to populariity(Optional) to empty.      


My accumulate(listOfPopularWord, prefix), has a listOfPopularWords as an accumulator and the prefix is the characters on the branches that were taken to get to the current node.

For size(), I use my fold method implemented and java's reduce to sum the all the child nodes sizes and  adding one to it.

For longestWord(), I get all the words in the tree and sort the words in reverse order after, I return the first word if it is not empty else i return an empty string.

for allwords(), I get list of popular word and the word from the word and place them into a list.

Finally, for prediction(prefix, numOfPredictions), I traverse through each character, then call all words and sort it by popularity and limit it by the numOfPredictions given and return the list of words. Then for predict(prefix) converts a list of on element to an optional.


## Advantages of using trees for predicting multipe words

i)There reason why there are some advantages to using trees for multiple word prediction is because it checks only for words that start with the prefix. 

ii)In addition, supposing the tree was very huge, it is most likely to be time efficient. 


## Disadvantage of using trees for predicting multipe words

i) Also, it requires more computation and it is less memory efficient.

## Git link.
https://git.cs.bham.ac.uk/kxf672/software-workshop-assignment.git

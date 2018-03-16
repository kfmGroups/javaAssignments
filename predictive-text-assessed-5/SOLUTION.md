# Solution approach

## A brief explanation as to how my system works.
Firstly, my system asserts(pre-conditions) the procedures invovled with building the Dictionarytree via (insert(word), insert(word, popularity) does not accept digits and empty strings as inputs for buidldng the Dictionarytree. 

In addition, these same assertions are applied to a few more procedures(contains(word), predict(prefix), predict(prefix, numberOfPredictions), remove(word)) which require such assertions to be validated before the the procedure can succsesfully begin operations.   

Furthermore, just to highlight on few procedures that require clarification. One of which include my remove(word) procedure. my remove(word) does not initially remove the word but assigns the last character which points to next DictionaryTree(emptyTree) in the word Optional(Value) to empty(). Thereafter, In prune() validates if the current DictionTree Optional(value) is Optional(value)is present() it adds it to a list of characters to be removed. Then it finally loops through the list of characters to be removed and removes it from the tree. 


##predict(word, numOfPredictions)
##fold()

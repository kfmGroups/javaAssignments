#SOLUTION DESCRIPTION:

when I(the user) types "quit" rather than recipeient name followed by a message to the client(clientSender) thread, the client(clientSender) thread via an if-condition is informed of my intentions to close all threads handling streams going and coming to the server, so it then passes that argument on to the server(serverReciever) and puts an end to its thread(clientSender).

Thereafter, in the server(serverReciever), from the argument recieved it will be informed of the user intentions via an if-condition to put an end to all the streams, thus the server(serverReciever) passes on the argument to the server(serverSender), removes the user(I) from the clientTable(stores information about clients and their corresponding messages) and puts an end to its thread(serverReciever).

Also, immediately the server(serverSender) recieves the argument, via an if-condition it will also be informed of the users intentions, thereafter passing on the argument to the final thread(clientReciever) and then put an end to its thread(serverSender).

Finally, from the given argument passed from the server(serverSender) this thread(clientReciever) via an if-condition is also informed of the users intention to put an end to all threads involved in the socket connection. In addition to ensure, the socket closes after putting an to all the threads handling input and output streams in both the server class and client class I wait for all threads to end with(.join()) in both classes before closing the streams and the socket in the server and client class.

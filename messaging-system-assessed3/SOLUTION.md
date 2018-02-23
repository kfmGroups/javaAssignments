
# Solution approach

# A brief explanation as to how my system is going to function.
First of all, I approached this problem by creating an interface called Command which led to the definition of unimplemented methods such as getNumberofArguments(), execute(), getCommand(), mustBeLoggedIn() and finally a static readCommand(). Afterwards, created a class called CommandArguments and another ServerCommandArguments an extension of CommandArguments. CommandArguments consist of a set of public variables namely whihch are going to represent the arguments entered by the user from the command line, an array which stores the users list of arguments, their stream(which cold be to the server and from the server) and also the name of the user logged in whiles ServerCommandArguments stores all data belonging to the server whiles the ServerCommandArgument will consist of the login table(LoginTable), clienttable of users(ClientTable)  and also user whom are logged in(UserLoggedin). In additon also stores a table storing the streams of users(StreamTable), the client name(clientName) and finally a timer and the time task object which perform saving operation(i.e saving users registered and their messages and the a list of indexes for each user) over a specified preriod of time(1milliseconds).  

In addition, I created abstract classes for implementing method bodies for specific types of command. Such as register, login and logout etc. Thus, for each type of command(abstract classes) was specified how many arguments were required from the command line(getNumeberOfArguments based on the particular type of command entered by the user of the program and also a method to return the name of the command(getCommmand()). In addition to a mustBeLoggedIn().

Finally, I had a final set of classes which handled operations on both the server side and clientside and they all extended the a specific command abstract class. These classes were created based on what the static method readCommand() read, where based on the userinput provided through an if-condition and boolean variable drove the decision making process of my system on whether to create an object to handle client side operations in the system or to handle the server side oprations in my sysytem.  

# Register
For registrtaion when the register(command )is passed as an input, my system, 
from the client via readCommand(), the first object created(new RegisterClient()) just sends the user credentials(clientName and password)  received from the command line as I said I defined above  via an execute method but before its called, the abstract register Command class has already defined how many arguments to be expected from the user whom has decided to be registered. For which the command entered is sent to the serverReciever which also reads via its readCommand() to also create an object(new RegisterClientInServer()) meant for registering the client in the server. This is done via the execute method called from the serverReciever.After, the server responds throught the clientReciever the user's registration was successful he is added to the clientTable or rather not due to the user being registerd already.

# Login
For logging in when the login(command )is passed as an input, my system, 
from the client via readCommand(), the first object created(new LoginClient()) just sends the user login credentials(clientName and password) received from the command line as I said I defined above  via an execute method but before its called,the abstract Login Command class has already defined how many arguments to be expected from the user whom has decided to Login in to the messaging system. For which the command entered is sent to the serverReciever which also reads via its readCommand() to also create an object(new LoginClientInServer()) meant for logging the client in the server. This is done via the execute method called from the serverReciever.After, the server responds throught the clientReciever if the user's was logged in successfully or not successful which is caused by the user entering the wrong login details or already being logged in on another client. In addition, in my system,
if the user logs in successfully I either display his current message if any else my system responds by informing the user of being logged in successufully. Also, when I run my system initially, my system does not have the clients name. However, my clientname is ascertained when he logs in successfully via successful login and current message if any, which(clientName whom  has logged in succsessfully is then stored in the client via a Client.setUserName() else if it returns a string starting with "Error: ", for which then Client.setUserName() is set to null.

# Logout
For logging out when the logout(command)is passed as an input, my system, 
from the client via readCommand(), the first object created(new LogsTheClientOut()) just checks if the user trying to logout is logged in before he logs the client out and I said above via an execute method but before its called,the abstract Logout Command class has already defined how many arguments(which is 0) to be expected from the user whom has decided to Login in to the messaging system. For which in my client my system via Client.setUserName() is set to null indicating to the system that he is logged out. After the command entered is sent to the serverReciever which also reads via its readCommand() to also create an object(LogClientOutServer()) meant for logging the user out from the server. Thus, before the user of this system logs out. this class(new LogClientOutServer()) checks if the user is logged in before he can be logged out therefter remove his stream. In addition to that if the user is not logged in the system informs him to login before he can logout.

# Previous
For previous my system just confirms if the user is logged in before the user is allowed to utilize the previous command. Else it just responds to the user his previous message and if there are not any he informs the user of not having any previous messages

# Next
Next works exactly the same as previous but for the next message.

# Delete
Delete it gets the current message and sets the next to the new current message. 

# Extra Features

## Quit
The user can enter a command to quit the client when they are logged out.

## Password and Message Encryption
The messages are encrypted with the users passsword as the key and passwords are encrpyted using a pre-shared key, ideally this will be randomly generated.Encryption was done using an a Encryptor class.

## User table and messages stored in a file so that a server can be stopped and restarted without loss of information.
Messages and registerd users are saved to the file system every second and loaded when the server starts up.


# Git link
https://git.cs.bham.ac.uk/kxf672/software-workshop-assignment.git



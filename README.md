# Application

This application showcases _**Akka Remoting**_; communication module designed to connect actor systems in a peer-to-peer fashion.


## Actor

MessagePrinterActor actor logs content of messages that it receives.

## Remote actor system

A remote actor system is created by loading configuration file `remote-application.conf`. 

The actor system creates an instance of the `MessagePrinterActor`. Upon running the remote actor system, one can notice the path of the `MessagePrinterActor`.

Path of the remote actor should be 
> akka://RemoteSystem/user/remote-message-printer

There are two ways in which a client interact with the remote actor
 - _**Through look-up**_: Seeks an actor on a remote node via actor selection. 
 - _**Through creation**_: Creates an actor on a remote node via actorOf method.
  
## Client that seeks a remote actor

`CreateInteractionClient` loads the configuration file `create-client-application.conf` for a new client actor system.
 
 It contains a client that looks up the remote instance of `MessagePrinterActor` in another actor system namely `RemoteSystem` and sends a message to it.
 
 The selection path used conforms to the syntax `<protocol>//<remote-actor-system>@<host>:<port>`

## Client that creates a remote actor

`SeekInteractionClient` loads the configuration file `seek-client-application.conf` for a new client actor system.

 It contains a client that creates a new remote instance of `MessagePrinterActor` in another actor system namely `RemoteSystem` and sends a message to it.

## Deployment instructions

- Run the application _**RemoteSystem**_ in its dedicated tab in terminal.
- Run the application _**SeekInteractionClient**_ in a new tab in terminal. As the client sends a message to the remote actor, one should be able to see a new message in the terminal window of _**RemoteSystem**_. The message should typically be 
 > Actor[akka://RemoteSystem/user/remote-message-printer#<numeric-identifier>]: received message 'Hello from 'seek-interaction-client''
- Run the application _**CreateInteractionClient**_ in a new tab in terminal. As the client creates a new remote actor, one should notice the path to the newly created remote actor as 
 > akka.tcp://RemoteSystem@127.0.0.1:50711/remote/akka.tcp/CreateClientSystem@127.0.0.1:50875/user/non-local-message-printer
- The _**CreateClientSystem**_ sends a message to the new remote actor in _**RemoteSystem**_ so in the terminal window of RemoteSystem, one should find the following
 > Actor[akka://RemoteSystem/remote/akka.tcp/CreateClientSystem@127.0.0.1:50875/user/non-local-message-printer#<numeric-identifier>]: received message 'Hello from 'create-interaction-client''
 